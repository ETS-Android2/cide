package com.github.pomaretta.cide.sockets.exercice01;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Carlos Pomares <https://www.github.com/pomaretta>
 */
public class Server {

    public static void main(String[] args) throws Exception {

        // Creamos el socket del servidor en el puerto 54605
        ServerSocket serverSocket = new ServerSocket(54605);
        
        // Mostramos un mensaje en el servidor para indicar que estamos listos para recibir conexiones.
        System.out.println("Esperando un cliente...");

        // Obtenemos el Socket del client
        Socket socket = serverSocket.accept();

        // Obtenemos la entrada del socket.
        InputStream is = socket.getInputStream();
        byte[] buffer = new byte[1024];
        int read = is.read(buffer);

        // Mostramos el mensaje recibido.
        System.out.println("Recibido: " + new String(buffer, 0, read));

        // Cerramos los sockets.
        socket.close();
        serverSocket.close();

    }

}
