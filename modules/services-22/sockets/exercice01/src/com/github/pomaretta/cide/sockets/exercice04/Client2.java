package com.github.pomaretta.cide.sockets.exercice04;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Client2 {
    
    public static void main(String[] args) throws Exception {
        
        DatagramSocket socket = new DatagramSocket(54601);    
        DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
    
        while (true) {
            socket.receive(packet);

            String received = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Received: " + received);

            if (received.equals("token")) {
                String message = "Rebut";
                byte[] buffer = message.getBytes();
                socket.send(new DatagramPacket(buffer, buffer.length, new java.net.InetSocketAddress("localhost", 54600)));
                break;
            }   
        }

        socket.close();

    }

}
