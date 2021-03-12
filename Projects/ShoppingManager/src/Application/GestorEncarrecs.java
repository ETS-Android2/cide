package Application;

/*

    Project     Programming21
    Package     Application.Entities    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-03-12

    DESCRIPTION
    
*/

import Data.BDManager;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Carlos Pomares
 */

public class GestorEncarrecs {

    private BDManager gestor;
    private BufferedReader entrada;

    public GestorEncarrecs() throws Exception {
        gestor = new BDManager();
        entrada = new BufferedReader(new InputStreamReader(System.in));
    }

    // TODO Método de entrada de la aplicación por consola
    public void start(){}

    // TODO Menu principal
    private void menuPrincipal(){}

    // TODO Método genérico de entrada de pregunta

    // TODO Método genérico de muestra de pregunta

    // TODO Método buscar cliente, secuencia de preguntas...

    // TODO Método agregar cliente, secuencia de preguntas...

    public static void main(String[] args) throws Exception {
        GestorEncarrecs ge = new GestorEncarrecs();
        ge.start();
    }

}
