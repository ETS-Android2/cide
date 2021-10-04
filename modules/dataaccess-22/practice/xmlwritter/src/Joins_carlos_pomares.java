import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 * Acceso a Datos - 2021-2022
 * 
 * Práctica 4
 * 
 * Crea una aplicación que pida la ruta de dos ficheros de texto 
 * y de una ruta de destino (solo la ruta, sin fichero al final).
 * Debes copiar el contenido de los dos ficheros en uno, 
 * este tendrá el nombre de los dos ficheros separados por un guion bajo, 
 * y se guardara en la ruta donde le hayamos indicado por teclado.
 * 
 * Para unir los ficheros en uno, crea un método donde le pases como 
 * parámetro todas las rutas. En este método, aparte de copiar 
 * debe comprobar que si existe el fichero de destino, nos muestre 
 * un mensaje informándonos de si queremos sobrescribir el fichero. 
 * Te recomiendo usar la clase File y JOptionPane. Por ejemplo, si tengo un fichero
 * A.txt con “ABC” como contenido, un fichero B.txt con “DEF” 
 * y una ruta de destino D:\, el resultado sera un fichero 
 * llamado A_B.txt en la ruta D:\ con el contenido “ABCDEF”.
 * 
 * Puedes crear submétodos para realizar la copia de ficheros.
 * Recuerda que debes controlar las excepciones que puedan aparecer. 
 * En caso de error, mostrar una ventana de dialogo con información del error.
 * 
 * @author Carlos Pomares (http://www.github.com/pomaretta)
 */

public class Joins_carlos_pomares extends JFrame {
    
    // On normal situation, is better to use package methods, recommended one is from apache library
    // but is not from java standard library, for not reinventing the wheel, I prefer to use a functional
    // method from the internet.
    // https://stackoverflow.com/questions/941272/how-do-i-trim-a-file-extension-from-a-string-in-java
    public static String removeExtension(String s) {

        String separator = System.getProperty("file.separator");
        String filename;
    
        // Remove the path upto the filename.
        int lastSeparatorIndex = s.lastIndexOf(separator);
        if (lastSeparatorIndex == -1) {
            filename = s;
        } else {
            filename = s.substring(lastSeparatorIndex + 1);
        }
    
        // Remove the extension.
        int extensionIndex = filename.lastIndexOf(".");
        if (extensionIndex == -1)
            return filename;
    
        return filename.substring(0, extensionIndex);
    }
    
    /**
     * 
     * Merge two files into a given destination.
     * 
     * @param f first file to get the data.
     * @param f2 second file to get the data.
     * @param destination the destination file to write the data.
     * @throws IOException if an error occurs opening or reading the files.
     */
    private static void mergeFiles(File f, File f2, File destination) throws IOException {

        // Read the data from the files.
        String firstData = readFile(f);
        String secondData = readFile(f2);

        // Merge the data (String + String)
        String mergedData = mergeData(firstData, secondData);
    
        // Write the merged data inside the destination file.
        FileWriter writer = new FileWriter(destination);
        writer.write(mergedData);
        // Close stream, for avoid flow errors.
        writer.close();

    }

    /**
     * 
     * Read the content of a given file.
     * 
     * @param f the file to read from.
     * @return The content of the file as String.
     * @throws IOException if an error occurs reading the file.
     */
    private static String readFile(File f) throws IOException {
        
        // New buffered reader from a FileReader.
        BufferedReader reader = new BufferedReader(new FileReader(f));
        // New StringBuilder as output.
        StringBuilder out = new StringBuilder();
        
        // Read all the lines of the files.
        String line = null;
        while ((line = reader.readLine()) != null) {
            // Append to the string builder.
            out.append(line);
        }
        // Close stream.
        reader.close();

        // Return data.
        return out.toString();
    }

    /**
     * 
     * Merge two Strings in one, using joins.
     * 
     * @param v data from first value.
     * @param v1 data from second value.
     * @return the results of the join of the two values.
     */
    private static String mergeData(String v, String v1) {
        return String.join("", v, v1);
    }

    /**
     * 
     * Display an error window with the given message and title.
     * 
     * @param message the message to be shown.
     * @param title the title to the pane.
     */
    private void errorMessage(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) throws Exception {
        
        // Create a new JFrame
        Joins_carlos_pomares app = new Joins_carlos_pomares();

        // Get data from User, using Scanner.
        Scanner userIn = new Scanner(System.in);

        // Declare two files.
        File firstFile = null;
        File secondFile = null;

        // Get input of the first file.
        System.out.print("Introduce la primera ruta: ");
        String path = userIn.nextLine();

        /*
            Create a file object and check if exists, else will show an error window.
            Then exists the application.
        */
        firstFile = new File(path);
        if (!firstFile.exists()) {
            app.errorMessage("El archivo especificado no existe.", "Primer archivo");
            System.exit(1);
        }

        // Get the second file from the user.
        System.out.print("Introduce la segunda ruta: ");
        path = userIn.nextLine();

    
        /*
            Create the second file object and check if exists, else 
            exit the application showing an error window.
        */
        secondFile = new File(path);
        if (!secondFile.exists()) {
            app.errorMessage("El archivo especificado no existe.", "Segundo archivo");
            System.exit(1);
        }

        // Obtain the target directory to place the new file.
        JFileChooser chooser = new JFileChooser();
        // Set the current directory of the pane, with the cwd.
        chooser.setCurrentDirectory(new File("."));
        // Set the selection mode of the FileChooser, get directories only.
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        // Open the pane and get the input from the user.
        chooser.showOpenDialog(app);

        // Get the selected file.
        File destination = chooser.getSelectedFile();
        if (destination == null || !destination.isDirectory()) {
            app.errorMessage("El archivo seleccionado no es un directorio.", "Directorio");
            System.exit(1);
        }

        // Create the filepath, removing extensions and joining with a _.
        String filePath = String.format("%s_%s.txt", removeExtension(firstFile.getName()), removeExtension(secondFile.getName()));
        
        // Create the destination file, joining the selected directory absolute path, a file separator and the file path name.
        File destinationFile = new File(String.format("%s%s%s", destination.getAbsolutePath(), File.separator, filePath));

        // Override flag.
        boolean override = false;
        if (destinationFile.exists()) {
            // If the file exists, ask the user if want to override it.
            int action = JOptionPane.showConfirmDialog(app, String.format("El archivo %s existe, desea sobresescribirlo?", filePath), "Sobreescribir", JOptionPane.YES_NO_OPTION);
            // action == 0, means that the user picked the yes answer.
            if (action == 0) override = true;
        }

        // If exists and override is false, show a message exiting.
        if (destinationFile.exists() && override == false) {
            app.errorMessage("Operación cancelada.", "Saliendo...");
            System.exit(0);
        }

        // Merge the files.
        try {
            mergeFiles(firstFile, secondFile, destinationFile);
        } catch (IOException e) {
            app.errorMessage("Error al intentar juntar los archivos!\n" + e.getMessage(), "Saliendo...");
        } catch (Exception e) {
            app.errorMessage("Error inesperado!\n" + e.getMessage(), "Saliendo...");
        } finally {
            userIn.close();
            System.exit(0);
        }

    }

}
