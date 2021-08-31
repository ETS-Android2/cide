package EjerciciosDeClase.Binary;

import java.io.*;
import java.nio.ByteBuffer;

public class Binary {

    private OutputStream outputStream;
    private InputStream inputStream;
    private int number = 40321;

    public void write(){
        try {
            this.outputStream = new BufferedOutputStream(new FileOutputStream("a.bin"));

            int n = number;
            int[] numbers = new int[(int)Math.floor((double)n / 255) + 1];
            int count = 0;



            while(n > 0){
                if(n > 255){
                    numbers[count] = 255;
                    n -= 255;
                } else {
                    numbers[count] = n;
                    n -= n;
                }
                count++;
            }

            for(int number : numbers){
                System.out.println(Integer.toBinaryString(number));
                outputStream.write(number);
            }

            outputStream.close();

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void read(){
        try {

            inputStream = new BufferedInputStream(new FileInputStream("a.bin"));

            byte[] bytes = inputStream.readAllBytes();

            int n = 0;

            for(byte b : bytes){
                int x = ((b << 32) & 0xFF);
                n += x;
            }

            System.out.println(n);

            inputStream.close();

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void write_2(){
        try {
            this.outputStream = new BufferedOutputStream(new FileOutputStream("a.bin"));

            for(int number : intToByte(number)){
                outputStream.write(number);
            }

            outputStream.close();

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void read_2(){
        try {

            inputStream = new BufferedInputStream(new FileInputStream("a.bin"));

            byte[] bytes = inputStream.readAllBytes();

            int n = bytesToInt(bytes);

            System.out.println(n);

            inputStream.close();

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void test(){

        int target = 1321;
        System.out.println(bytesToInt(intToByte(target)));
    }

    public byte[] intToByte(int value){

        int number = 0;
        int count = 0;

        while (number <= value){
            number = (int)Math.pow((double) 2,(double)count);
            count++;
        }

        System.out.println(count);

        int allocation = (count / 8) + 1;

        byte[] bytes = new byte[allocation];

        for (int i = 0; i < allocation; i++) {
            System.out.println(8 * i);
            bytes[i] = (byte)(value >>> (8*i));
        }

        return bytes;
    }

    public int bytesToInt(byte[] bytes){
        int count = 0;
        int result = 0;
        for (int i = 0; i < bytes.length; i++) {
            result += (bytes[i] << (8*count));
            count++;
        }
        return result;
    }

    public static void main(String[] args) {
        Binary bin = new Binary();
        //bin.write_2();
        bin.write();
        bin.read();
    }

}
