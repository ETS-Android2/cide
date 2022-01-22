package com.github.pomaretta.cide.sockets.exercice05;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @author Carlos Pomares <https://www.github.com/pomaretta>
 */
public class Server {

    public static void main(String[] args) throws Exception {
        
        // Creamos el socket en el puerto 54605
        ServerSocket serverSocket = new ServerSocket(54605);
        // Creamos un array de sockets para almacenar los sockets que se conectan.
        ArrayList<Socket> connections = new ArrayList<Socket>();

        while (true) {
            // Esperamos a que llegue una conexión
            Socket socket = serverSocket.accept();
            // Añadimos el socket a la lista de sockets.
            connections.add(socket);
            // Obtenemos el OutputStream del socket para poderle mandar datos.
            OutputStream os = socket.getOutputStream();
            // Enviamos en número de sockets que hay.
            os.write(String.format("Conexión establecida\nNúmero de clientes: %d", connections.size()).getBytes());
            os.flush();
        }

    }

}
