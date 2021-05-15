package services;

import common.data.Data;
import common.data.Line;
import common.specification.User;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class PescaAPI extends FileAPI {

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

        boolean flow;
        do {
             flow = establishDataFlows();
        } while (!flow);

    }

    private boolean establishDataFlows() throws IOException {

        try {
            this.usersIn = new FileInputStream(getClass().getResource("/flow/users.txt").getFile());
            this.usersOut = new FileOutputStream(getClass().getResource("/flow/users.txt").getFile());
        } catch (FileNotFoundException e){
            createFileEmpty(System.getProperty("file.separator") + "flow","users.txt");
            return false;
        }

        try {
            this.boatsIn = new FileInputStream(getClass().getResource("/flow/boats.txt").getFile());
            this.boatsOut = new FileOutputStream(getClass().getResource("/flow/boats.txt").getFile());
        } catch (FileNotFoundException e){
            createFileEmpty(System.getProperty("file.separator") + "flow","boats.txt");
            return false;
        }

        try {
            this.registersIn = new FileInputStream(getClass().getResource("/flow/registers.txt").getFile());
            this.registersOut = new FileOutputStream(getClass().getResource("/flow/registers.txt").getFile());
        } catch (FileNotFoundException e){
            createFileEmpty(System.getProperty("file.separator") + "flow","registers.txt");
            return false;
        }

        return true;
    }

    private void createFileEmpty(String bucket, String key) throws IOException {

        ClassLoader loader = ClassLoader.getSystemClassLoader();
        URL resource = loader.getResource(bucket);

        FileOutputStream outputStream = new FileOutputStream(resource.getFile() + System.getProperty("file.separator") + key);
        outputStream.write(' ');
        outputStream.close();

    }

    private String parseKey(String bucket,String key){
        return bucket + System.getProperty("file.separator") + key;
    }

    private void closeFlows() throws IOException {
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
