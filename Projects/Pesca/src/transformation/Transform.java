package transformation;

import common.data.Data;

import java.util.ArrayList;
import java.util.Arrays;

public class Transform {

    /* ======================================
        BYTE METHODS
     ====================================== */

    /**
     *
     * Allows to convert primitive byte[] array to complex Byte[] array.
     *
     * @param bytes the input byte[] array.
     * @return a Byte[] array.
     */
    public static Byte[] toComplex(byte[] bytes){
        Byte[] output = new Byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            output[i] = bytes[i];
        }
        return output;
    }

    /**
     *
     * Allows to convert a char[] array to complex Byte[] array.
     *
     * @param bytes the input char[] array.
     * @return a Byte[] array of the input.
     */
    public static Byte[] toComplexFromChars(char[] bytes){
        Byte[] output = new Byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            output[i] = (byte) bytes[i];
        }
        return output;
    }

}
