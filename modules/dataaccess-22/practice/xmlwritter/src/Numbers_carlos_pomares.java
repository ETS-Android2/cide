import java.io.FileWriter;
import java.io.File;

/**
 * 
 * Acceso a Datos - 2021-2022
 * 
 * Realitzeu un programa en JAVA que faci volcat d’un 
 * array amb els 100 primers números parells a un 
 * fitxer de texto.
 * 
 * @author Carlos Pomares (http://www.github.com/pomaretta)
 */

public class Numbers_carlos_pomares {

    public static void main(String[] args) throws Exception {

        // Creamos un writer para escribir cada número.
        FileWriter writer = new FileWriter(new File("./data.txt"));

        // Un contador, para obtener cada número.
        int counter = 0;
        // Un contador, para saber cuantos números llevamos escritos.
        int writted = 0;
        // Una bandera, para saber cuando parar.
        boolean stop = false;

        // Mientras no paremos, seguiremos obteniendo un número nuevo.
        // Si es par, se escribirá en el fichero y aumentaremos el contador, de los escritos.
        // Siempre subira el contador de números.
        while (!stop) {
            // Si hemos escrito 100 numeros, parara el bucle.
            if (writted == 100) stop = true;
            // Si es par, lo escribirá en el fichero y aumentará el contador de los escritos.
            if ((counter % 2) == 0) {
                writer.write(String.valueOf(counter) + "\n");
                writted++;
            }
            // Siempre aumenta el número a iterar.
            counter++;
        }

        // Cerramos el stream del writer.
        writer.close();

    }

}