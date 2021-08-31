package Application.Services.Console.Components.Command;

/*

    Project     Programming21
    Package     Application.Services.Console.Components.Command    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-03-18

    DESCRIPTION
    
*/

import Application.Services.Console.OrderConsole;

/**
 * @author Carlos Pomares
 */

public abstract class OrderParser extends CommandParser {

    private static OrderConsole console;

    @Override
    protected int parseBlock(String command) throws Exception {
        switch (command.toLowerCase()){
            case "help":
                console.help();
                break;
            case "author":
                console.author();
                break;
            case "configure":
                console.configure();
                break;
            case "reset":
                console.reset();
                break;
            case "debug": case "test":
                throw new Exception("NOT IMPLEMENTED");
            default:
                return callBack(command.toLowerCase());
        }
        return 0;
    }

    protected abstract int callBack(String command) throws Exception;

    public static void setConsole(OrderConsole c){
        OrderParser.console = c;
    }

}
