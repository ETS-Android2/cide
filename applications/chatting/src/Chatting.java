/*

    Project     Programming21
        
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-02-25

    DESCRIPTION
    
*/

import Util.Application;
import Util.FormManager;

/**
 * @author Carlos Pomares
 */

public class Chatting {

    public static void main(String[] args) {
        Application.init(args);
        FormManager.changeForm(Application.getFormManager(), Application.loginForm);
    }

}
