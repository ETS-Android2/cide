package common.data;

import transformation.Transform;

import java.io.IOException;
import java.util.ArrayList;

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
        String data = "";
        data += delimiter;
        for(Data d : this.data){
            for(byte b : d.getRaw()){
                data += (char) b;
            }
            data += delimiter;
        }
        data += '\n';
        return data.getBytes();
    }

    /**
     *
     * Return single Data object contained in a Line.
     *
     * @param input the raw input of data.
     * @param delimiter to separate the data.
     * @param position of the data to return.
     * @return a Data Object with the values.
     * @throws IOException if something of the Input/Output fails.
     */
    public static Data getDataInParsedLine(Byte[] input,char delimiter,int position) throws IOException {

        String currentData = "";
        int delimiterCount = 0;
        int positionCount = 0;

        for(byte c : input){

            if(c == delimiter){
                delimiterCount++;
            } else if (c != '\n'){
                currentData += (char) c;
            }
            // The data is limited by 2 delimiters #<DATA>#.
            if(delimiterCount == 2){
                Data d = new Data(Transform.toComplex(currentData.getBytes()));

                delimiterCount = 1;
                currentData = "";

                if(position == positionCount){
                    return d;
                }

                positionCount++;

            }


        }

        throw new IOException(String.format("Data not found. Search for %d and stopped on %d",position,positionCount));
    }

}
