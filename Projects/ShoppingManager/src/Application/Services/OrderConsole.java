package Application.Services;

/*

    Project     Programming21
    Package     Application.Services.Console    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-03-15

    DESCRIPTION
    
*/

import Application.Persistent.DatabaseDriver;
import Application.Services.Console.ConsoleUI;

import java.io.BufferedReader;
import java.sql.DriverManager;

/**
 * @author Carlos Pomares
 */

public class OrderConsole extends ConsoleUI {

    private DatabaseDriver datasource;

    public OrderConsole(BufferedReader user,DatabaseDriver databaseDriver) {
        super(user);
        this.datasource = databaseDriver;
    }

    @Override
    protected void update() throws Exception {

    }

    // TODO Método de entrada de la aplicación por consola

    // TODO Menu principal

    // TODO Método genérico de entrada de pregunta

    // TODO Método genérico de muestra de pregunta

    // TODO Método buscar cliente, secuencia de preguntas...

    // TODO Método agregar cliente, secuencia de preguntas...

}
