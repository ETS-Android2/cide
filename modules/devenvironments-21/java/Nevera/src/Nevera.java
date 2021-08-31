/*

    Project     ED2021
        
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-02-18

    DESCRIPTION
    
*/

import java.util.Collection;
import java.util.HashSet;

/**
 * @author Carlos Pomares
 */

public class Nevera {

    private Collection<String> items = new HashSet<String>();

    public boolean put(String item){
        return this.items.add(item);
    }

    public boolean contains(String item){
        return this.items.contains(item);
    }

    public boolean take(String item) throws NoSuchItemException {
        if(!this.items.contains(item)){
            throw new NoSuchItemException(item + " no esta en la nevera");
        }
        return this.items.remove(item);
    }

}
