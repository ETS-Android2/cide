package com.github.pomaretta.cide.sockets.exercice02;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Carlos Pomares <https://www.github.com/pomaretta>
 */
public class Server {
 
    public static void main(String[] args) throws Exception {
        
        // Creamos el server socket en el puerto 54605
        ServerSocket serverSocket = new ServerSocket(54605);

        // Mostramos que estamos esperando a una conexión.
        System.out.println("Esperando a un cliente...");

        // Aceptamos la conexión en cuanto se conecte un cliente.
        Socket socket = serverSocket.accept();

        // Obtenemos el input stream para poder leer los mensajes.
        InputStream is = socket.getInputStream();

        // Obtenemos el output stream para poder enviar mensajes.
        OutputStream os = socket.getOutputStream();
        
        // Leemos el mensaje de entrada del cliente.
        char c;
        String input = "";
        while ((c = (char) is.read()) !=  -1) {
            input += c;
            if (c == '?')
                break;
        }

        // Creamos el mensaje de salida.
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


        // Mostramos en el cliente el mensaje que se va a enviar.
        System.out.println("Enviando: " + output);

        // Enviamos el mensaje al cliente.
        os.write(output.getBytes());

        // Cerramos los sockets.
        socket.close();
        serverSocket.close();

    }

}
