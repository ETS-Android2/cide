package com.github.pomaretta.cide.sockets.exercice03;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

/**
 * @author Carlos Pomares <https://www.github.com/pomaretta>
 */
public class Client {
    
    public static void main(String[] args) throws Exception {
        
        // Creamos el socket en el puerto 54604
        DatagramSocket socket = new DatagramSocket(54604);
        // Creamos el paquete para poder recibir datos.
        DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);

        // Creamos el scanner para poder obtener datos del cliente.
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        do {

            // Obtenemos el mensaje a enviar.
            System.out.print("Enter a message: ");
            String message = scanner.nextLine();

            // Si el mensaje es "exit" acabará el programa cliente.
            if (message.equals("exit")) {
                exit = true;
            } else {
                byte[] buffer = message.getBytes();
                // Enviamos el mensaje al servidor
                socket.send(
                    new java.net.DatagramPacket(
                        buffer,
                        buffer.length,
                        new java.net.InetSocketAddress("localhost", 54605) // Al puerto 54605
                    )
                );
            }

            // Esperamos una respuesta del servidor
            try {
                socket.receive(packet);
                String input = new String(packet.getData(), 0, packet.getLength());
                if (!input.equals("")) {
                    System.out.println("Servidor[Recibido]: " + input);
                }
            } catch (SocketTimeoutException timeout) {
                // Si no hay respuesta, seguimos esperando.
                System.out.println("Se esperaba respuesta del servidor pero no ha habido alguna.");
            }

        } while (!exit);

        scanner.close();
        
        // Cerramos el socket.
        socket.close();

    }

}
