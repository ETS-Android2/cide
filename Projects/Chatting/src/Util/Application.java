package Util;

/*

    Project     Programming21
    Package     Util    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-02-25

    DESCRIPTION
    
*/

import Screen.*;
import Services.Database;
import Services.ErrorWindow;
import Services.RoomManager;

import java.sql.SQLException;

/**
 * @author Carlos Pomares
 */

public class Application {

    public static ChatForm loginForm = new LoginForm();
    public static ChatForm signupForm = new SignUpForm();
    public static ChatForm roomSelect = new RoomSelect();
    public static ChatForm roomInterface = new RoomUserInterface();
    public static ChatForm roomCreator = new RoomCreator();

    private static FormManager formManager;
    private static RoomManager roomManager;
    private static Database databaseManager;

    private Application(){}

    public static void init(String[] args){
        formManager = new FormManager();
        roomManager = new RoomManager();
        try {
            databaseManager = Database.init();
        } catch (Exception e){
            ErrorWindow.run(e.getMessage());
        }
    }

    public static FormManager getFormManager() {
        return formManager;
    }
    public static RoomManager getRoomManager(){
        return roomManager;
    }
    public static Database getDatabaseManager() {
        return databaseManager;
    }
}
