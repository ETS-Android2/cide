package Utility;

/*

    Project     Programming21
    Package     Utilitats    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2020-12-09

    DESCRIPTION
    
*/

/**
 * @author Carlos Pomares
 */


public class ClearConsole {
    public static void clearScreen() throws Exception {
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            Runtime.getRuntime().exec("clear");
    }
}
