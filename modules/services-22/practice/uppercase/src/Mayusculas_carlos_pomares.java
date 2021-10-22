import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * 
 * Servicios y Procesos - 21/22
 * 
 * Práctica 5
 * 
 * Proceso Padre
 * 
 * Escribir una clase llamada Mayúsculas que haga lo siguiente:
 * Cree un proceso hijo.
 * 
 * El proceso padre y el proceso hijo se comunicarán de manera bidireccional utilizando streams.
 * 
 * El proceso padre leerá líneas de su entrada estándar y las enviará a la entrada estándar del
 * hijo (utilizando el OutputStream del hijo).
 * 
 * El proceso hijo lee el texto por su entrada estándar, lo transformará todo en letras mayúsculas y lo imprimirá para su salida estándar. Para realizar el programa hijo se puede utilizar cualquier lenguaje de programación, generando un ejecutable.
 * 
 * El padre imprimirá en pantalla lo que recibe del hijo a través del InputStream de este.
 * 
 * @author Carlos Pomares (https://www.github.com/pomaretta)
 * 
 */

public class Mayusculas_carlos_pomares {

    // Scanner del padre
    private Scanner userIn;

    // Proceso hijo
    private Process childProcess;
    // El input stream del hijo
    private DataInputStream childStdin;
    // El output stream del hijo
    private DataOutputStream childStdout;

    /**
     * 
     * Se le pasa un process builder del cual iniciará para poder iniciar el proceso.
     * 
     * @param builder el builder para iniciar el proceso.
     * @throws Exception si no se puede iniciar el proceso.
     */
    public Mayusculas_carlos_pomares(ProcessBuilder builder) throws Exception {

        // Iniciamos el scanner del usuario.
        this.userIn = new Scanner(System.in);

        // Iniciamos el proceso hijo.
        try {
            this.childProcess = builder.start();
            if (!this.childProcess.isAlive()) throw new RuntimeException("El proceso ha acabado inesperadamente");
        } catch (IOException e) {
            // Si no se puede iniciar el proceso se devuelve una excepción.
            throw new Exception("No se ha podido iniciar el proceso hijo, dado que el comando no existe.");
        } catch (RuntimeException re) {
            // Si el proceso acaba antes de intentar interactuar devolvemos excepción.
            throw re;
        } catch (Exception ie) {
            // Excepción inesperada.
            throw ie;
        } 
        
        // Creamos los streams del proceso hijo.
        this.childStdin = new DataInputStream(this.childProcess.getInputStream());
        this.childStdout = new DataOutputStream(this.childProcess.getOutputStream());

    }

    /**
     * 
     * Bucle principal donde pedirá input al usuario y a través de los streams del proceso
     * hijo permitirá obtener el texto del input en mayúsculas y devolverlo por el output stream
     * del padre.
     * 
     * @throws Exception
     */
    public void mainLoop() throws Exception {

        // La linea a obtener del usuario.
        String line = null;
        // La bandera para saber cuando parar el proceso.
        boolean exit = false;

        // Información de uso básica.
        System.out.println("Para finalizar el programa de manera correcta, escriba la órden \"EOP\".");

        do {

            try {

                // Pedimos al usuario los datos de entrada.
                System.out.print("Dato de entrada: ");
                line = userIn.nextLine();

                // Comprobación de final de proceso.
                if (line.equals("EOP")) {
                    // Devolemos el código de salida del proceso hijo.
                    System.out.println(
                        String.format("Codigo de salida del proceso hijo: %d", this.safeExit())
                    );
                    // Ponemos la bandera a true para salir del bucle.
                    exit = true;
                    continue;
                }

                // Mandar el mensaje hacia el proceso hijo a través el input obtenido del usuario.
                sendMessage(line);

                // Comprobar si tenemos mensajes dispnible de parte del proceso hijo.
                if (this.childStdin.available() == 0) {
                    throw new Exception("No se ha obtenido respuesta del proceso hijo");
                }

            } catch (InterruptedException ie) {
                System.out.println(String.format("Forced interuption %s", ie.getMessage()));
                this.forceExit();
                throw new Exception("Finalización forzada");
            } catch (Exception e) {
                System.out.println(String.format("Error: %s", e.getMessage()));
                continue;
            }

            // Mostrar el mensaje del proceso hijo.
            System.out.println(this.childStdin.readUTF());

        }
        while (!exit);

    }

    /**
     * 
     * Permite mandar un mensaje al OutputStream del proceso hijo.
     * Se espera 1 segundo despues de mandar el mansaje para que el proceso padre tenga tiempo
     * a obtener la respuesta.
     * 
     * @param message el mensaje a mandar.
     * @throws Exception si no se puede mandar el mensaje.
     */
    private void sendMessage(String message) throws IOException, InterruptedException {
        this.childStdout.writeUTF(message);
        this.childStdout.flush();
        // Esperamos 1 segundo para dar tiempo al hijo a responder.
        Thread.sleep(500);
    }

    /**
     * 
     * Permite cerrar de manera segura los streams del proceso hijo y del padre.
     * 
     * Para parar el proceso hijo, se manda el mensaje de final de proceso al hijo.
     * 
     * @return Devolvemos el código de salida del hijo.
     * @throws Exception si no se pueden cerrar los streams del hijo.
     */
    private int safeExit() throws Exception {
        // Close user scanner.
        this.userIn.close();

        // Send end signal to child.
        this.childStdout.writeUTF("EOP");
        
        // Close child stdin stream.
        this.childStdin.close();
        // Close child stdout stream.
        this.childStdout.close();

        // Time to close child
        Thread.sleep(500);

        // If process is alive after sending exit signal destroy child.
        if (this.childProcess.isAlive()) {
            this.forceExit();
        }

        // Return child process code.
        return this.childProcess.exitValue();
    }

    /**
     * 
     * Permite forzar la finalización del proceso hijo.
     * 
     * @return el código del proceso hijo.
     * @throws Exception
     */
    private int forceExit() throws Exception {

        // Close user scanner.
        this.userIn.close();

        // Destroy child process.
        this.childProcess.destroy();

        return this.childProcess.waitFor();
    }

    public static void main(String[] args) {
        
        // Default command.
        String[] command = new String[]{
            "java",
            "-cp",
            Mayusculas_carlos_pomares.class.getProtectionDomain().getCodeSource().getLocation().getPath(),
            "Mayusculas_child_carlos_pomares",
        };

        // Si le pasan argumentos los usará como ejecutable como hijo.
        if (args.length > 0 ) {
            command = args;
        }

        // Iniciamos la aplicación.
        Mayusculas_carlos_pomares app = null;
        String fileName = String.format("./%s_%s.log","uppercase_log",LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE));

        // Creamos el process builder con el comando.
        ProcessBuilder pb = new ProcessBuilder(command);
        
        try {
            app = new Mayusculas_carlos_pomares(pb);
        } catch (Exception e) {
            System.out.println(String.format("%s, Guardando traza en %s", "No se ha podido iniciar la aplicación", fileName));
            try {
                PrintWriter wr = new PrintWriter(new File(fileName));
                e.printStackTrace(wr);
                wr.close();
            } catch (IOException io) {
                System.out.println("No se ha podido crear el archivo de log.");
            }
        }
        
        try {
            app.mainLoop();
        } catch (Exception e) {
            System.out.println(String.format("%s, Guardando traza en %s", "Ha habido un error crítico durante la ejecución", fileName));
            try {
                PrintWriter wr = new PrintWriter(new File(fileName));
                e.printStackTrace(wr);
                wr.close();
            } catch (IOException io) {
                System.out.println("No se ha podido crear el archivo de log.");
            }
        }

    }

}
