/*

    Project     Programming21
        
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-02-25

    DESCRIPTION
    
*/

import Screen.*;
import Util.ChatForm;
import Util.FormApp;
import Util.FormManager;

/**
 * @author Carlos Pomares
 */

public class Chatting {

    public static ChatForm loginForm = new LoginForm();
    public static ChatForm signupForm = new SignUpForm();
    public static ChatForm roomSelect = new RoomSelect();
    public static ChatForm roomInterface = new RoomUserInterface();
    public static ChatForm roomCreator = new RoomCreator();

    public static void main(String[] args) {
        FormApp.init(args);
        FormManager.changeForm(FormApp.getFormManager(),loginForm);
    }

}
