package com.github.pomaretta.cide.sockets.exercice03;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;

public class Server {
    
    class ClientHandler extends Thread {

        private int clientPort;
        private int serverPort;

        private String clientIp;
        private String clientId;

        private DatagramSocket socket;
        private DatagramPacket packet;

        private HashMap<String, Boolean> clients;

        public ClientHandler(
            int serverPort,
            String clientIp,
            int clientPort,
            String clientId,
            HashMap<String, Boolean> clients
        ){
            this.clientPort = clientPort;
            this.serverPort = serverPort;
            this.clientIp = clientIp;
            this.clientId = clientId;
            this.clients = clients;
        }

        @Override
        public void run() {
            
            // Create socket
            try {
                this.socket = new DatagramSocket(this.serverPort);
                // Timeout to 20 seconds
                this.socket.setSoTimeout(20000);
                // this.socket.setSoTimeout(5000);
            } catch (Exception e) {
                return;
            }

            // Create packet
            byte[] buffer = new byte[1024];
            this.packet = new DatagramPacket(buffer, buffer.length);

            // Send the new port to the client
            try {
                System.out.printf(
                    "\n\t[%s] Sending new port to %s:%d",
                    this.clientId,
                    this.clientIp,
                    this.clientPort
                );
                DatagramPacket clientResponse = new DatagramPacket(
                    String.valueOf(this.serverPort).getBytes(),
                    String.valueOf(this.serverPort).getBytes().length
                );
                clientResponse.setAddress(InetAddress.getLocalHost());
                clientResponse.setPort(clientPort);
                this.socket.send(clientResponse);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

            while (true) {

                // Receive packet from client
                try {
                    this.socket.receive(this.packet);
                } catch (Exception e) {
                    // Client is not responding after 5 seconds
                    break;
                }

                // Send the confirmation
                try {
                    System.out.printf(
                        "\n\t[%s] Sending confirmation to %s:%d",
                        this.clientId,
                        this.clientIp,
                        this.clientPort
                    );
                    DatagramPacket confirmation = new DatagramPacket(
                        "OK".getBytes(),
                        "OK".getBytes().length
                    );
                    confirmation.setAddress(InetAddress.getLocalHost());
                    confirmation.setPort(clientPort);
                    this.socket.send(confirmation);
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }

            }

            System.out.printf(
                "\n\t[%s] Client %s:%d is not responding... removing it from the list",
                this.clientId,
                this.clientIp,
                this.clientPort
            );
    
            // Unregister client.
            this.clients.replace(this.clientId, false);
            this.socket.close();

        }

    }

    public void start() throws Exception {

        // Táctica:
        // 1. 
        // Cuando un cliente se conecta al puerto principal.
        // se obtiene su identificador. Crear un nuevo socket con otro puerto
        // para que el cliente pueda enviar mensajes al servidor.
        // 2.
        // El servidor se queda en escucha del nuevo puerto para ese cliente en un thread aparte
        // contando cuanto tiempo desde que el cliente envío la última señal.

        final int PORT = 45001;

        DatagramSocket serverSocket = new DatagramSocket(PORT);
        HashMap<String, Boolean> clients = new HashMap<>();

        System.out.printf(
            "Starting server\n\tPort=%d",
            PORT
        );

        // Se quedara esperando nuevas conexiones
        while (true) {
            
            // Recibir el paquete
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            serverSocket.receive(packet);

            // Se obtiene el identificador del cliente, ip + puerto
            String clientIp = packet.getAddress().getHostAddress();
            int clientPort = packet.getPort();
            String clientId = String.format(
                "%s:%d",
                clientIp,
                clientPort
            );

            System.out.printf(
                "\nClient connected\n\tId=%s\n\tIp=%s\n\tPort=%d",
                clientId,
                clientIp,
                clientPort
            );

            // Generar un puerto para crear un nuevo listener para el cliente
            int redirectPort = (PORT + 1) + (int)((Math.random() * 500) + 1);

            // Register client in HashMap.
            if (clients.containsKey(clientId) && !clients.get(clientId)) {
                // Client reconnected
                clients.replace(clientId, true);
            } else {
                // New client
                clients.put(clientId, true);
            }

            System.out.printf(
                "\n\tRedirecting to port=%d\n",
                redirectPort
            );

            // Create handler
            new ClientHandler(
                redirectPort,
                clientIp,
                clientPort,
                clientId,
                clients
            ).start();
        }
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.start();
    }
    
}
