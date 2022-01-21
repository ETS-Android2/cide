package com.github.pomaretta.cide.sockets.exercice03;

import java.net.DatagramSocket;
import java.util.Scanner;

public class Client {
    
    public static void main(String[] args) throws Exception {
        
        DatagramSocket socket = new DatagramSocket();

        Scanner scanner = new Scanner(System.in);

        boolean exit = false;

        do {

            System.out.print("Enter a message: ");
            String message = scanner.nextLine();

            if (message.equals("exit")) {
                exit = true;
            } else {
                byte[] buffer = message.getBytes();
                socket.send(new java.net.DatagramPacket(buffer, buffer.length, new java.net.InetSocketAddress("localhost", 54605)));
            }

        } while (!exit);

        scanner.close();
        socket.close();

    }

}
