package com.treloiii;

import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;

public class User extends Thread {

    private Server server;
    private Socket client;
    private PrintWriter writer;
    private String userName;
    private Gson gson;
    User(Server server,Socket client){
        this.server=server;
        this.client=client;
        this.gson=new Gson();
    }


    @Override
    public void run() {

        try{

            InputStream input=this.client.getInputStream();
            BufferedReader reader=new BufferedReader(new InputStreamReader(input));
            OutputStream out=this.client.getOutputStream();
            writer=new PrintWriter(out,true);

            Message message= gson.fromJson(reader.readLine(),Message.class);
            this.userName=message.getName();
            Message clientMessage;
            this.server.checkClient(this);
            server.addUser(this);
            System.out.println("Новое подключение: "+this.userName);

            do{
                clientMessage=gson.fromJson(reader.readLine(),Message.class);
                System.out.println(clientMessage.toString());
                switch (clientMessage.getTextAdmin()) {
                    case "msg":
                    case "msgImage":
                    case "Image":
                        server.broadcast(clientMessage);
                        break;

                    case "textInput":
                        server.broadcast(new Message("Печатает", clientMessage.getName(), "textInput",null));
                        break;
                    case "textOver":
                        server.broadcast(new Message("", clientMessage.getName(), "textOver",null));
                        break;
                    case "ping":
                        server.broadcast(new Message("pong", clientMessage.getName(), "pong",null));
                        break;
                }
            }
            while (!(clientMessage.getTextAdmin() != null && clientMessage.getTextAdmin().equals("disconnect")));
            disconnect();
            System.out.println("Пользователь отключился: "+this.userName);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void disconnect(){
        try {
            this.sendMessage(new Message("disconnect",this.userName,"disconnect",null));
            this.client.close();
            this.server.removeUser(this);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Message message){
        //DO send message
        writer.println(gson.toJson(message));
    }

    public String getUserName() {
        return userName;
    }

    public Socket getClient() {
        return client;
    }
}
