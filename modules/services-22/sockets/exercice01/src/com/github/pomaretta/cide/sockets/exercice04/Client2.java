package com.github.pomaretta.cide.sockets.exercice04;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author Carlos Pomares <https://www.github.com/pomaretta>
 */
public class Client2 {
    
    public static void main(String[] args) throws Exception {
        
        // Creamos el socket en el puerto 54601
        DatagramSocket socket = new DatagramSocket(54601);    
        // Creamos el paquete para poder recibir datos.
        DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
    
        
        while (true) {
            // Esperamos a que llegue un paquete
            socket.receive(packet);

            // Obtenemos el mensaje
            String received = new String(packet.getData(), 0, packet.getLength());
            // Mostramos en el servidor que hemos recibido el mensaje.
            System.out.println("Cliente[Recibido]: " + received);

            // Comprobamos si el mensaje es un "token".
            if (received.equals("token")) {
                // Generamos el mensaje de respuesta.
                String message = "Rebut";
                byte[] buffer = message.getBytes();
                // Mandamos el mensaje.
                socket.send(
                    new DatagramPacket(
                        buffer,
                        buffer.length,
                        new java.net.InetSocketAddress("localhost", 54600)
                    )
                );
                break;
            }   
        }

        // Cerramos el socket
        socket.close();

    }

}
