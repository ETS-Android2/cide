package Application.Services.Console.Components.Menu;

/*

    Project     Programming21
    Package     Application.Services.Console.Components.Menu    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-03-18

    DESCRIPTION
    
*/

import Application.Services.Console.Components.Command.CommandParser;
import Application.Services.Console.Components.Error.ErrorLog;

import java.io.BufferedReader;

/**
 * @author Carlos Pomares
 */

public class OrderInteractiveMenu extends DefaultInteractiveMenu {

    public OrderInteractiveMenu(ErrorLog e, OptionMenu o, CommandParser p, BufferedReader r, String s) {
        super(e, o, p, r, s);
    }

    @Override
    protected void outsideLoop() {

    }

    @Override
    protected void loopBlock() {

    }

}
