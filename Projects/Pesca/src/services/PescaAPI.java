package services;

import common.data.Data;
import common.data.Line;
import common.specification.User;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class PescaAPI extends FileAPI {

    private final String pescaDirectory = "pesca";

    private InputStream florida;
    private InputStream mediterrania;

    private InputStream usersIn;
    private InputStream boatsIn;
    private InputStream registersIn;

    private OutputStream usersOut;
    private OutputStream boatsOut;
    private OutputStream registersOut;

    public PescaAPI() throws IOException {
        this.florida = new FileInputStream(getClass().getResource("/data/florida.txt").getFile());
        this.mediterrania = new FileInputStream(getClass().getResource("/data/mediterrania.txt").getFile());

        createFlowContainer();

        boolean flow;
        do {
             flow = establishDataFlows();
        } while (!flow);
    }

    private boolean establishDataFlows() throws IOException {

        try {
            this.usersIn = new FileInputStream(parseKey("flow","users.txt"));
            this.usersOut = new FileOutputStream(parseKey("flow","users.txt"));
        } catch (FileNotFoundException e){
            createFileEmpty("flow","users.txt");
            return false;
        }

        try {
            this.boatsIn = new FileInputStream(parseKey("flow","boats.txt"));
            this.boatsOut = new FileOutputStream(parseKey("flow","boats.txt"));
        } catch (FileNotFoundException e){
            createFileEmpty("flow","boats.txt");
            return false;
        }

        try {
            this.registersIn = new FileInputStream(parseKey("flow","registers.txt"));
            this.registersOut = new FileOutputStream(parseKey("flow","registers.txt"));
        } catch (FileNotFoundException e){
            createFileEmpty("flow","registers.txt");
            return false;
        }

        return true;
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

    public void closeFlows() throws IOException {
        florida.close();
        mediterrania.close();
        usersOut.close();
        usersIn.close();
        boatsOut.close();
        boatsIn.close();
        registersOut.close();
        registersIn.close();
    }

    /* ======================================
        USERS METHODS
     ====================================== */

    public boolean getUserByIdentifier(String user) throws IOException {
        byte[] raw = getDataFromFlow(this.usersIn);
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

        byte[] raw = getDataFromFlow(this.usersIn);
        ArrayList<Line> lines = parseLines(raw,'#',1);

        for (Line l : lines){
            this.usersOut.write(l.exportData('#'));
        }

        this.usersOut.write(toPrimitive(identifier.toCharArray()));

        this.usersOut.flush();
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
