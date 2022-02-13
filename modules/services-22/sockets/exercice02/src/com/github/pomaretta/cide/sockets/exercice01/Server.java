package com.github.pomaretta.cide.sockets.exercice01;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;

/**
 * @author Carlos Pomares <https://www.github.com/pomaretta>
 */
public class Server {

    class ClientHandler implements Runnable {
            
            private Socket socket;

            private File fileToGet;
    
            public ClientHandler(Socket socket) {
                this.socket = socket;
            }
    
            private String listFiles() throws Exception {
                StringBuilder sb = new StringBuilder();
                for (File file : new File(".").listFiles()) {
                    if (file.isDirectory()) continue;
                    sb.append(file.getName()).append("\n");
                }
                return sb.toString();
            }

            private byte[] getFile(String fileName) throws Exception {
                
                byte[] bytes = null;
                fileToGet = null;
                
                Files.list(Path.of(".")).map(Path::toFile).forEach(f -> {
                    if (f.getName().equals(fileName)) fileToGet = f;
                });
                
                if (fileToGet == null || fileToGet.isDirectory()) {
                    bytes = new byte[1];
                    bytes[0] = -1;
                    return bytes;
                }

                bytes = Files.readAllBytes(fileToGet.toPath());
                return bytes;
            }

            @Override
            public void run() {
                
                InputStream inputStream = null;
                OutputStream outputStream = null;

                try {
                    inputStream = socket.getInputStream();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    outputStream = socket.getOutputStream();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                while (true) {
                    
                    int available = 0;

                    try {
                        if (inputStream.available() == 0) continue;
                        available = inputStream.available();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    byte[] buffer = new byte[available];
            
                    try {
                        inputStream.read(buffer);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    String[] message = new String(buffer).split(" ");

                    if (message[0].equals("exit")) {
                        System.out.println("Client disconnected");
                        break;
                    }
                    
                    switch (message[0]) {
                        case "LLISTAR":
                            try {
                                String s = listFiles();
                                System.out.println(s);
                                outputStream.write(s.getBytes());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case "OBTENIR":
                            if (message.length < 2) break;
                            try {
                                byte[] bytes = getFile(message[1]);
                                outputStream.write(bytes);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        default:
                            System.out.println("Invalid operation");
                            break;
                    }

                }

                try {
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
    }

    private final int PORT = 44001;

    private ServerSocket serverSocket;
    private LinkedList<ClientHandler> clients;

    public Server() throws Exception {
        this.serverSocket = new ServerSocket(this.PORT);
        this.clients = new LinkedList<ClientHandler>();
    }

    public void run() throws Exception {
        while (true) {
            Socket client = this.serverSocket.accept();
            System.out.println("Client connected");
            ClientHandler clientHandler = new ClientHandler(client);
            this.clients.add(clientHandler);
            new Thread(clientHandler).start();
        }
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.run();
    }

}
