package com.github.pomaretta.cide.sockets.exercice03;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    
    public static void main(String[] args) throws Exception {
     
        final int SERVER_PORT = 45001;

        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(10000);

        // Server Packet
        byte[] handshakeBuffer = new byte[1024];
        DatagramPacket handshakePacket = new DatagramPacket(handshakeBuffer, handshakeBuffer.length);
        handshakePacket.setData("handshake".getBytes());
        handshakePacket.setAddress(InetAddress.getLocalHost());
        handshakePacket.setPort(SERVER_PORT);

        // Client Packet
        byte[] clientBuffer = new byte[1024];
        DatagramPacket clientPacket = new DatagramPacket(clientBuffer, clientBuffer.length);

        // Send handshake
        socket.send(handshakePacket);
        
        // Recieve new port
        socket.receive(clientPacket);

        int serverPort = Integer.parseInt(new String(clientPacket.getData()).trim());

        while (true) {

            // Print confirmation
            System.out.printf("Sending alive to server\n\tPort=%d\n",serverPort);

            // Send a message to the server
            socket.send(new DatagramPacket(
                "alive".getBytes(),
                "alive".getBytes().length,
                InetAddress.getLocalHost(),
                serverPort
            ));

            // Recieve a confirmation from the server
            socket.receive(clientPacket);

            // Print confirmation
            System.out.println("Received confirmation from server");

            int waitFor = 2000 + (int)(Math.random() * 5000);
            System.out.println("Sleeping for " + waitFor + "ms");
            Thread.sleep(waitFor);

        }
        

    }

}
