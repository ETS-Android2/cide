package termux.Components.Menu;

/*

    Project     Programming21
    Package     Application.Services.Components.Menu
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-03-18

    DESCRIPTION
    
*/


import termux.Components.Command.CommandParser;
import termux.Components.Error.ErrorLog;
import termux.Components.Menu.DefaultInteractiveMenu;
import termux.Components.Menu.DefaultMenu;
import termux.Components.Menu.SelectionMenu;

import java.io.BufferedReader;

/**
 * @author Carlos Pomares
 */

public abstract class SelectionInteractiveMenu extends DefaultInteractiveMenu {

    protected SelectionMenu selectionMenu;

    public SelectionInteractiveMenu(ErrorLog e, DefaultMenu o, CommandParser p, BufferedReader r, String s, SelectionMenu selection) {
        super(e, o, p, r, s);
        this.selectionMenu = selection;
    }

    @Override
    protected void loopBlock() {
        selectionMenu.show();
        optionMenu.show();
    }

}
