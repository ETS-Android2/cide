import java.io.IOException;

/**
 * 
 * Servicios y Procesos - 2021-2022
 * 
 * Práctica 1
 * 
 * Creación de procesos, y salida de errores.
 * 
 * @author Carlos Pomares (http://www.github.com/pomaretta)
 */

public class EX01_carlos_pomares {    
    public static void main(String[] args) throws Exception {
        
        // Creamos la instancia en null.
        Process p = null;

        try {
            /* 
                Creamos el proceso con los argumentos
                de entrada asignandolos al ProcessBuilder y lo iniciamos.
            */
            p = new ProcessBuilder(args).start();
        } catch (IOException e) {
            // En caso de que el comando insertado sea erroneo lanzará una excepción, y mostraremos un mensaje de error.
            throw new Exception("El proceso no ha podido realizarse.");
        }
        
        /*
            Una vez iniciado el proceso, esperaremos a que este acabe, esperando el valor de salida de la
            ejecución, en caso de ser diferente a 0, indica que ha salido con error el proceso, y lanzaremos
            una excepción diciendo que el proceso no ha podido ser realizado satisfactoriamente.
        */
        if (p.waitFor() != 0) {
            throw new Exception("El proceso no ha terminado satisfactoriamente (waitFor).");
        }

    }
}
