package com.github.pomaretta.cide.sockets.exercice03;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.channels.DatagramChannel;

public class Server {
    
    public static void main(String[] args) throws Exception {
        
        DatagramSocket socket = new DatagramSocket(54605);    
        DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
    
        while (true) {
            socket.receive(packet);

            String received = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Received: " + received);

            if (received.equals("done")) {
                System.out.println("Server is done");
                break;
            }   
        }

        socket.close();

    }

}
