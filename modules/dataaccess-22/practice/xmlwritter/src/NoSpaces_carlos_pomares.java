import java.io.File;
import java.io.FileReader;

/**
 * 
 * Acceso a Datos - 2021-2022
 * 
 * Práctica 1
 * 
 * Leer un archivo X, iterar sobre su contenido y de cada línea, quitar los espacios.
 * 
 * @author Carlos Pomares (http://www.github.com/pomaretta)
 */
public class NoSpaces_carlos_pomares {
    
    public static void main(String[] args) throws Exception {
        
        // Creamos un File Reader que nos permitira iterar sobre el archivo.
        FileReader reader = new FileReader(new File("./data.txt"));

        // Inicializamos un dato de tipo integer para ir almacenando los bytes entrantes.
        int data = 0;

        // Iteramos el stream hasta que nos encontremos con un -1, que indicara el final de archivo.
        while ((data = reader.read()) != -1) {
            // Si nos encontramos con un espacio lo imitimos.
            if (((char) data) == ' ') {
                continue;
            }
            // Si nos es un espacio lo mostrará por pantalla.
            System.out.print((char) data);
        }

        // Cerramos el stream.
        reader.close();

    }

}
