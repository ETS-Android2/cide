package com.github.pomaretta.cide.sockets.exercice02;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class Server {
    
    public static void main(String[] args) throws Exception {
        
        DatagramSocket socket = new DatagramSocket(new InetSocketAddress("localhost", 44001));
        DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);

        while (true) {
            // Accept new connection
            socket.receive(packet);
            System.out.println("Handshake received");
            DatagramPacket response = new DatagramPacket(new byte[1024], 1024);
            response.setAddress(packet.getAddress());
            response.setPort(packet.getPort());
            response.setData(InetAddress.getLocalHost().getHostAddress().getBytes());

            // Send the server IP Address with the response packet
            System.out.println("Sending IP Address to client with port " + response.getPort() + " and address " + response.getAddress().getHostAddress());
            socket.send(response);
        }

    }

}
