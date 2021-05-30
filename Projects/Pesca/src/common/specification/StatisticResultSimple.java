package common.specification;

import common.data.Line;
import transformation.Transform;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class StatisticResultSimple extends StatisticResult {

    private final String sizeKey = parseKey("tmp","sizes.txt");
    private final String catchKey = parseKey("tmp","catches.txt");

    /**
     *
     * Get the statistics result, by the stream iterates the file and parse the lines, then
     * allows to apply some statistics with the incoming data and over the line process the line
     * gets overwritten by the next line.
     *
     * @param stream the stream to read from.
     * @param delimiter the delimiter that separates the data.
     * @param numberOfData the number of data that contains each row.
     * @param flag the position of the flag.
     * @param data the position of the data value.
     * @throws IOException if something of the Input/Output fails.
     */
    public StatisticResultSimple(InputStream stream, char delimiter, int numberOfData, int flag, int data) throws IOException {
        parseStatisticFile(stream,delimiter,numberOfData,flag,data);
    }

    /**
     *
     * Get the statistics result, by the stream iterates the file and parse the lines, then
     * allows to apply some statistics with the incoming data and over the line process the line
     * gets overwritten by the next line. This constructor allows to filter by identifier that contains the
     * file.
     *
     * @param stream the stream to read from.
     * @param delimiter the delimiter that separates the data.
     * @param numberOfData the number of data that contains each row.
     * @param flag the position of the flag.
     * @param data the position of the data value.
     * @throws IOException if something of the Input/Output fails.
     */
    public StatisticResultSimple(InputStream stream, char delimiter, int numberOfData, int flag, int data, int identifier, String id) throws IOException {
        parseStatisticFile(stream,delimiter,numberOfData,flag,data,identifier,id);
    }

    public String getSizeKey() {
        return sizeKey;
    }

    public String getCatchKey() {
        return catchKey;
    }

    /**
     *
     * Detects if the temporary files exists, if exists deletes the files.
     * And create empty ones that are the (reset) action to avoid duplicate errors.
     *
     * @throws IOException If the files cannot be created or deleted.
     */
    protected void createStatisticsFiles() throws IOException {
        if (new File(sizeKey).exists())
            removeFile(parseKey("tmp","sizes.txt"));

        if (new File(catchKey).exists())
            removeFile(parseKey("tmp","catches.txt"));

        createFileEmpty("tmp","sizes.txt");
        createFileEmpty("tmp","catches.txt");
    }

    /**
     *
     * Creates the temporary files to handle the sizes and catches for the new statistics.
     * Parse lines at read and then apply some statistics to it.
     *
     * @param stream the stream to read from.
     * @param delimiter the delimiter that separates the data.
     * @param numberOfData the number of data that contains one row. (Real numbers, 4 items, 4 numberOfData)
     * @param flag the position of the flag. Starts from 0.
     * @param data the position of the data value. Starts from 0.
     * @throws IOException if something of the Input/Output fails.
     */
    protected void parseStatisticFile(InputStream stream, char delimiter, int numberOfData, int flag, int data) throws IOException {

        // Delete old files and create new.
        createStatisticsFiles();

        String currentData = "";
        int delimiterCount = 0;
        byte b;

        // Mean data, only float values.
        ArrayList<Float> meanData = new ArrayList<>();
        float average = 0;
        int lineCount = 0;

        while((b = (byte) stream.read()) != -1){

            currentData += (char) b;

            if(b == delimiter){
                delimiterCount++;
            }

            // Detects lines.
            if (delimiterCount == (numberOfData + 1)){

                // Parse the line and then get the data.
                Line l = new Line(Transform.toComplexFromChars(currentData.toCharArray()),delimiter);

                // Reset counters.
                delimiterCount = 0;
                currentData = "";

                String dataFlag = l.getData()[flag].getStringValue();
                float dataValue = l.getData()[data].getFloatValue();

                applyStatistics(dataFlag,dataValue,delimiter);

                average += dataValue;

                meanData.add(dataValue);

                lineCount++;

            }

        }

        // AVERAGE
        this.average = average / lineCount;

        // MEAN
        Collections.sort(meanData);

        if(meanData.size() > 1){
            this.mean = meanData.get((meanData.size() / 2) - 1) + meanData.get(meanData.size() / 2) / 2;
        }

        stream.close();

    }

    /**
     *
     * Creates the temporary files to handle the sizes and catches for the new statistics.
     * Parse lines at read and then apply some statistics to it. This method allows to filter
     * the incoming line by the identifier field.
     *
     * @param stream the stream to read from.
     * @param delimiter the delimiter that separates the data.
     * @param numberOfData the number of data that contains one row. (Real numbers, 4 items, 4 numberOfData)
     * @param flag the position of the flag. Starts from 0.
     * @param data the position of the data value. Starts from 0.
     * @param identifier the position of the identifier to filter by.
     * @param id the identifier to filter by.
     * @throws IOException if something of the Input/Output fails.
     */
    protected void parseStatisticFile(InputStream stream, char delimiter, int numberOfData, int flag, int data, int identifier, String id) throws IOException {

        // Delete old files and create new.
        createStatisticsFiles();

        String currentData = "";
        int delimiterCount = 0;
        byte b;

        // Mean data, only float values.
        ArrayList<Float> meanData = new ArrayList<>();
        float average = 0;
        int lineCount = 0;

        while((b = (byte) stream.read()) != -1){

            currentData += (char) b;

            if(b == delimiter){
                delimiterCount++;
            }

            // Detects lines.
            if (delimiterCount == (numberOfData + 1)){


                Line l = new Line(Transform.toComplex(currentData.getBytes()),delimiter);

                // Reset counters
                delimiterCount = 0;
                currentData = "";

                // Filter by identifier.
                if (l.getData()[identifier].getStringValue().equals(id)){

                    String dataFlag = l.getData()[flag].getStringValue();
                    float dataValue = l.getData()[data].getFloatValue();

                    applyStatistics(dataFlag,dataValue,delimiter);

                    average += dataValue;

                    meanData.add(dataValue);

                    lineCount++;

                }

            }

        }

        // AVERAGE
        this.average = average / lineCount;

        // MEAN
        Collections.sort(meanData);

        if(meanData.size() > 1){
            this.mean = meanData.get((meanData.size() / 2) - 1) + meanData.get(meanData.size() / 2) / 2;
        }

        stream.close();
    }

    /**
     *
     * Allows to modify the values of the current statistics and
     * append new data to the statistics data.
     *
     * @param dataFlag the incoming data flag.
     * @param dataValue the incoming data value.
     * @param delimiter the delimiter that separates the data.
     * @throws IOException if something of the Input/Output fails.
     */
    protected void applyStatistics(String dataFlag, Float dataValue,char delimiter) throws IOException {

        // MAX
        if(this.max < dataValue){
            this.max = dataValue;
        }

        // FIRST MIN
        if(this.min == 0){
            this.min = dataValue;
        }

        // MIN
        if(this.min > dataValue){
            this.min = dataValue;
        }

        // FISH SIZES
        Line sizeData = new Line(convertToBytes(dataFlag,dataValue,delimiter),delimiter);
        parseFishSize(sizeData,dataFlag,dataValue,sizeKey,delimiter);

        // FISH CATCHES
        parseFishCatch(dataFlag,catchKey,delimiter);

    }

    /**
     *
     * Allows to search a fish in a file.
     * If not exists append it to the file with the incoming size.
     * If exists and his size is lower than the incoming size, then
     * remove the old value and writes the new value to the file.
     *
     * @param l the line to export the data and write to the file.
     * @param dataFlag the flag to identify the fish.
     * @param dataValue the value of the current fish (size)
     * @param key the key of the file (path)
     * @param delimiter the delimiter that separates the data.
     * @throws IOException if something of the Input/Output fails.
     */
    protected void parseFishSize(Line l, String dataFlag, Float dataValue, String key, char delimiter) throws IOException {
        if (!searchDataInFlow(read(key),delimiter,2,0,dataFlag)){
            // Append first size to the flag (fish name)
            appendData(
                    key
                    ,delimiter
                    ,l
            );
        } else if (
                // If the fish exists in the file.
                searchDataInFlow(read(key),delimiter,2,0,dataFlag)
                        &&
                // Check the current size in the file with the incoming size.
                getDataInLinePosition(
                        read(key)
                        ,delimiter
                        ,2
                        ,getLinePositionInFlow(
                                read(key)
                                ,delimiter
                                ,2
                                ,0
                                ,dataFlag
                        )
                        ,1
                ).getFloatValue() > dataValue
        ) {

            // Remove the old data.
            removeData(
                    key
                    ,delimiter
                    ,2
                    ,getLinePositionInFlow(
                            read(key)
                            ,delimiter
                            ,2
                            ,0
                            ,dataFlag
                    )
            );

            // Append the new data with the incoming size.
            appendData(
                    key
                    ,delimiter
                    ,l
            );
        }
    }

    /**
     *
     * Allows to search a fish in a file.
     * If not exists append it to the file.
     * If exists, get his float value (the catches of the fish) and increment it by 1 and
     * then writes to the file.
     *
     * @param dataFlag the data flag the identifies the value.
     * @param key the key of the file (path)
     * @param delimiter the delimiter that separates the data.
     * @throws IOException if something of the Input/Output fails.
     */
    protected void parseFishCatch(String dataFlag, String key, char delimiter) throws IOException {
        if (!searchDataInFlow(read(key),delimiter,2,0,dataFlag))
        {
            // Append the new flag (fish name) with a 1f as default value for first catch.
            appendData(
                    key
                    ,delimiter
                    ,new Line(convertToBytes(dataFlag,1f,delimiter),delimiter)
            );
        }
        else if (searchDataInFlow(read(key),delimiter,2,0,dataFlag))
        {
            // Get the current count of the catches (1,2,3...)
            float count = getDataInLinePosition(
                    read(key)
                    ,delimiter
                    ,2
                    ,getLinePositionInFlow(
                            read(key)
                            ,delimiter
                            ,2
                            ,0
                            ,dataFlag
                    )
                    ,1
            ).getFloatValue();

            // Remove the old value
            removeData(
                    key
                    ,delimiter
                    ,2
                    ,getLinePositionInFlow(
                            read(key)
                            ,delimiter
                            ,2
                            ,0
                            ,dataFlag
                    )
            );

            // Append the new value with his count incremented.
            appendData(
                    key
                    ,delimiter
                    ,new Line(convertToBytes(dataFlag,count + 1,delimiter),delimiter)
            );
        }

    }

    /**
     *
     * Allows to convert a given flag and data value to a pattern like string and get the bytes.
     *
     * @param flag the position that contains the data flag in a row.
     * @param data the position that contains the data value in a row.
     * @param delimiter the delimiter that separates the data.
     * @return The bytes of the created string.
     */
    protected Byte[] convertToBytes(String flag, Float data,char delimiter){
        String output = delimiter + flag + delimiter + data + delimiter + '\n';
        return Transform.toComplex(output.getBytes());
    }

    /**
     *
     * Iterates the statistics files showing in the System.out the desired data. Specified with
     * the flag position and data position, can be formatted by the format parameter.
     *
     * @param bucket the bucket that contains the file. ('tmp','flow').
     * @param key the key of the file. ('sizes.txt','catches.txt')
     * @param delimiter the delimiter that separates the data.
     * @param numberOfData the number of data that contains one row.
     * @param flag the position that contains the data flag in a row. Stars with 0.
     * @param data the position that contains the data value in a row. Starts with 0.
     * @param format the format to show up the information.
     * @throws IOException if something of the Input/Output fails.
     */
    public void showStatisticsConsole(String bucket, String key, char delimiter, int numberOfData, int flag, int data, String format) throws IOException {

        InputStream stream = read(parseKey(bucket,key));

        String currentData = "";
        int delimiterCount = 0;
        byte b;

        while((b = (byte) stream.read()) != -1){

            currentData += (char) b;

            if(b == delimiter){
                delimiterCount++;
            }

            if (delimiterCount == (numberOfData + 1)){

                Line l = new Line(Transform.toComplexFromChars(currentData.toCharArray()),delimiter);

                delimiterCount = 0;
                currentData = "";

                String dataFlag = l.getData()[flag].getStringValue();
                Float dataValue = l.getData()[data].getFloatValue();

                System.out.printf(format,dataFlag,dataValue);

            }

        }

        stream.close();

    }

    /**
     *
     * SWING FEATURE
     * MAY BE IGNORED.
     *
     * Allows to export the data of a file in a hashmap to show up in Swing JLists.
     *
     * @param bucket the bucket that contains the file. ('tmp','flow')
     * @param key the key of the file. ('sizes.txt','catches.txt')
     * @param delimiter the delimiter that separates the data.
     * @param numberOfData the number of data that contains one row.
     * @param flag the position that contains the data flag in a row. Stars with 0.
     * @param data the position that contains the data value in a row. Starts with 0.
     * @return HashMap with String (dataFlag) and Float (dataValue).
     * @throws IOException if something of the Input/Output fails.
     */
    public void getStatisticsFromFile(HashMap<String,Float> input,String bucket, String key, char delimiter, int numberOfData,int flag, int data) throws IOException {

        InputStream stream = read(parseKey(bucket,key));

        String currentData = "";
        int delimiterCount = 0;
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

                String dataFlag = l.getData()[flag].getStringValue();
                Float dataValue = l.getData()[data].getFloatValue();

                input.put(dataFlag,dataValue);

            }

        }

        stream.close();
    }

}