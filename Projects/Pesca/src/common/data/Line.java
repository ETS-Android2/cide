package common.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Line {

    /**
     * The data that contains the incoming line.
     */
    private Data[] data;

    /**
     *
     * The Line is the separation of information inside a file.
     *
     * @param input the raw input of the parsed line.
     * @param delimiter the delimiter that separates the data.
     */
    public Line(Byte[] input, char delimiter) {
        this.data = parseLine(input,delimiter);
    }

    /**
     *
     * This method will take the line and will detect the data inside it,
     * when the data is detected will create a new Data() with it.
     *
     * @param input the raw input of data.
     * @param delimiter the delimiter that separates de data.
     * @return an arraylist of data.
     */
    protected Data[] parseLine(Byte[] input,char delimiter){
        ArrayList<Data> data = new ArrayList<>();
        ArrayList<Byte> currentData = new ArrayList<>();
        int delimiterCount = 0;
        for(byte c : input){
            if(c == delimiter){
                delimiterCount++;
            } else if (c != '\n'){
                currentData.add(c);
            }
            // The data is limited by 2 delimiters #<DATA>#.
            if(delimiterCount == 2){
                Byte[] raw = new Byte[currentData.size()];
                data.add(new Data(currentData.toArray(raw)));
                delimiterCount = 1;
                currentData.clear();
            }
        }
        Data[] flow = new Data[data.size()];
        return data.toArray(flow);
    }

    public Data[] getData() {
        return data;
    }

    /**
     *
     * Allow to export the parsed data to raw data.
     * And can be writed with an FileOutputStream.
     *
     * @param delimiter the delimiter to separate the data.
     * @return an array of bytes containing the data with \n at the end.
     */
    public byte[] exportData(char delimiter){
        ArrayList<Byte> data = new ArrayList<>();
        data.add((byte)delimiter);
        for(Data d : this.data){
            Collections.addAll(data, d.getRaw());
            data.add((byte)delimiter);
        }
        data.add((byte)'\n');
        Byte[] flow = new Byte[data.size()];
        return toPrimitive(data.toArray(flow));
    }

    /**
     *
     * Allows to convert Byte[] to byte[]
     *
     * @param bytes the incoming Byte[] array.
     * @return an output byte[] array.
     */
    public byte[] toPrimitive(Byte[] bytes){
        byte[] output = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            output[i] = bytes[i];
        }
        return output;
    }

    public static Data getDataInParsedLine(Byte[] input,char delimiter,int position) throws IOException {

        ArrayList<Byte> currentData = new ArrayList<>();
        int delimiterCount = 0;
        int positionCount = 0;

        for(byte c : input){

            if(c == delimiter){
                delimiterCount++;
            } else if (c != '\n'){
                currentData.add(c);
            }
            // The data is limited by 2 delimiters #<DATA>#.
            if(delimiterCount == 2){
                Byte[] raw = new Byte[currentData.size()];
                Data d = new Data(currentData.toArray(raw));
                delimiterCount = 1;
                currentData.clear();

                if(positionCount == (position - 1)){
                    return d;
                }

                positionCount++;

            }


        }

        throw new IOException("Data not found.");
    }

}
