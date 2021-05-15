package services;

import common.data.Data;
import common.data.Line;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class FileAPI {

    public byte[] getDataFromFile(String path) throws IOException {
        InputStream inputStream = new FileInputStream(new File(path));
        return inputStream.readAllBytes();
    }

    public ArrayList<Line> parseLines(byte[] data,char delimiter,int numberOfData){
        ArrayList<Line> output = new ArrayList<>();
        ArrayList<Character> currentData = new ArrayList<>();
        int delimiterCount = 0;
        for(byte b : data){
            char x = (char) b;
            currentData.add(x);
            if (x == delimiter){
                delimiterCount++;
            }
            if (delimiterCount == (numberOfData + 1)){
                Character[] flow = new Character[currentData.size()];
                output.add(new Line(currentData.toArray(flow),delimiter));
                delimiterCount = 0;
                currentData.clear();
            }
        }
        return output;
    }

    public ArrayList<Line> parseLinesArray(byte[] data,char delimiter,int positionOfIndex){
        ArrayList<Line> output = new ArrayList<>();
        ArrayList<Character> currentData = new ArrayList<>();

        int delimiterCount = 0;

        ArrayList<Character> indexData = new ArrayList<>();
        boolean index = false;
        Integer numberOfItems = null;

        for(byte b : data){

            char x = (char) b;

            currentData.add(x);

            if (x == delimiter){
                delimiterCount++;
            }

            if (delimiterCount == positionOfIndex){
                index = true;
            }

            if (index){
                indexData.add(x);
            }

            if (delimiterCount == positionOfIndex + 1){
                Character[] flow = new Character[currentData.size()];
                numberOfItems = Integer.parseInt(parseRaw(indexData.toArray(flow)).replace(delimiter,' ').trim());
                index = false;
            }

            if(numberOfItems != null && delimiterCount == numberOfItems + 1){
                Character[] flow = new Character[currentData.size()];
                output.add(new Line(currentData.toArray(flow),delimiter));
                currentData.clear();
                delimiterCount = 0;
                indexData.clear();
            }

        }
        return output;
    }

    private String parseRaw(Character[] flow){
        String raw = "";
        for(char c : flow){
            raw += c;
        }
        return raw;
    }

}
