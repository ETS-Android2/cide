package com.github.pomaretta.cide.sockets.exercice02;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
 
    public static void main(String[] args) throws Exception {
        
        ServerSocket serverSocket = new ServerSocket(54605);
        System.out.println("Waiting for client...");
        Socket socket = serverSocket.accept();

        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        
        char c;
        String input = "";
        while ((c = (char) is.read()) != '\n') {
            input += c;
            if (c == '?')
                break;
        }

        
        String output = "";
        switch (input.toLowerCase()) {
            case "com et dius?":
                output = "Com et dius? Em dic Exercici 2";
                break;
            case "quantes linies de codi tens?":
                output = "Quantes linies de codi tens? Tinc una de cada tres";
                break;
            default:
                output = "No he entès la pregunta";
                break;
        }

        System.out.println("Sending: " + output);

        os.write(output.getBytes());

        socket.close();
        serverSocket.close();

    }

}
