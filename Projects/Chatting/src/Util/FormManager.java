package Util;

/*

    Project     Programming21
    Package     Util    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-02-25

    DESCRIPTION
    
*/

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Carlos Pomares
 */

public class FormManager {

    private ChatForm currentForm;

    private void loadForm(){
        getCurrentForm().run();
    }

    private void setCurrentForm(ChatForm form){
        this.currentForm = form;
    }

    public ChatForm getCurrentForm() {
        return currentForm;
    }

    public static void changeForm(FormManager formManager, ChatForm chatForm){
        formManager.setCurrentForm(chatForm);
        formManager.loadForm();
    }

}
