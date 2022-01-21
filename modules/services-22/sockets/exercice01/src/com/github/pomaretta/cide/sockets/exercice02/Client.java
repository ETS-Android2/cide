package com.github.pomaretta.cide.sockets.exercice02;

import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    
    public static void main(String[] args) throws Exception {
        
        // Creamos el socket
        Socket socket = new Socket();

        // Obtenemos la IP del servidor
        // IP=localhost
        // PORT=54605
        InetSocketAddress serverAddress = new InetSocketAddress("localhost", 54605);

        // Conectamos el socket al servidor
        socket.connect(serverAddress);

        System.out.println("Connected to server");

        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        // Enviamos el mensaje al servidor
        socket.getOutputStream().write(input.getBytes());

        // Esperamos a que el servidor responda
        InputStream is = socket.getInputStream();
        while (is.available() == 0) {
            // Esperamos
        }

        byte[] buffer = new byte[1024];
        int read = is.read(buffer);
        System.out.println(new String(buffer, 0, read));

        // Cerramos el socket
        socket.close();

    }

}
