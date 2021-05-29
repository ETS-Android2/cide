package common.specification;

import common.data.Data;
import common.data.Line;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class StatisticResultSimple extends StatisticResult {

    private final String sizeKey;
    private final String catchKey;

    public StatisticResultSimple(InputStream stream, char delimiter, int numberOfData, int flag, int data) throws IOException {
        sizeKey = parseKey("tmp","sizes.txt");
        catchKey = parseKey("tmp","catches.txt");
        parseStatisticFile(stream,delimiter,numberOfData,flag,data);
    }

    public StatisticResultSimple(InputStream stream, char delimiter, int numberOfData, int flag, int data, int identifier, String id) throws IOException {
        sizeKey = parseKey("tmp","sizes.txt");
        catchKey = parseKey("tmp","catches.txt");
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
     * Read all data and build the metrics in the same moment of parsing.
     *
     * @param stream to read from.
     * @param delimiter to separate the data.
     * @param numberOfData items in the line.
     * @param flag position in the data.
     * @param data position in the data.
     * @throws IOException if something of the Input/Output fails.
     */
    protected void parseStatistic(InputStream stream, char delimiter, int numberOfData, int flag, int data) throws IOException {

        ArrayList<Byte> currentData = new ArrayList<>();
        int delimiterCount = 0;
        byte b;

        ArrayList<Float> meanData = new ArrayList<>();
        float average = 0;
        int lineCount = 0;

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

                String dataFlag = l.getData()[flag].getStringValue();
                float dataValue = l.getData()[data].getFloatValue();

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
                if (!fishSizes.containsKey(dataFlag)){
                    fishSizes.put(dataFlag,dataValue);
                } else if(fishSizes.containsKey(dataFlag) && fishSizes.get(dataFlag) < dataValue){
                    fishSizes.replace(dataFlag,dataValue);
                }

                // FISH CATCHES
                if (!fishCatches.containsKey(dataFlag)){
                    fishCatches.put(dataFlag,1f);
                } else if(fishCatches.containsKey(dataFlag)){
                    fishCatches.replace(dataFlag,fishCatches.get(dataFlag) + 1);
                }

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

    }

    /**
     *
     * Read all data and build the metrics in the same moment of parsing, filtered by identifier.
     *
     * @param stream to read from.
     * @param delimiter to separate the data.
     * @param numberOfData items in the line.
     * @param flag position in the data.
     * @param data position in the data.
     * @param identifier position in the data.
     * @param id String to filter.
     * @throws IOException if something of the Input/Output fails.
     */
    protected void parseStatistic(InputStream stream, char delimiter, int numberOfData, int flag, int data, int identifier, String id) throws IOException {

        ArrayList<Byte> currentData = new ArrayList<>();
        int delimiterCount = 0;
        byte b;

        ArrayList<Float> meanData = new ArrayList<>();
        float average = 0;
        int lineCount = 0;

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

                if (l.getData()[identifier].getStringValue().equals(id)){

                    String dataFlag = l.getData()[flag].getStringValue();
                    float dataValue = l.getData()[data].getFloatValue();

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
                    if (!fishSizes.containsKey(dataFlag)){
                        fishSizes.put(dataFlag,dataValue);
                    } else if(fishSizes.containsKey(dataFlag) && fishSizes.get(dataFlag) < dataValue){
                        fishSizes.replace(dataFlag,dataValue);
                    }

                    // FISH CATCHES
                    if (!fishCatches.containsKey(dataFlag)){
                        fishCatches.put(dataFlag,1f);
                    } else if(fishCatches.containsKey(dataFlag)){
                        fishCatches.replace(dataFlag,fishCatches.get(dataFlag) + 1);
                    }

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

    }

    protected void parseStatisticFile(InputStream stream, char delimiter, int numberOfData, int flag, int data) throws IOException {

        if (new File(sizeKey).exists())
            removeFile(parseKey("tmp","sizes.txt"));

        if (new File(catchKey).exists())
            removeFile(parseKey("tmp","catches.txt"));

        createFileEmpty("tmp","sizes.txt");
        createFileEmpty("tmp","catches.txt");

        //ArrayList<Byte> currentData = new ArrayList<>();
        String currentData = "";
        int delimiterCount = 0;
        byte b;

        ArrayList<Float> meanData = new ArrayList<>();
        float average = 0;
        int lineCount = 0;

        while((b = (byte) stream.read()) != -1){

            //currentData.add(b);
            currentData += (char) b;

            if(b == delimiter){
                delimiterCount++;
            }

            if (delimiterCount == (numberOfData + 1)){

                //Byte[] flow = new Byte[currentData.size()];
                //Line l = new Line(currentData.toArray(flow),delimiter);

                Byte[] flow = new Byte[currentData.toCharArray().length];
                Line l = new Line(toComplexFromChars(currentData.toCharArray()),delimiter);

                delimiterCount = 0;
                // currentData.clear();
                currentData = "";

                String dataFlag = l.getData()[flag].getStringValue();
                float dataValue = l.getData()[data].getFloatValue();

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

    protected void parseStatisticFile(InputStream stream, char delimiter, int numberOfData, int flag, int data, int identifier, String id) throws IOException {

        removeFile(parseKey("tmp","sizes.txt"));
        removeFile(parseKey("tmp","catches.txt"));

        createFileEmpty("tmp","sizes.txt");
        createFileEmpty("tmp","catches.txt");

        ArrayList<Byte> currentData = new ArrayList<>();
        int delimiterCount = 0;
        byte b;

        ArrayList<Float> meanData = new ArrayList<>();
        float average = 0;
        int lineCount = 0;

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

                if (l.getData()[identifier].getStringValue().equals(id)){

                    String dataFlag = l.getData()[flag].getStringValue();
                    float dataValue = l.getData()[data].getFloatValue();

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
                    String sizeKey = parseKey("tmp","sizes.txt");

                    parseFishSize(sizeData,dataFlag,dataValue,sizeKey,delimiter);

                    // FISH CATCHES

                    String catchKey = parseKey("tmp","catches.txt");

                    parseFishCatch(dataFlag,catchKey,delimiter);

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

    }

    protected void parseFishSize(Line l, String dataFlag, Float dataValue, String key, char delimiter) throws IOException {
        if (!searchDataInFlow(read(key),delimiter,2,0,dataFlag)){
            appendData(
                    key
                    ,delimiter
                    ,l
            );
        } else if (
                searchDataInFlow(read(key),delimiter,2,0,dataFlag)
                        &&
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
                        ).getFloatValue() <= dataValue
        ) {
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
            appendData(
                    key
                    ,delimiter
                    ,l
            );
        }
    }

    protected void parseFishCatch(String dataFlag, String key, char delimiter) throws IOException {
        if (!searchDataInFlow(read(key),delimiter,2,0,dataFlag)){
            appendData(
                    key
                    ,delimiter
                    ,new Line(convertToBytes(dataFlag,1f,delimiter),delimiter)
            );
        } else if (
                searchDataInFlow(read(key),delimiter,2,0,dataFlag)
        ) {
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
            appendData(
                    key
                    ,delimiter
                    ,new Line(convertToBytes(dataFlag,count + 1,delimiter),delimiter)
            );
        }

    }

    protected Byte[] convertToBytes(String flag, Float data,char delimiter){
        String output = delimiter + flag + delimiter + data + delimiter + '\n';
        return toComplex(output.getBytes());
    }

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

                Line l = new Line(toComplexFromChars(currentData.toCharArray()),delimiter);

                delimiterCount = 0;
                currentData = "";

                String dataFlag = l.getData()[flag].getStringValue();
                Float dataValue = l.getData()[data].getFloatValue();

                System.out.printf(format,dataFlag,dataValue);

            }

        }

        stream.close();

    }

    public HashMap<String,Float> getStatisticsFromFile(String bucket, String key, char delimiter, int numberOfData,int flag, int data) throws IOException {

        HashMap<String,Float> output = new HashMap<>();

        InputStream stream = read(parseKey(bucket,key));

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
                Line l = new Line(currentData.toArray(flow),delimiter);

                delimiterCount = 0;
                currentData.clear();

                String dataFlag = l.getData()[flag].getStringValue();
                Float dataValue = l.getData()[data].getFloatValue();

                output.put(dataFlag,dataValue);

            }

        }

        stream.close();

        return output;
    }

}