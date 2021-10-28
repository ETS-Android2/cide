import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 
 * Servicios y Procesos - 21/22
 * 
 * Práctica 5
 * 
 * Proceso Hijo
 * 
 * Un programa que debe devolver por su salida estándar un número aleatorio entre 0 y 10,
 * donde si recibe el comando "fin" acabe su ejecución.
 * 
 * @author Carlos Pomares (https://www.github.com/pomaretta)
 * 
 */

public class RANDOM_Child_carlos_pomares {
    
    // La entrada estándar del proceso.
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    /**
     * 
     * El constructor de la applicación, donde necesita obtener la entrada estándar del proceso.
     * 
     * @param input la entrada estándar del proceso.
     */
    public RANDOM_Child_carlos_pomares(InputStream stdin, OutputStream stdout) {
        this.dataInputStream = new DataInputStream(stdin);
        this.dataOutputStream = new DataOutputStream(stdout);
    }

    /**
     * 
     * El bucle principal de la aplicacion, donde comprobará si tienes datos disponibles en su entrada estándar.
     * 
     * @throws IOException si la entrada estándar se ha cerrado.
     */
    public void mainLoop() throws IOException {

        boolean end = false;

        // Bucle principal que está iterando hasta que el proceso padre lo pare.
        while (!end) {

            // Si no tiene datos para procesar, seguira iterando.
            if (this.dataInputStream.available() == 0) continue;

            // Obtiene los datos de la entrada estándar.
            String out = this.dataInputStream.readUTF();
        
            // Comprueba si la entrada coincide con el código de parada de proceso.
            if (out.equals("fin")) {
                end = true;
                continue;
            }

            // Escribe por la salida estandar del proceso el número aleatorio entre 0-10
            this.dataOutputStream.writeUTF(String.valueOf((int) (Math.random() * 11)));
        }
    }

    public static void main(String[] args)  throws Exception {
        // Inicializamos la aplicación.
        RANDOM_Child_carlos_pomares app = new RANDOM_Child_carlos_pomares(System.in, System.out);
        // Iniciamos el bucle principal de la aplicación.
        app.mainLoop();
    }

}
