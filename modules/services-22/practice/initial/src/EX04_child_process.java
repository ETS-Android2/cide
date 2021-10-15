import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * 
 * Servicios y Procesos - 21/22
 * 
 * Práctica 4
 * 
 * Un programa hijo que se ocupe de leer un archivo.
 * 
 * @author Carlos Pomares (https://www.github.com/pomaretta)
 * 
 */

public class EX04_child_process {

    public static void main(String[] args) throws Exception {
        
        // Get the origin file to read from.
        File f = new File(args[0]);
        // Get the letter to check.
        Character letter = args[1].toCharArray()[0];
        // Get the file to output data.
        File destination = new File(args[2]);

        // Obtain the inputStream from the process.
        FileReader r = new FileReader(f);

        // The each character to read.
        int c = 0;
        // The counter of the letter.
        int counter = 0;

        // Iterate each character after end of file, and check if lowercase is equal
        // to the letter.
        while ((c = r.read()) != -1) {
            if (Character.toLowerCase((char) c) == letter) counter++;    
        }

        // Close the inputStream of the process.
        r.close();

        // Create a new FileWriter to write the counter on a file.
        FileWriter writer = new FileWriter(destination);
        // Write the value of the counter as String (for avoid writting as bytes value).
        writer.write(String.valueOf(counter));
        // Close the writer.
        writer.close();

    }
    
}
