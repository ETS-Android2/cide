package common.data;

import java.util.ArrayList;

public class Line {

    private Data[] data;

    public Line(Byte[] input, char delimiter) {
        this.data = parseLine(input,delimiter);
    }

    private Data[] parseLine(Byte[] input,char delimiter){
        ArrayList<Data> data = new ArrayList<>();
        ArrayList<Byte> currentData = new ArrayList<>();
        int delimiterCount = 0;
        for(byte c : input){
            if(c == delimiter){
                delimiterCount++;
            } else {
                currentData.add(c);
            }
            if(delimiterCount == 2){
                Byte[] raw = new Byte[currentData.size()];
                data.add(new Data(currentData.toArray(raw)));
                delimiterCount = 0;
                currentData.clear();
            }
        }
        Data[] flow = new Data[data.size()];
        return data.toArray(flow);
    }

}
