package common.data;

import java.util.ArrayList;
import java.util.Collections;

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

    public Data[] getData() {
        return data;
    }

    public byte[] exportData(char delimiter){
        ArrayList<Byte> data = new ArrayList<>();
        data.add((byte)delimiter);
        for(Data d : this.data){
            Collections.addAll(data, d.getRaw());
            data.add((byte)delimiter);
        }
        Byte[] flow = new Byte[data.size()];
        return toPrimitive(data.toArray(flow));
    }

    public byte[] toPrimitive(Byte[] bytes){
        byte[] output = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            output[i] = bytes[i];
        }
        return output;
    }

}
