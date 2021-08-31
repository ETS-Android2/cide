import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;


/*

    Project     ED2021
        
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-02-18

    DESCRIPTION
    
*/

/**
 * @author Carlos Pomares
 */

class NeveraTest {

    @Test
    public void testNevera() throws NoSuchItemException {
        Nevera n = new Nevera();

        String[] items = new String[]{
            "queso",
            "manzana",
            "pollo",
            "pizza"
        };

        for(String item : items){
            n.put(item);
            assertTrue(n.contains(item));
            assertTrue(n.take(item));
        }

    }

}