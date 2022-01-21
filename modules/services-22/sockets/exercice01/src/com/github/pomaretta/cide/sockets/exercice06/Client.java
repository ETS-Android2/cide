package com.github.pomaretta.cide.sockets.exercice06;

import java.net.ServerSocket;
import java.net.Socket;

public class Client {
    
    public static void main(String[] args) throws Exception {

        // Posición en el anillo.
        Integer position = Integer.parseInt(args[0]);
        // Tamaño del anillo.
        Integer lenght = Integer.parseInt(args[1]);

        // Socket del servidor
        ServerSocket serverSocket = new ServerSocket(
            54000 + position
        );

        if (position == 1) {
        
            // Connect to the next server.
            Socket socket = null;
            boolean connected = false;

            while (!connected) {
                try {
                    socket = new Socket(
                        "localhost",
                        54000 + (position + 1)
                    );
                    connected = true;
                } catch (Exception e) {
                    // Wait for the next server.
                }
            }

            // Send the message to the next server.
            socket.getOutputStream().write(
                String.format("Client{Position=%d, Lenght=%d};token", position, lenght).getBytes()
            );

            // Close the socket.
            socket.close();

            // Wait to end loop
            Socket before = serverSocket.accept();

            // Read the message from the previous server.
            byte[] buffer = new byte[1024];
            before.getInputStream().read(buffer);

            System.out.println(
                new String(buffer)
            );

            // Close the socket.
            before.close();


        } else {

            // Wait for the connection.
            Socket socket = serverSocket.accept();

            // Receive the message from the previous server.
            byte[] buffer = new byte[1024];
            socket.getInputStream().read(buffer);

            System.out.println(
                new String(buffer)
            );

            // Connect to the next server.
            Socket next = null;
            boolean connected = false;
            Integer target = position == lenght ? 1 : position + 1;
            

            while (!connected) {
                try {
                    next = new Socket(
                        "localhost",
                        54000 + target
                    );
                    connected = true;
                } catch (Exception e) {
                    // Wait for the next server.
                }
            }

            // Send the message to the next server.
            next.getOutputStream().write(
                String.format("Client{Position=%d, Lenght=%d};token", position, lenght).getBytes()
            );

        }
        
    }

}
