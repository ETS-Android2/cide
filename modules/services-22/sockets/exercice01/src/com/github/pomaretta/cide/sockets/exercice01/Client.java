package com.github.pomaretta.cide.sockets.exercice01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author Carlos Pomares <https://www.github.com/pomaretta>
 */
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

        // Mensaje para indicar que nos hemos conectado.
        System.out.println("Conectados al servidor.");

        // Leemos el archivo de texto para enviar al servidor.
        File file = new File("resources/data.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line;
        String output = "";
        while((line = reader.readLine()) != null) {
            // Agregamos cada línea para que mandemos todo el archivo al completo.
            output += line + "\n";
        }
        reader.close();

        // Mensaje en el client para indicar que estamos enviando.
        System.out.println("Enviando: " + output);

        // Enviamos el contenido del archivo al servidor
        socket.getOutputStream().write(output.getBytes());
        
        // Cerramos el socket
        socket.close();

    }

}