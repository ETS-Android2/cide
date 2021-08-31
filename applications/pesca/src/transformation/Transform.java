package transformation;

import common.data.Data;

import java.util.ArrayList;
import java.util.Arrays;

public class Transform {

    /* ======================================
        BYTE METHODS
     ====================================== */

    public static byte[] toPrimitive(char[] chars){
        byte[] output = new byte[chars.length];
        for (int i = 0; i < chars.length; i++) {
            output[i] = (byte) chars[i];
        }
        return output;
    }

    public static byte[] toPrimitive(Byte[] bytes){
        byte[] output = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            output[i] = bytes[i];
        }
        return output;
    }

    public static Byte[] toComplex(byte[] bytes){
        Byte[] output = new Byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            output[i] = bytes[i];
        }
        return output;
    }

    public static Byte[] toComplexFromChars(char[] bytes){
        Byte[] output = new Byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            output[i] = (byte) bytes[i];
        }
        return output;
    }

    /* ======================================
        DATA METHODS
     ====================================== */

    public static ArrayList<Data> toComplex(Data[] bytes){
        return new ArrayList<>(Arrays.asList(bytes));
    }

}
