package common.data;

import java.util.ArrayList;

public class Line {

    private Data[] data;

    public Line(Character[] input, char delimiter) {
        this.data = parseLine(input,delimiter);
    }

    private Data[] parseLine(Character[] input,char delimiter){
        ArrayList<Data> data = new ArrayList<>();
        ArrayList<Character> currentData = new ArrayList<>();
        int delimiterCount = 0;
        for(char c : input){
            if(c == delimiter){
                delimiterCount++;
            } else {
                currentData.add(c);
            }
            if(delimiterCount == 2){
                Character[] raw = new Character[currentData.size()];
                data.add(new Data(currentData.toArray(raw)));
                delimiterCount = 0;
                currentData.clear();
            }
        }
        Data[] flow = new Data[data.size()];
        return data.toArray(flow);
    }

}
