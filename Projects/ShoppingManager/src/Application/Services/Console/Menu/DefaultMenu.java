package Application.Services.Console.Menu;

/*

    Project     Programming21
    Package     Application.Services.Console.Menu    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-03-15

    DESCRIPTION
    
*/

/**
 * @author Carlos Pomares
 */

public abstract class DefaultMenu implements Menu {

    protected String[] options;

    public DefaultMenu(String[] o) {
        this.options = o;
    }

}
