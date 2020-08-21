package server;

import java.net.*;

public class Server{

    public static void main(String[] args) {

        try{

            ServerSocket server = new ServerSocket(2050);
            while(true){
                System.out.println("Waiting for a client");
                Socket socket = server.accept();
                System.out.println("Client connected");
                ServerRunnable sr = new ServerRunnable(socket);
                sr.start();
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

}
