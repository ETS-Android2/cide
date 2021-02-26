package Util;

/*

    Project     Programming21
    Package     Util    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-02-25

    DESCRIPTION
    
*/

import java.io.InputStream;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;

/**
 * @author Carlos Pomares
 */

public class MD5 {

    // https://www.geeksforgeeks.org/md5-hash-in-java/

    public static String getMD5(String input)
            throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(input.getBytes());
        BigInteger no = new BigInteger(1,messageDigest);
        String hashText = no.toString(16);
        while(hashText.length() < 32){
            hashText = "0" + hashText;
        }
        return hashText;
    }

}
