package services;

import common.data.Data;
import common.data.Line;

import java.io.*;
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

    /* ======================================
        SEARCH METHODS
     ====================================== */

    protected boolean searchDataInFlow(InputStream stream,char delimiter,int numberOfData,int position,String toSearch) throws IOException {

        ArrayList<Byte> currentData = new ArrayList<>();
        int delimiterCount = 0;
        byte b;

        while((b = (byte) stream.read()) != -1){

            currentData.add(b);

            if(b == delimiter){
                delimiterCount++;
            }

            if (delimiterCount == (numberOfData + 1)){
                Byte[] flow = new Byte[currentData.size()];

                Data d = Line.getDataInParsedLine(currentData.toArray(flow),delimiter,position);

                delimiterCount = 0;
                currentData.clear();

                if(d.getStringValue().equals(toSearch)){
                    return true;
                }

            }

        }

        return false;
    }

    protected boolean searchDataInFlow(InputStream stream,char delimiter,int numberOfData,int position,Integer toSearch) throws IOException {

        ArrayList<Byte> currentData = new ArrayList<>();
        int delimiterCount = 0;
        byte b;

        while((b = (byte) stream.read()) != -1){

            currentData.add(b);

            if(b == delimiter){
                delimiterCount++;
            }

            if (delimiterCount == (numberOfData + 1)){
                Byte[] flow = new Byte[currentData.size()];

                Data d = Line.getDataInParsedLine(currentData.toArray(flow),delimiter,position);

                delimiterCount = 0;
                currentData.clear();

                if(d.getIntValue() == toSearch){
                    return true;
                }

            }

        }

        return false;
    }

    protected boolean searchDataInFlow(InputStream stream,char delimiter,int numberOfData,int position,Float toSearch) throws IOException {

        ArrayList<Byte> currentData = new ArrayList<>();
        int delimiterCount = 0;
        byte b;

        while((b = (byte) stream.read()) != -1){

            currentData.add(b);

            if(b == delimiter){
                delimiterCount++;
            }

            if (delimiterCount == (numberOfData + 1)){
                Byte[] flow = new Byte[currentData.size()];

                Data d = Line.getDataInParsedLine(currentData.toArray(flow),delimiter,position);

                delimiterCount = 0;
                currentData.clear();

                if(d.getFloatValue() == toSearch){
                    return true;
                }

            }

        }

        return false;
    }

    /* ======================================
        POSITION METHODS
     ====================================== */

    protected int getLinePositionInFlow(InputStream stream,char delimiter,int numberOfData,int position,String toSearch) throws IOException {

        ArrayList<Byte> currentData = new ArrayList<>();
        int delimiterCount = 0;
        int lineCount = 0;
        byte b;

        while((b = (byte) stream.read()) != -1){

            currentData.add(b);

            if(b == delimiter){
                delimiterCount++;
            }

            if (delimiterCount == (numberOfData + 1)){
                Byte[] flow = new Byte[currentData.size()];

                Data d = Line.getDataInParsedLine(currentData.toArray(flow),delimiter,position);

                delimiterCount = 0;
                currentData.clear();

                if(d.getStringValue().equals(toSearch)){
                    return lineCount;
                }

                lineCount++;

            }

        }

        return -1;
    }

    protected int getLinePositionInFlow(InputStream stream,char delimiter,int numberOfData,int position,Integer toSearch) throws IOException {

        ArrayList<Byte> currentData = new ArrayList<>();
        int delimiterCount = 0;
        int lineCount = 0;
        byte b;

        while((b = (byte) stream.read()) != -1){

            currentData.add(b);

            if(b == delimiter){
                delimiterCount++;
            }

            if (delimiterCount == (numberOfData + 1)){
                Byte[] flow = new Byte[currentData.size()];

                Data d = Line.getDataInParsedLine(currentData.toArray(flow),delimiter,position);

                delimiterCount = 0;
                currentData.clear();

                if(d.getIntValue() == toSearch){
                    return lineCount;
                }

                lineCount++;

            }

        }

        return -1;
    }

    protected int getLinePositionInFlow(InputStream stream,char delimiter,int numberOfData,int position,Float toSearch) throws IOException {

        ArrayList<Byte> currentData = new ArrayList<>();
        int delimiterCount = 0;
        int lineCount = 0;
        byte b;

        while((b = (byte) stream.read()) != -1){

            currentData.add(b);

            if(b == delimiter){
                delimiterCount++;
            }

            if (delimiterCount == (numberOfData + 1)){
                Byte[] flow = new Byte[currentData.size()];

                Data d = Line.getDataInParsedLine(currentData.toArray(flow),delimiter,position);

                delimiterCount = 0;
                currentData.clear();

                if(d.getFloatValue() == toSearch){
                    return lineCount;
                }

                lineCount++;

            }

        }

        return -1;
    }

    /* ======================================
        GET METHODS
     ====================================== */

    protected Line getLineDataInFlow(InputStream stream,char delimiter,int numberOfData,int position) throws IOException {

        ArrayList<Byte> currentData = new ArrayList<>();
        int delimiterCount = 0;
        int lineCount = 0;
        byte b;

        while((b = (byte) stream.read()) != -1){

            currentData.add(b);

            if(b == delimiter){
                delimiterCount++;
            }

            if (delimiterCount == (numberOfData + 1)){
                Byte[] flow = new Byte[currentData.size()];

                Line l = new Line(currentData.toArray(flow),delimiter);

                delimiterCount = 0;
                currentData.clear();

                if(lineCount == position - 1){
                    return l;
                }

                lineCount++;

            }

        }

        return null;
    }

    protected Data getDataInLinePosition(InputStream stream,char delimiter,int numberOfData,int position,int dataPosition) throws IOException {

        ArrayList<Byte> currentData = new ArrayList<>();
        int delimiterCount = 0;
        int lineCount = 0;
        byte b;

        while((b = (byte) stream.read()) != -1){

            currentData.add(b);

            if(b == delimiter){
                delimiterCount++;
            }

            if (delimiterCount == (numberOfData + 1)){
                Byte[] flow = new Byte[currentData.size()];

                delimiterCount = 0;

                if(lineCount == position){
                    return Line.getDataInParsedLine(currentData.toArray(flow),delimiter,dataPosition);
                }

                currentData.clear();

                lineCount++;

            }


        }

        return null;
    }

    /* ======================================
        OPERATION METHODS
     ====================================== */

    protected int getLineWhereCondition(InputStream stream,char delimiter,int numberOfData,int position,Integer initial, DataOperation operation) throws IOException {

        ArrayList<Byte> currentData = new ArrayList<>();
        int delimiterCount = 0;
        int lineCount = 0;
        byte b;

        int line = -1;

        while((b = (byte) stream.read()) != -1){

            currentData.add(b);

            if(b == delimiter){
                delimiterCount++;
            }

            if (delimiterCount == (numberOfData + 1)){

                Byte[] flow = new Byte[currentData.size()];

                Data d = Line.getDataInParsedLine(currentData.toArray(flow),delimiter,position);

                delimiterCount = 0;
                currentData.clear();

                switch (operation){
                    case EQUAL:
                        if(d.getIntValue() == initial)
                            return lineCount;
                    case LOWER:
                        if(d.getIntValue() < initial)
                            line = lineCount;
                    case HIGHER:
                        if(d.getIntValue() > initial)
                            line = lineCount;
                    case LOWER_OR_EQUAL:
                        if(d.getIntValue() <= initial)
                            line = lineCount;
                    case HIGHER_OR_EQUAL:
                        if(d.getIntValue() >= initial)
                            line = lineCount;
                    default:
                        throw new IOException("Operation not supplied.");
                }

            }

            lineCount++;

        }

        return line;
    }

    protected int getLineWhereCondition(InputStream stream,char delimiter,int numberOfData,int position,Float initial, DataOperation operation) throws IOException {

        ArrayList<Byte> currentData = new ArrayList<>();
        int delimiterCount = 0;
        int lineCount = 0;
        byte b;

        int line = -1;

        while((b = (byte) stream.read()) != -1){

            currentData.add(b);

            if(b == delimiter){
                delimiterCount++;
            }

            if (delimiterCount == (numberOfData + 1)){

                Byte[] flow = new Byte[currentData.size()];

                Data d = Line.getDataInParsedLine(currentData.toArray(flow),delimiter,position);

                delimiterCount = 0;
                currentData.clear();

                switch (operation){
                    case EQUAL:
                        if(d.getFloatValue() == initial)
                            return lineCount;
                        break;
                    case LOWER:
                        if(initial < d.getFloatValue())
                            line = lineCount;
                        break;
                    case HIGHER:
                        if(initial > d.getFloatValue())
                            line = lineCount;
                        break;
                    case LOWER_OR_EQUAL:
                        if(initial <= d.getFloatValue())
                            line = lineCount;
                        break;
                    case HIGHER_OR_EQUAL:
                        if(initial >= d.getFloatValue())
                            line = lineCount;
                        break;
                    default:
                        throw new IOException("Operation not supplied.");
                }

                lineCount++;

            }

        }

        return line;
    }

}
