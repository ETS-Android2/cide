package com.github.pomaretta.cide.sockets.exercice01;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.net.InetSocketAddress;
import java.net.Socket;

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

        // Leemos el archivo de texto para enviar al servidor.
        File file = new File("resources/data.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line;
        String output = "";
        while((line = reader.readLine()) != null) {
            output += line + "\n";
        }
        reader.close();

        System.out.println("Sending data: " + output);

        // Enviamos el archivo al servidor
        socket.getOutputStream().write(output.getBytes());
        
        // Cerramos el socket
        socket.close();

    }

}