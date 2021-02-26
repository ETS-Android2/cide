/*

    Project     Programming21
        
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-02-25

    DESCRIPTION
    
*/

import Screen.*;
import Services.Database;
import Util.ChatForm;
import Util.FormApp;
import Util.FormManager;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Carlos Pomares
 */

public class Chatting {

    public static void main(String[] args) {
        FormApp.init(args);
        FormManager.changeForm(FormApp.getFormManager(),FormApp.loginForm);
    }

}
