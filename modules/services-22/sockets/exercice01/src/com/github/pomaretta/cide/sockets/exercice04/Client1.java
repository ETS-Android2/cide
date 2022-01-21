package com.github.pomaretta.cide.sockets.exercice04;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Client1 {
    
    public static void main(String[] args) throws Exception {
        
        DatagramSocket socket = new DatagramSocket(54600);
        DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);

        String message = "token";
        byte[] buffer = message.getBytes();
        socket.send(new DatagramPacket(buffer, buffer.length, new java.net.InetSocketAddress("localhost", 54601)));

        while (true) {
            socket.receive(packet);
            String input = new String(packet.getData(), 0, packet.getLength());
            if (input.equals("Rebut")) {
                break;
            }
        }

        socket.close();

    }

}
