package Application.Services.Console.Components.Menu;

/*

    Project     Programming21
    Package     Application.Services.Console.Components    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-03-18

    DESCRIPTION
    
*/

import java.util.ArrayList;

/**
 * @author Carlos Pomares
 */

public abstract class DefaultMenu implements Menu {

    protected String[] options;
    protected String escapeCharacters;

    public DefaultMenu(String[] o, String e) {
        this.options = o;
        this.escapeCharacters = e;
    }

    public DefaultMenu(ArrayList<String> o, String e) {
        this.options = o.toArray(new String[0]);
        this.escapeCharacters = e;
    }

    protected abstract void update();
    
    @Override
    public void show() {
        update();
    }

}
