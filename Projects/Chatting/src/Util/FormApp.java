package Util;

/*

    Project     Programming21
    Package     Util    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-02-25

    DESCRIPTION
    
*/

import Services.Database;
import Services.ErrorWindow;

import java.sql.SQLException;

/**
 * @author Carlos Pomares
 */

public class FormApp {

    private static FormManager formManager;
    private static Database databaseManager;

    private FormApp(){}

    public static void init(String[] args){
        formManager = new FormManager();
        try {
            databaseManager = Database.init();
        } catch (SQLException e){
            ErrorWindow.run(e.getMessage());
        }
    }

    public static FormManager getFormManager() {
        return formManager;
    }

    public static Database getDatabaseManager() {
        return databaseManager;
    }
}
