import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;

/**
 * 
 * Servicios y Procesos - 21/22
 * 
 * Práctica 3
 * 
 * Crea un programa que lance un proceso hijo el cual abra una consola CMD, 
 * le pase un comando (por ejemplo: un DIR) y guarde el resultado en un fichero. 
 * El proceso padre debe esperar a que el proceso hijo acabe 
 * y luego mostrar por pantalla el contenido del fichero.
 * 
 * @author Carlos Pomares (http://www.github.com/pomaretta)
 * 
 */


public class EX03_carlos_pomares {

    public static void main(String[] args) throws Exception {
        
        // Archivo en el cual se escribiran datos y se leeran posteriomente.
        File f = new File("./out.txt");

        // Los comandos a ejecutar (Alternativa a DIR en Windows)
        String[] commands = new String[]{
            "/bin/ls",
            "-la"
        };

        // Creación del Builder pasando los comandos.
        ProcessBuilder pb = new ProcessBuilder(commands);
        // Creación y inicio del proceso.
        Process p = pb.start();

        // Inicialización de un reader con un input del proceso hijo.
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        // Inicialización de un writer para escribir el resultado del proceso hijo en el archivo.
        FileWriter wr = new FileWriter(f);
        
        // Inicialización de la línea.
        String line = null;

        // Iteramos el input stream del proceso hasta que este pare.
        while ((line = br.readLine()) != null) {
            // Cuando obtenemos una nueva línea, la escribimos en el archivo out.txt
            wr.write(line + "\n"); // Necesita un salto de línea al final.
        }

        // Cerramos tanto el stream del proceso como el del archivo.
        br.close();
        wr.close();

        // Esperamos a que termine el proceso hijo y obtenemos su código de salida.
        int exitCode = p.waitFor();
        
        // Lanzamos una excepción si ha ocurrido un error a la hora de lanzar el proceso hijo.
        if (exitCode != 0) {
            throw new Exception(String.format("Process exited with status code %d", exitCode));
        }

        // Creamos un reader que permitira leer el contenido del archivo.
        BufferedReader bfr = new BufferedReader(new FileReader(f));

        // Iteramos el archivo anteriormente creado y mostramos su contenido.
        while ((line = bfr.readLine()) != null) {
            System.out.println(line);
        }

        // Cerramos el stream del reader.
        bfr.close();

    }
    
}
