package com.github.pomaretta.cide.sockets.exercice05;

import java.io.InputStream;
import java.net.Socket;

public class Client {
    
    public static void main(String[] args) throws Exception {
        
        Socket socket = new Socket("localhost", 54605);
        
        InputStream is = socket.getInputStream();
        
        while (is.available() == 0) {
            // wait
        }

        byte[] buffer = new byte[is.available()];
        is.read(buffer);
        System.out.println(new String(buffer));

        socket.close();

    }

}
