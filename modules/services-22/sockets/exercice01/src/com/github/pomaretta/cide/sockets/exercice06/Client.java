package com.github.pomaretta.cide.sockets.exercice06;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Carlos Pomares <https://www.github.com/pomaretta>
 */
public class Client {
    
    public static void main(String[] args) throws Exception {

        // Posición en el anillo.
        Integer position = Integer.parseInt(args[0]);
        // Tamaño del anillo.
        Integer lenght = Integer.parseInt(args[1]);

        // Socket del servidor
        ServerSocket serverSocket = new ServerSocket(
            54000 + position
        );

        // En caso de ser el primero de la cadena, primero enviamos mensaje después recibimos.
        if (position == 1) {
        
            // Connect to the next server.
            Socket socket = null;
            boolean connected = false;

            while (!connected) {
                try {
                    socket = new Socket(
                        "localhost",
                        54000 + (position + 1)
                    );
                    connected = true;
                } catch (Exception e) {
                    // Esperamos a que se pueda establcer conexión con el siguiente servidor.
                }
            }

            // Mandamos el mensaje de token.
            socket.getOutputStream().write(
                String.format("Cliente{Posición=%d, Anillo=%d};token", position, lenght).getBytes()
            );

            socket.close();

            // Esperamos una conexión.
            Socket before = serverSocket.accept();

            // Obtenemos el mensaje del anterior cliente.
            byte[] buffer = new byte[1024];
            before.getInputStream().read(buffer);

            // Mostramos el mensaje.
            System.out.println(
                new String(buffer)
            );

            before.close();

            // Acabamos el programa.
            return;
        }
    
        // Esperamos a que el cliente se conecte.
        Socket socket = serverSocket.accept();

        // Recibimos el mensaje del anterior cliente.
        byte[] buffer = new byte[1024];
        socket.getInputStream().read(buffer);

        // Mostramos el mensaje.
        System.out.println(
            new String(buffer)
        );

        // Nos conectamos al servidor siguiente.
        Socket next = null;
        boolean connected = false;
        Integer target = position == lenght ? 1 : position + 1;
        

        while (!connected) {
            try {
                next = new Socket(
                    "localhost",
                    54000 + target
                );
                connected = true;
            } catch (Exception e) {
                // Esperamos a que se pueda establcer conexión con el siguiente servidor.
            }
        }

        // Send the message to the next server.
        next.getOutputStream().write(
            String.format("Cliente{Posición=%d, Anillo=%d};token", position, lenght).getBytes()
        );
   
    }

}
