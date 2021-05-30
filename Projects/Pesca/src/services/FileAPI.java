package services;

import common.data.Data;
import common.data.Line;
import transformation.Transform;

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
    protected OutputStream execute(String key) throws IOException {
        return new FileOutputStream(key);
    }

    /**
     *
     * Returns a FileOutputStream with the specified path.
     *
     * @param key the path to a file.
     * @return FileOutputStream of the key.
     * @throws IOException if something of the Input/Output fails.
     */
    protected OutputStream execute(String key, boolean append) throws IOException {
        return new FileOutputStream(key,append);
    }

    /**
     *
     * Returns a FileInputStream with the specified path.
     *
     * @param key the path to a file.
     * @return FileInputStream of the path.
     * @throws IOException if something of the Input/Output fails.
     */
    protected InputStream read(String key) throws IOException {
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
            File f = new File(parseKey(bucket,key));
            f.createNewFile();
        } catch (IOException e){
            createBucket(bucket);
            createFileEmpty(bucket,key);
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
     * @param delimiter the delimiter to separate the information.
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
     * Split the lines in the file by the number of items specified, doesnt detect lines.
     * When detect the number of items specified reading byte per byte, converts that flow of data
     * into a Line class.
     *
     * @param stream the data stream to manipulate.
     * @param delimiter the delimiter to separate the information.
     * @param numberOfData the number of items that contains one line.
     * @return an array of Lines.
     */
    protected ArrayList<Line> parseLines(InputStream stream,char delimiter,int numberOfData) throws IOException {
        ArrayList<Line> output = new ArrayList<>();
        ArrayList<Byte> currentData = new ArrayList<>();
        int delimiterCount = 0;
        byte b;
        while((b = (byte) stream.read()) != -1){
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

    /**
     *
     * Search string by desired position of data in all lines that contains a file.
     *
     * @param stream to read from.
     * @param delimiter to separate the data
     * @param numberOfData items that contains one line.
     * @param position of the item to search.
     * @param toSearch the string to search.
     * @return if the string exists in the file.
     * @throws IOException if something of the Input/Output fails.
     */
    protected boolean searchDataInFlow(InputStream stream,char delimiter,int numberOfData,int position,String toSearch) throws IOException {

        String currentData = "";
        int delimiterCount = 0;
        byte b;

        while((b = (byte) stream.read()) != -1){

            currentData += (char) b;

            if(b == delimiter){
                delimiterCount++;
            }

            if (delimiterCount == (numberOfData + 1)){

                Data d = Line.getDataInParsedLine(Transform.toComplex(currentData.getBytes()),delimiter,position);

                delimiterCount = 0;
                currentData = "";

                if(d.getStringValue().equals(toSearch)){
                    stream.close();
                    return true;
                }

            }

        }

        stream.close();
        return false;
    }

    /**
     *
     * Search float by desired position of data in all lines that contains a file.
     *
     * @param stream to read from.
     * @param delimiter to separate the data
     * @param numberOfData items that contains one line.
     * @param position of the item to search.
     * @param toSearch the float to search.
     * @return if the float exists in the file.
     * @throws IOException if something of the Input/Output fails.
     */
    protected boolean searchDataInFlow(InputStream stream,char delimiter,int numberOfData,int position,Float toSearch) throws IOException {

        String currentData = "";
        int delimiterCount = 0;
        byte b;

        while((b = (byte) stream.read()) != -1){

            currentData += (char) b;

            if(b == delimiter){
                delimiterCount++;
            }

            if (delimiterCount == (numberOfData + 1)){
                Data d = Line.getDataInParsedLine(Transform.toComplex(currentData.getBytes()),delimiter,position);

                delimiterCount = 0;
                currentData = "";

                if(d.getFloatValue() == toSearch){
                    stream.close();
                    return true;
                }

            }

        }

        stream.close();
        return false;
    }

    /* ======================================
        POSITION METHODS
     ====================================== */

    /**
     *
     * Search string and get the line in the file that contains the search result.
     *
     * @param stream to read from.
     * @param delimiter to separate the data
     * @param numberOfData items that contains one line.
     * @param position of the item to search.
     * @param toSearch the string to search.
     * @return if the string exists in the file.
     * @throws IOException if something of the Input/Output fails.
     */
    protected int getLinePositionInFlow(InputStream stream,char delimiter,int numberOfData,int position,String toSearch) throws IOException {

        String currentData = "";
        int delimiterCount = 0;
        int lineCount = 0;
        byte b;

        while((b = (byte) stream.read()) != -1){

            currentData += (char) b;

            if(b == delimiter){
                delimiterCount++;
            }

            if (delimiterCount == (numberOfData + 1)){

                Data d = Line.getDataInParsedLine(Transform.toComplex(currentData.getBytes()),delimiter,position);

                delimiterCount = 0;
                currentData = "";

                if(d.getStringValue().equals(toSearch)){
                    stream.close();
                    return lineCount;
                }

                lineCount++;

            }

        }

        stream.close();
        throw new IOException("Line not found.");
    }

    /* ======================================
        GET METHODS
     ====================================== */


    /**
     *
     * Get line object by passing the line number in the file to read.
     *
     * @param stream to read from.
     * @param delimiter to separate the data
     * @param numberOfData items that contains one line.
     * @param position of the line.
     * @return a line object containing all the data.
     * @throws IOException if something of the Input/Output fails.
     */
    protected Line getLineDataInFlow(InputStream stream,char delimiter,int numberOfData,int position) throws IOException {

        String currentData = "";
        int delimiterCount = 0;
        int lineCount = 0;
        byte b;

        while((b = (byte) stream.read()) != -1){

            currentData += (char) b;

            if(b == delimiter){
                delimiterCount++;
            }

            if (delimiterCount == (numberOfData + 1)){
                Line l = new Line(Transform.toComplex(currentData.getBytes()),delimiter);

                delimiterCount = 0;
                currentData = "";

                if(lineCount == position){
                    stream.close();
                    return l;
                }

                lineCount++;

            }

        }

        stream.close();
        return null;
    }

    /**
     *
     * Get data object by passing the line number and the data position in the file to read.
     *
     * @param stream to read from.
     * @param delimiter to separate the data
     * @param numberOfData items that contains one line.
     * @param position of the line.
     * @return a data object.
     * @throws IOException if something of the Input/Output fails.
     */
    protected Data getDataInLinePosition(InputStream stream,char delimiter,int numberOfData,int position,int dataPosition) throws IOException {

        String currentData = "";
        int delimiterCount = 0;
        int lineCount = 0;
        byte b;

        while((b = (byte) stream.read()) != -1){

            currentData += (char) b;

            if(b == delimiter){
                delimiterCount++;
            }

            if (delimiterCount == (numberOfData + 1)){

                delimiterCount = 0;

                if(lineCount == position){
                    stream.close();
                    return Line.getDataInParsedLine(Transform.toComplex(currentData.getBytes()),delimiter,dataPosition);
                }


                lineCount++;

            }


        }

        stream.close();
        return null;
    }

    /* ======================================
        OPERATION METHODS
     ====================================== */

    /**
     *
     * Get a line position by an integer where apply a condition with the DataOperation enum.
     *
     * @param stream to read from.
     * @param delimiter to separate the data
     * @param numberOfData items that contains one line.
     * @param position of the data to compare.
     * @param initial value of data to compare.
     * @param operation to make on comparison.
     * @return the line number in the file that applies the condition. If not find something returns -1.
     * @throws IOException if something of the Input/Output fails.
     */
    protected int getLineWhereCondition(InputStream stream,char delimiter,int numberOfData,int position,Integer initial, DataOperation operation) throws IOException {

        String currentData = "";
        int delimiterCount = 0;
        int lineCount = 0;
        byte b;

        int line = -1;

        while((b = (byte) stream.read()) != -1){

            currentData += (char) b;

            if(b == delimiter){
                delimiterCount++;
            }

            if (delimiterCount == (numberOfData + 1)){

                Data d = Line.getDataInParsedLine(Transform.toComplex(currentData.getBytes()),delimiter,position);

                delimiterCount = 0;
                currentData = "";

                switch (operation){
                    case EQUAL:
                        if(d.getIntValue() == initial)
                            stream.close();
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

        stream.close();
        return line;
    }

    /**
     *
     * Get a line position by float where apply a condition with the DataOperation enum.
     *
     * @param stream to read from.
     * @param delimiter to separate the data
     * @param numberOfData items that contains one line.
     * @param position of the data to compare.
     * @param initial value of data to compare.
     * @param operation to make on comparison.
     * @return the line number in the file that applies the condition. If not find something returns -1.
     * @throws IOException if something of the Input/Output fails.
     */
    protected int getLineWhereCondition(InputStream stream,char delimiter,int numberOfData,int position,Float initial, DataOperation operation) throws IOException {

        String currentData = "";
        int delimiterCount = 0;
        int lineCount = 0;
        byte b;

        int line = -1;

        while((b = (byte) stream.read()) != -1){

            // currentData.add(b);
            currentData += (char) b;

            if(b == delimiter){
                delimiterCount++;
            }

            if (delimiterCount == (numberOfData + 1)){

                Data d = Line.getDataInParsedLine(Transform.toComplex(currentData.getBytes()),delimiter,position);

                delimiterCount = 0;
                currentData = "";

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

    /* ======================================
        MANIPULATION METHODS
     ====================================== */

    /**
     *
     * Allows to write all containing data in a InputStream and write one more line passed by parameters.
     *
     * @param key the key of the file to read and write.
     * @param delimiter to separate the data
     * @param line to write.
     * @throws IOException if something of the Input/Output fails.
     */
    protected void appendData(String key,char delimiter, Line line) throws IOException {
        OutputStream outputStream = this.execute(key,true);
        outputStream.write(line.exportData(delimiter));
        outputStream.close();
    }

    /**
     *
     * Allows to remove data by line number.
     *
     * @param key the key of the file to read and write.
     * @param delimiter to separate the data
     * @param numberOfData the number of items that contains the file.
     * @param position of the line to remove.
     * @throws IOException if something of the Input/Output fails.
     */
    protected void removeData(String key,char delimiter,int numberOfData, int position) throws IOException {

        createFileEmpty("tmp","replace.txt");
        String replacedFile = parseKey("tmp","replace.txt");

        String currentData = "";
        int delimiterCount = 0;
        int lineCount = 0;
        byte b;

        InputStream inputStream = this.read(key);
        OutputStream outputStream = this.execute(replacedFile);

        while((b = (byte) inputStream.read()) != -1){

            currentData += (char) b;

            if(b == delimiter){
                delimiterCount++;
            }

            if (delimiterCount == (numberOfData + 1)){
                Line l = new Line(Transform.toComplex(currentData.getBytes()),delimiter);

                delimiterCount = 0;
                currentData = "";

                if(lineCount != position){
                    outputStream.write(l.exportData(delimiter));
                }

                lineCount++;

            }

        }

        inputStream.close();
        outputStream.close();

        replaceFile(replacedFile,key);

    }

    protected void replaceFile(String oldValue,String newValue) throws IOException {

        File f = new File(oldValue);
        File f2 = new File(newValue);

        if (!f.exists()){
            throw new IOException("File to replace dont exist.");
        }

        f2.createNewFile();

        InputStream input = read(oldValue);
        OutputStream output = execute(newValue);
        byte b;

        while ((b = (byte) input.read()) != -1){
            output.write(b);
        }

        input.close();
        output.close();

        if (!f.delete()) {
            throw new IOException("Old file cannot be deleted.");
        }

    }

    protected void copyFile(String oldValue, String newValue) throws IOException {

        File f = new File(oldValue);
        File f2 = new File(newValue);

        if (!f.exists()){
            throw new IOException("File to replace dont exists.");
        }

        f2.createNewFile();

        InputStream input = read(oldValue);
        OutputStream output = execute(newValue);
        byte b;

        while ((b = (byte) input.read()) != -1){
            output.write(b);
        }

        input.close();
        output.close();

    }

    protected void removeFile(String key) throws IOException {

        File f = new File(key);

        if (!f.exists()) {
            throw new IOException("File dont exist.");
        }

        if (!f.delete()) {
            throw new IOException("Old file cannot be deleted.");
        }

    }

}
