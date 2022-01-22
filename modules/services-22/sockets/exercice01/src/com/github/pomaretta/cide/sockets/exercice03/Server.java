package com.github.pomaretta.cide.sockets.exercice03;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author Carlos Pomares <https://www.github.com/pomaretta>
 */
public class Server {
    
    public static void main(String[] args) throws Exception {
        
        // Creamos el socket en el puerto 54605
        DatagramSocket socket = new DatagramSocket(54605);    
        // Creamos el paquete para poder recibir datos.
        DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
    
        boolean exit = false;
        while (!exit) {

            // Esperamos a que llegue un paquete
            socket.receive(packet);

            // Obtenemos el mensaje
            String received = new String(packet.getData(), 0, packet.getLength());
            // Mostramos en el servidor que hemos recibido el mensaje.
            System.out.println("Cliente[Recibido]: " + received);

            // Comprobamos si el mensaje es un "Uep".
            if (received.equals("Uep")) {
                // Generamos el mensaje de respuesta.
                String message = "Que tal?";
                byte[] buffer = message.getBytes();
                // Mandamos el mensaje.
                socket.send(
                    new DatagramPacket(
                        buffer,
                        buffer.length,
                        new java.net.InetSocketAddress("localhost", 54604) // El otro cliente/servidor está en el puerto 54604
                    )
                );
            } else if (received.equals("done")) {
                System.out.println("Apagando servidor...");
                exit = true;
            }   
        }

        socket.close();

    }

}
