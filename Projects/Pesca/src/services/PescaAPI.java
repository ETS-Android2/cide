package services;

import common.data.Data;
import common.data.Line;
import common.specification.User;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class PescaAPI extends FileAPI {

    private final String pescaDirectory = "pesca";

    public PescaAPI() throws IOException {

        createFlowContainer();

        boolean flow;
        do {
            flow = establishDataFlows();
        } while (!flow);
    }

    private boolean establishDataFlows() throws IOException {

        try {
            File file = new File(parseKey("flow","users.txt"));
            if(!file.exists()){
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException e){
            createFileEmpty("flow","users.txt");
            return false;
        }

        try {
            File file = new File(parseKey("flow","boats.txt"));
            if(!file.exists()){
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException e){
            createFileEmpty("flow","boats.txt");
            return false;
        }

        try {
            File file = new File(parseKey("flow","registers.txt"));
            if(!file.exists()){
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException e){
            createFileEmpty("flow","registers.txt");
            return false;
        }

        return true;
    }

    private FileOutputStream execute(String key) throws IOException {
        return new FileOutputStream(key);
    }

    private FileInputStream read(String key) throws IOException {
        return new FileInputStream(key);
    }

    private void createFileEmpty(String bucket, String key) throws IOException {
        try {
            FileOutputStream outputStream = new FileOutputStream(parseKey(bucket,key));
            outputStream.write(' ');
            outputStream.close();
        } catch (FileNotFoundException e){
            createBucket(bucket);
            createFileEmpty(bucket,key);
        } catch (IOException e){
            throw new IOException(e.getMessage());
        }
    }

    private boolean createBucket(String bucket) {
        File path = new File(parseBucket(bucket));
        return path.mkdir();
    }

    private String parseKey(String bucket,String key){
        return parseBucket(bucket) + System.getProperty("file.separator") + key;
    }

    private String parseBucket(String bucket){
        return System.getProperty("user.home") + System.getProperty("file.separator") + pescaDirectory + System.getProperty("file.separator") + bucket;
    }

    private String parseBucket(String home, String bucket){
        return System.getProperty("user.home") + System.getProperty("file.separator") + home + System.getProperty("file.separator") + bucket;
    }

    private boolean createFlowContainer(){
        File path = new File(System.getProperty("user.home") + System.getProperty("file.separator") + pescaDirectory);
        boolean exists = path.exists();
        if(exists){
            return true;
        } else {
            return path.mkdir();
        }
    }

    /* ======================================
        USERS METHODS
     ====================================== */

    public boolean getUserByIdentifier(String user) throws IOException {
        byte[] raw = getDataFromFlow(read(parseKey("flow","users.txt")));
        ArrayList<Line> lines = parseLines(raw,'#',1);
        for (Line l : lines){
            if(l.getData()[0].getStringValue().equals(user)){
                return true;
            }
        }
        return false;
    }

    public void registerUser(String identifier) throws IOException {

        // Prepare data for file
        identifier = '#' + identifier + '#';

        byte[] raw = getDataFromFlow(read(parseKey("flow","users.txt")));
        FileOutputStream outputStream = execute(parseKey("flow","users.txt"));
        ArrayList<Line> lines = parseLines(raw,'#',1);

        for (Line l : lines){
            outputStream.write(l.exportData('#'));
        }

        outputStream.write(toPrimitive(identifier.toCharArray()));
        outputStream.close();
    }

    /* ======================================
        UTILITY METHODS
     ====================================== */

    public byte[] toPrimitive(char[] chars){
        byte[] output = new byte[chars.length];
        for (int i = 0; i < chars.length; i++) {
            output[i] = (byte) chars[i];
        }
        return output;
    }

}
