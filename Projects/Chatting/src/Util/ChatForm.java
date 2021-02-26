package Util;

/*

    Project     Programming21
    Package     Screen    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-02-25

    DESCRIPTION
    
*/

import Util.Form;

import javax.swing.*;
import java.awt.*;

/**
 * @author Carlos Pomares
 */

public abstract class ChatForm implements Form {

    protected JFrame root;

    public ChatForm(){
        root = new JFrame();
        root.setVisible(false);
        root.setResizable(false);
    }

    protected JFrame getRoot(){
        return this.root;
    }

    protected void show(){
        root.setVisible(true);
    }

    protected void hide(){
        root.setVisible(false);
    }

}
