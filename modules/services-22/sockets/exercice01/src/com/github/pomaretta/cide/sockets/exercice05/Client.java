package com.github.pomaretta.cide.sockets.exercice05;

import java.io.InputStream;
import java.net.Socket;

/**
 * @author Carlos Pomares <https://www.github.com/pomaretta>
 */
public class Client {
    
    public static void main(String[] args) throws Exception {
        
        // Creamos el socket para conectarnos al servidor con puerto 54605
        Socket socket = new Socket("localhost", 54605);
        
        // Obtenemos el InputStream para poder obtener datos que nos envie el servidor.
        InputStream is = socket.getInputStream();
        
        // Esperamos hasta que obtengamos respuesta.
        while (is.available() == 0) {
            // wait
        }

        byte[] buffer = new byte[is.available()];
        is.read(buffer);
        // Mostramos el mensaje que nos envio el servidor.
        System.out.println(new String(buffer));

        // Cerramos el socket
        socket.close();

    }

}
