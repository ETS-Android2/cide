package Application.Services.Console;

/*

    Project     Programming21
    Package     Application.Services    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-03-15

    DESCRIPTION
    
*/

import Application.Services.Console.Error.ErrorLog;

import java.io.BufferedReader;

/**
 * @author Carlos Pomares
 */

public abstract class ConsoleUI implements Console {

    protected boolean exit = false;
    protected boolean debug = false;

    protected BufferedReader userInput;
    protected ErrorLog errorLog;

    public ConsoleUI(BufferedReader user) {
        this.userInput = user;
        this.errorLog = new ErrorLog();
    }

    @Override
    public void start() {
        mainLoop();
    }

    private void mainLoop(){
        while(!exit){
            try {
                update();
            } catch (Exception e){
                errorLog.log(e.getMessage());
            }
        }
    }

    protected abstract void update() throws Exception;

}
