package com.github.pomaretta.cide.sockets.exercice01;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(54605);
        System.out.println("Waiting for client...");
        Socket socket = serverSocket.accept();

        InputStream is = socket.getInputStream();
        byte[] buffer = new byte[1024];

        int read = is.read(buffer);
        System.out.println("Received: " + new String(buffer, 0, read));

        socket.close();
        serverSocket.close();

    }

}
