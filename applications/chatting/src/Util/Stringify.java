package Util;

/*

    Project     Programming21
    Package     Util    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-02-25

    DESCRIPTION
    
*/

/**
 * @author Carlos Pomares
 */

public class Stringify {

    public static String charsToString(char[] chars){
        StringBuilder output = new StringBuilder();
        for(char x : chars){
            output.append(x);
        }
        return output.toString();
    }

}
