/**
 * 
 * Servicios y Procesos - 21/22
 * 
 * Práctica 4
 * 
 * Crear un programa que sea capaz de contar cuantas vocales hay en un fichero 
 * (por ejemplo el texto de este enunciado). El programa padre debe lanzar 
 * cinco procesos hijo, donde cada uno de ellos se ocupará de contar 
 * una vocal concreta (que puede ser minúscula o mayúscula). 
 * 
 * Cada subproceso que cuenta vocales deberá dejar el resultado en un fichero. 
 * El programa padre se ocupará de recuperar los resultados de los ficheros, 
 * sumar todos los subtotales y mostrar el resultado final en pantalla.
 * 
 * @author Carlos Pomares (https://www.github.com/pomaretta)
 * 
 */

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class EX04_carlos_pomares {
    
    /**
     * 
     * Read one letter to lowercase on the file, counts
     * the number of times that the letter appears on the origin
     * file.
     * 
     * @param letter the letter to count.
     * @param origin the origin data.
     * @param destination the destination file to write the count.
     * @return the exit code of the process.
     * @throws Exception if the process fails.
     */
    public int readLetterFromFile(Character letter, File origin, File destination) throws Exception {

        // The command to execute, see the file with CAT, will change on Windows.
        // This works on Linux and macOS.
        String[] commands = new String[]{
            "/bin/cat"
            ,origin.getAbsolutePath()
        };

        // Create the process builder and start the process.
        ProcessBuilder pb = new ProcessBuilder(commands);
        Process p = pb.start();

        // Obtain the inputStream from the process.
        InputStreamReader r = new InputStreamReader(p.getInputStream());

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

        // Return the exit code of the process.
        return p.waitFor();
    }

    /**
     * 
     * Reads the file, and returns it as integer value.
     * This can throw an exception if the number is not a number
     * or is an invalid character.
     * 
     * @param f the file to read from.
     * @return the first byte of the file.
     * @throws IOException if cannot open the file or cannot parse the number.
     */
    private String readNumberOfFile(File f) throws IOException {
        FileReader reader = new FileReader(f);
        int n = 0;
        String out = new String();
        while ((n = reader.read()) != -1) {
            out += ((char) n);
        }
        reader.close();
        return out;
    }

    /**
     * 
     * Iterates between files, and reads the file for a number.
     * 
     * @param files the files to iterate.
     * @return the sum of each file number.
     * @throws IOException if cannot opens one file.
     */
    public int sumAllFiles(ArrayList<File> files) throws IOException {
        int counter = 0;
        for (File file : files) {
            counter += Integer.valueOf(
                this.readNumberOfFile(file)
            );
        }
        return counter;
    }

    public static void main(String[] args) throws Exception {
        
        // Create the App.
        EX04_carlos_pomares app = new EX04_carlos_pomares();

        // Array for store processes exit codes.
        ArrayList<Integer> exitCodes = new ArrayList<Integer>();
        // Array for store the destination files.
        ArrayList<File> files = new ArrayList<File>();

        // Origin file to read from.
        File origin = new File("./origin.txt");

        // All letters to check.
        Character[] letters = new Character[]{
            'a', 'e', 'i', 'o', 'u'
        };

        // Iterate each letter and create the process.
        for (Character letter : letters) {
            // Create the letter file. i.e "a.txt"
            File file = new File(String.format("./%s.txt", Character.toString(letter)));
            // Add to the files array.
            files.add(file);

            // Create each process and store the exit code in the array.
            exitCodes.add(
                app.readLetterFromFile(
                    letter // The letter to search, char
                    ,origin // The file to read from, as File
                    ,file // The file to write in, as File.
                )
            );
        }

        // Sum all files, iterate each file and get the number.
        int sum = app.sumAllFiles(files);
        
        // Show on screen the final count.
        System.out.println(String.format("Result of count: %d", sum));

    }

}
