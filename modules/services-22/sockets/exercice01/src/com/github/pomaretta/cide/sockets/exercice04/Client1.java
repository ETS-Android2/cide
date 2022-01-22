package com.github.pomaretta.cide.sockets.exercice04;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author Carlos Pomares <https://www.github.com/pomaretta>
 */
public class Client1 {
    
    public static void main(String[] args) throws Exception {
        
        // Creamos el socket
        DatagramSocket socket = new DatagramSocket(54600);
        // Creamos el paquete para poder recibir datos.
        DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);

        // Mensaje de token
        String message = "token";
        byte[] buffer = message.getBytes();
        // Lo enviamos al otro cliente en el puerto 54601
        socket.send(
            new DatagramPacket(
                buffer,
                buffer.length,
                new java.net.InetSocketAddress("localhost", 54601)
            )
        );

        // Esperamos a que llegue un paquete
        while (true) {
            // Recibimos el paquete
            socket.receive(packet);
            // Obtenemos el mensaje
            String input = new String(packet.getData(), 0, packet.getLength());
            // Si es "Rebut" salimos del bucle
            if (input.equals("Rebut")) {
                break;
            }
        }

        // Cerramos el socket
        socket.close();

    }

}
