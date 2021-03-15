package Application.Services.Console.Error;

/*

    Project     Programming21
    Package     Application.Services    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-03-15

    DESCRIPTION
    
*/

import java.util.ArrayList;

/**
 * @author Carlos Pomares
 */

public class ErrorLog {

    final private ArrayList<String> ERRORS;

    public ErrorLog() {
        ERRORS = new ArrayList<>();
    }

    public void log(String message){
        ERRORS.add(message);
    }

    public void clear(){
        ERRORS.clear();
    }

    public ArrayList<String> getERRORS(){
        return ERRORS;
    }

}
