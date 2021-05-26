package common.specification;

import common.data.Line;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class StatisticResultSimple extends StatisticResult {

    public StatisticResultSimple(InputStream stream, char delimiter, int numberOfData, int flag, int data) throws IOException {
        this.fishCatches = new HashMap<>();
        this.fishSizes = new HashMap<>();
        parseStatistic(stream,delimiter,numberOfData,flag,data);
    }

    public StatisticResultSimple(InputStream stream, char delimiter, int numberOfData, int flag, int data, int identifier, String id) throws IOException {
        this.fishCatches = new HashMap<>();
        this.fishSizes = new HashMap<>();
        parseStatistic(stream,delimiter,numberOfData,flag,data,identifier,id);
    }

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

}