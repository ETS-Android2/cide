import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * Acceso a Datos - 2021-2022
 * 
 * Práctica 4
 * 
 * Crea una aplicación donde pidamos la ruta de un fichero por teclado
 * y un texto que queramos a escribir en el fichero. 
 * Deberás mostrar por pantalla el mismo texto pero variando entre mayúsculas y minúsculas, 
 * es decir, si escribo “Bienvenido” deberá devolver “bIENVENIDO”. 
 * Si se escribe cualquier otro carácter, se quedara tal y como se escribió. 
 * Deberás crear un método para escribir en el fichero el texto introducido 
 * y otro para mostrar el contenido en mayúsculas.
 * 
 * @author Carlos Pomares (http://www.github.com/pomaretta)
 */

public class MinUpp_carlos_pomares {

    // Scanner for getting input of the user.
    private static Scanner userIn = new Scanner(System.in);
    
    /**
     * 
     * Returns a string, where the original lowercase characters
     * are replaced with uppercase, and original uppercase to lowercase.
     * 
     * @param data the original string to reverse.
     * @return The result of the reversed original string.
     */
    private static String reverse(String data) {
        // String Builder for using append methods.
        StringBuilder str = new StringBuilder();

        for (Character c : data.toCharArray()) {
            /* 
                If the character is uppercase, then its converted to lowercase.
                And its appended to the str output.
            */
            if (Character.isUpperCase(c)) {
                str.append(Character.toLowerCase(c));
                continue;
            }
            // As default, all characters are converted to uppercase. 
            // Except for already detected uppercase characters
            str.append(Character.toUpperCase(c));
        }
        return str.toString();
    }

    /**
     * 
     * Write a given string to the given File.
     * 
     * @param file File to be written to.
     * @param data The data to be write on the file.
     * @throws IOException If the operation cannot be finished.
     */
    private static void writeToFile(File file, String data) throws IOException {
        FileWriter writer = new FileWriter(file);
        writer.write(data);
        writer.close();
    }

    public static void main(String[] args) throws Exception {
        
        /*

            GENERAL QUESTIONS

            - Crea una aplicación donde pidamos la ruta de un fichero por teclado 
            y un texto que queramos a escribir en el fichero.

            We want to write the original text of the users without reversing it?

            ==============

            - Si se escribe cualquier otro carácter, se quedara tal y como se escribió.

            This is about the reverse method? Or it's a different functionality of the application?

            ==============

            - Deberás crear un método para escribir en el fichero el texto introducido 
            y **otro para mostrar el contenido en mayúsculas**.

            About showing the content in uppercase, I assume that I can convert to uppercase (or reverse functionality I thought), 
            and then showing it, instead of making a method that calls @reverse function and calls System.out inside the method.
            
        */

        // Get the user file path.
        System.out.print("Ruta de archivo: ");
        String filePath = userIn.nextLine();

        // Check if the file is null or empty.
        if (filePath == null || filePath.equals("")) {
            throw new Exception("Error al parsear la ruta o la ruta está vacia.");
        }

        // Create the file object with the given Path.
        File file = new File(filePath);
        
        // Get the user line and reverse it.
        System.out.print("Introduce el texto a escribir: ");
        String userLine = userIn.nextLine();

        // Print the reversed string to the user.
        System.out.println(String.format("Resultado: %s", reverse(userLine)));

        // Write to file the reversed line.
        try {
            writeToFile(file, userLine);
        } catch (IOException e) {
            // If throws an IOException, then alert user the specified exception.
            System.out.println("Error al escribir en el archivo!");
            e.printStackTrace();
        } catch (Exception e) {
            // If throws an unexpected exception, show to the user the error.
            System.out.println("Ha ocurrido un error!");
            e.printStackTrace();
        }

    }

}
