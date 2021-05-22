package services;

import common.data.Data;
import common.data.Line;
import common.specification.Fish;
import common.specification.StatisticResult;
import common.specification.Statistics;
import common.specification.User;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

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

    public void registerUser(String identifier) throws Exception {

        if(getUserByIdentifier(identifier)){
            throw new Exception("User already exists.");
        }

        // Prepare data for file
        identifier = '#' + identifier + '#' + '\n';

        byte[] raw = getDataFromFlow(read(parseKey("flow","users.txt")));
        FileOutputStream outputStream = execute(parseKey("flow","users.txt"));
        ArrayList<Line> lines = parseLines(raw,'#',1);

        for (Line l : lines){
            outputStream.write(l.exportData('#'));
        }

        outputStream.write(toPrimitive(identifier.toCharArray()));
        outputStream.close();
    }

    public void deleteUser(String identifier) throws Exception {

        if (!getUserByIdentifier(identifier)){
            throw new Exception("User not exists.");
        }

        byte[] raw = getDataFromFlow(read(parseKey("flow","users.txt")));
        FileOutputStream outputStream = execute(parseKey("flow","users.txt"));
        ArrayList<Line> lines = parseLines(raw,'#',1);

        for(Line l : lines){
            if(!l.getData()[0].getStringValue().equals(identifier)){
                outputStream.write(l.exportData('#'));
            }
        }

        outputStream.close();
    }

    /* ======================================
        REGISTER METHODS
     ====================================== */

    public void registerNewAction(String user, Fish fish) throws IOException {

        // Prepare data for insert
        LocalDate date = LocalDate.now();
        user = '#' + user + '#' + fish.getName() + '#' + fish.getSize() + '#' + date + '#' + '\n';

        byte[] raw = getDataFromFlow(read(parseKey("flow","registers.txt")));
        FileOutputStream outputStream = execute(parseKey("flow","registers.txt"));
        ArrayList<Line> lines = parseLines(raw,'#',4);

        for (Line l : lines){
            outputStream.write(l.exportData('#'));
        }

        outputStream.write(toPrimitive(user.toCharArray()));
        outputStream.close();
    }

    /* ======================================
        FISH METHODS
     ====================================== */

    public Fish getFish(String key) throws IOException {

        byte[] raw = getDataFromFlow(read(key));
        ArrayList<Line> lines = parseLines(raw,'#',4);

        float random = (float) (Math.random() * 1.5f);
        Fish higherFish = null;

        for(Line l : lines){
            Fish f = new Fish(l.getData()[0],l.getData()[1],l.getData()[2],l.getData()[3]);
            if (random >= f.getPercentage()){
                higherFish = f;
            }
        }

        return higherFish;
    }

    /* ======================================
        STATISTICS METHODS
     ====================================== */

    public StatisticResult getStatistics() throws IOException {

        byte[] raw = getDataFromFlow(read(parseKey("flow","registers.txt")));
        ArrayList<Line> lines = parseLines(raw,'#',4);
        ArrayList<Statistics> statistics = new ArrayList<>();

        for(Line l : lines){
            statistics.add(new Statistics(l.getData()[1],l.getData()[3]));
        }

        return new StatisticResult(statistics);
    }

    public StatisticResult getStatistics(String user) throws IOException {

        byte[] raw = getDataFromFlow(read(parseKey("flow","registers.txt")));
        ArrayList<Line> lines = parseLines(raw,'#',4);
        ArrayList<Statistics> statistics = new ArrayList<>();

        for(Line l : lines){
            if(l.getData()[0].getStringValue().equals(user)){
                statistics.add(new Statistics(l.getData()[1],l.getData()[3]));
            }
        }

        return new StatisticResult(statistics);
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
