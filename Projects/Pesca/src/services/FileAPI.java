package services;

import common.data.Data;
import common.data.Line;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class FileAPI {

    /* ======================================
        FLOW METHODS
     ====================================== */

    /**
     *
     * Returns a FileOutputStream with the specified path.
     *
     * @param key the path to a file.
     * @return FileOutputStream of the key.
     * @throws IOException if something of the Input/Output fails.
     */
    protected FileOutputStream execute(String key) throws IOException {
        return new FileOutputStream(key);
    }

    /**
     *
     * Returns a FileInputStream with the specified path.
     *
     * @param key the path to a file.
     * @return FileInputStream of the path.
     * @throws IOException if something of the Input/Output fails.
     */
    protected FileInputStream read(String key) throws IOException {
        return new FileInputStream(key);
    }

    /**
     *
     * Creates an empty file by the specified path.
     *
     * @param bucket the bucket to create the file.
     * @param key the key of the file.
     * @throws IOException if something of the Input/Output fails.
     */
    protected void createFileEmpty(String bucket, String key) throws IOException {
        try {
            FileOutputStream outputStream = execute(parseKey(bucket,key));
            outputStream.write(' ');
            outputStream.close();
        } catch (FileNotFoundException e){
            createBucket(bucket);
            createFileEmpty(bucket,key);
        } catch (IOException e){
            throw new IOException(e.getMessage());
        }
    }

    /**
     *
     * Creates an empty bucket by the given name.
     *
     * @param bucket the name of the bucket.
     * @return if the bucket is successfully created.
     */
    protected boolean createBucket(String bucket) {
        File path = new File(parseBucket(bucket));
        return path.mkdir();
    }

    /**
     *
     * Returns an absolute path of the key by given bucket and key.
     *
     * @param bucket the name of the bucket.
     * @param key the key.
     * @return an absolute path.
     */
    protected String parseKey(String bucket,String key){
        return parseBucket(bucket) + System.getProperty("file.separator") + key;
    }

    /**
     *
     * Return an absolute path of the bucket in the "pesca" directory.
     *
     * @param bucket the name of the bucket.
     * @return an absolute path.
     */
    protected String parseBucket(String bucket){
        return System.getProperty("user.home") + System.getProperty("file.separator") + "pesca" + System.getProperty("file.separator") + bucket;
    }

    /**
     *
     * Create the "pesca" directory if not exist.
     *
     * @return if the pesca directory exists/is created or cannot be created.
     */
    protected boolean createFlowContainer(){
        File path = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "pesca");
        boolean exists = path.exists();
        if(exists){
            return true;
        } else {
            return path.mkdir();
        }
    }

    /**
     *
     * The method that with an InputStream, read all the bytes and closes the stream.
     *
     * @param stream the stream to be read.
     * @return an array of bytes.
     * @throws IOException if something fails with the file.
     */
    protected byte[] getDataFromFlow(InputStream stream) throws IOException {
        byte[] b = stream.readAllBytes();
        stream.close();
        return b;
    }

    /* ======================================
        PARSE METHODS
     ====================================== */

    /**
     *
     * Split the lines in the file by the number of items specified, doesnt detect lines.
     * When detect the number of items specified reading byte per byte, converts that flow of data
     * into a Line class.
     *
     * @param data the data to parse.
     * @param delimiter the delimiter to separate the informtion.
     * @param numberOfData the number of items that contains one line.
     * @return an array of Lines.
     */
    protected ArrayList<Line> parseLines(byte[] data,char delimiter,int numberOfData){
        ArrayList<Line> output = new ArrayList<>();
        ArrayList<Byte> currentData = new ArrayList<>();
        int delimiterCount = 0;
        for(byte b : data){
            currentData.add(b);
            if (b == delimiter){
                delimiterCount++;
            }
            if (delimiterCount == (numberOfData + 1)){
                Byte[] flow = new Byte[currentData.size()];
                output.add(new Line(currentData.toArray(flow),delimiter));
                delimiterCount = 0;
                currentData.clear();
            }
        }
        return output;
    }


    /**
     *
     * Detect the lines without need to specify the number of items inside each line.
     * When detects the new line character, pack all the data inside a new Line.
     *
     * @param data the data to parse.
     * @param delimiter the delimiter to split.
     * @return an array of lines.
     */
    protected ArrayList<Line> parseLinesArray(byte[] data,char delimiter){
        ArrayList<Line> output = new ArrayList<>();
        ArrayList<Byte> currentData = new ArrayList<>();

        for(byte b : data){

            currentData.add(b);

            if (b == '\n'){
                Byte[] flow = new Byte[currentData.size()];
                output.add(new Line(currentData.toArray(flow),delimiter));
                currentData.clear();
            }

        }
        return output;
    }

}
