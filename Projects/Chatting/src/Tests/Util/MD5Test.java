package Util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


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

class MD5Test {

    @Test
    void getMD5() throws Exception {
        try {
            assertEquals("fc3ff98e8c6a0d3087d515c0473f8677",MD5.getMD5("hello world!"));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}