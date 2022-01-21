package com.github.pomaretta.cide.sockets.exercice05;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    public static void main(String[] args) throws Exception {
        
        ServerSocket serverSocket = new ServerSocket(54605);
        ArrayList<Socket> connections = new ArrayList<Socket>();

        while (true) {
            Socket socket = serverSocket.accept();
            connections.add(socket);
            OutputStream os = socket.getOutputStream();
            os.write(String.format("Connection accepted\nNumber of clients: %d", connections.size()).getBytes());
            os.flush();
        }

    }

}
