package Application;

/*

    Project     Programming21
    Package     Application.Entities    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-03-12

    DESCRIPTION
    
*/

import Application.Persistent.DatabaseDriver;
import Application.Services.OrderConsole;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Carlos Pomares
 */

public class GestorEncarrecs {

    final private DatabaseDriver driver;
    final private BufferedReader userIn;
    final private OrderConsole console;

    public GestorEncarrecs() throws Exception {
        driver = new DatabaseDriver();
        userIn = new BufferedReader(new InputStreamReader(System.in));
        console = new OrderConsole(userIn,driver);
    }

    public void start(){
        this.console.start();
    }

    public static void main(String[] args) throws Exception {
        GestorEncarrecs ge = new GestorEncarrecs();
        ge.start();
    }

}
