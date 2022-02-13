package com.github.pomaretta.cide.sockets.exercice01;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Carlos Pomares <https://www.github.com/pomaretta>
 */
public class Client {

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("localhost", 44001);
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        OutputStream outputStream = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();

        while (!exit) {

            System.out.println("1. LLISTAR ARXIUS");
            System.out.println("2. DESCARREGAR ARXIU");
            System.out.println("3. EXIT");

            System.out.print("$ ");
            String command = scanner.nextLine();

            int commandId = 0;

            try {
                commandId = Integer.parseInt(command);
            } catch (Exception e) {
                System.out.println("ERROR: Comanda no valida");
                continue;
            }

            switch (commandId) {
                case 1:
                    System.out.println("LLISTANT ARXIUS");
                    outputStream.write("LLISTAR".getBytes());
                    outputStream.flush();
                    while (inputStream.available() == 0){
                        // wait
                    }
                    byte[] buffer = new byte[inputStream.available()];
                    inputStream.read(buffer);
                    System.out.println(new String(buffer));
                    break;
                case 2:
                    System.out.print("Nom del arxiu: ");
                    String fileName = scanner.nextLine();
                    outputStream.write(("OBTENIR " + fileName).getBytes());
                    outputStream.flush();
                    while (inputStream.available() == 0){
                        // wait
                    }
                    buffer = new byte[inputStream.available()];
                    inputStream.read(buffer);
                    System.out.println(new String(buffer));
                    break;
                case 3:
                    outputStream.write("exit".getBytes());
                    outputStream.flush();
                    exit = true;
                    break;
            }

        }

        socket.close();

    }

}