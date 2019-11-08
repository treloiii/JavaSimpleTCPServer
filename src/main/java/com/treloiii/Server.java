package com.treloiii;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Server {

    private int port;
    //private Set<String> userNames=new HashSet<>();
    private Set<User> users=new HashSet<>();
    Server(int port){
        this.port=port;
    }

    public void startServer(){
        try(ServerSocket server=new ServerSocket(this.port)){
            System.out.println("Server started on port "+port);
            while(true){
                Socket socket =server.accept();
                //new user connected
                User user=new User(this,socket);
                //users.add(user);
                user.start();

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addUser(User user){
        this.users.add(user);
    }

    public void checkClient(User newUser){
        for (User user:users) {
            if(user.getUserName().equals(newUser.getUserName())){
                System.out.println("disconnect11");
                user.disconnect();
            }
        }
    }
    public void broadcast(String message){
        for (User user:users) {
            user.sendMessage(message);
        }
    }

//    public void addUserName(String userName){
//        userNames.add(userName);
//    }

    public void removeUser(User removable){
        users.remove(removable);
    }

}
