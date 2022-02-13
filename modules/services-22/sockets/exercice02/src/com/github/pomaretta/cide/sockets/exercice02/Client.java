package com.github.pomaretta.cide.sockets.exercice02;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    
    public static void main(String[] args) throws Exception {
        
        // New socket with random port
        DatagramSocket socket = new DatagramSocket();
        
        DatagramPacket packet = new DatagramPacket(
            "Handshake".getBytes(),
            "Handshake".length()
        );
          
        packet.setAddress(
            InetAddress.getByName("localhost")
        );
        packet.setPort(
            44001
        );

        socket.send(packet);

        // Wait for the server to send the IP Address
        DatagramPacket response = new DatagramPacket(
            new byte[1024],
            1024
        );
        
        socket.receive(response);
        System.out.println(
            "Server IP Address: " +
            new String(response.getData())
        );

        socket.close();

    }

}
