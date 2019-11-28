package com.treloiii;

public class Main {
    public static void main(String[] args){
//        if (args.length < 1) {
//            System.out.println("Syntax: java ChatServer <port-number>");
//            System.exit(0);
//        }
        Server server=new Server(8091);
        server.startServer();
    }
}
