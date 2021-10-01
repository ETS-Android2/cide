import java.io.IOException;

/**
 * 
 * Servicios y Procesos - 2021-2022
 * 
 * Práctica 2
 * 
 * Creación de procesos, y salida de errores.
 * 
 * @author Carlos Pomares (http://www.github.com/pomaretta)
 */

public class EX02_carlos_pomares {    
    public static void main(String[] args) throws Exception {
        
        // Creamos la instancia en null.
        Process p = null;

        try {
            /* 
                Creamos el proceso con los argumentos
                de entrada asignandolos al Runtime.
            */
            p = Runtime.getRuntime().exec(args);
        } catch (IOException e) {
            // En caso de que el comando insertado sea erroneo lanzará una excepción, y mostraremos un mensaje de error.
            throw new Exception("El proceso no ha podido realizarse.");
        }
        
        // Destruimos el proceso.
        p.destroy();
        
        /*
            Mostramos el código de salida del proceso, en este caso, dado que destruimos el proceso
            de forma seguida a la creación, seguramente no acabará, por ello cuando nos muestre el código
            de salida, saldrá diferente a 0.        
        */
        System.out.printf("Codigo: %d", p.waitFor());

    }
}
