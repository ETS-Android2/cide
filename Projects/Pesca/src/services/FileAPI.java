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
