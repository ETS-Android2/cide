package services;

import common.data.Data;
import common.data.Line;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class FileAPI {

    protected byte[] getDataFromFile(String path) throws IOException {
        InputStream inputStream = new FileInputStream(new File(path));
        return inputStream.readAllBytes();
    }

    protected byte[] getDataFromFlow(InputStream stream) throws IOException {
        byte[] b = stream.readAllBytes();
        stream.close();
        return b;
    }

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

    protected ArrayList<Line> parseLinesArray(byte[] data,char delimiter,int positionOfIndex){
        ArrayList<Line> output = new ArrayList<>();
        ArrayList<Byte> currentData = new ArrayList<>();

        int delimiterCount = 0;

        ArrayList<Byte> indexData = new ArrayList<>();
        Integer numberOfItems = null;

        for(byte b : data){

            currentData.add(b);

            if (b == delimiter){
                delimiterCount++;
            }

            if(delimiterCount == positionOfIndex){
                indexData.add(b);
            }

            if(delimiterCount == positionOfIndex + 1){
                Byte[] flow = new Byte[indexData.size()];
                numberOfItems = Integer.parseInt(parseRaw(indexData.toArray(flow)).replaceAll("#",""));
            }

            if (numberOfItems != null && delimiterCount == (2 + numberOfItems + 1)){
                Byte[] flow = new Byte[currentData.size()];
                output.add(new Line(currentData.toArray(flow),delimiter));
                delimiterCount = 0;
                numberOfItems = null;
                indexData.clear();
                currentData.clear();
            }

        }
        return output;
    }

    protected String parseRaw(Byte[] flow){
        String raw = "";
        for(byte c : flow){
            raw += (char) c;
        }
        return raw;
    }

}
