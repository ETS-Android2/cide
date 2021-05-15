package common.data;

public class Data {

    private final Byte[] raw;
    private Float floatValue;
    private Integer intValue;

    public Data(Byte[] raw) {
        this.raw = raw;
        parseData();
    }

    private String parseRaw(Byte[] flow){
        String raw = "";
        for(byte c : flow){
            raw += (char) c;
        }
        return raw;
    }

    private void parseData(){
        try {
            this.intValue = Integer.parseInt(parseRaw(this.raw));
        } catch (Exception e){
            // Ignored
        }
        try {
            this.floatValue = Float.parseFloat(parseRaw(this.raw));
        } catch (Exception e){
            // Ignored
        }
    }

    public String getStringValue() {
        return parseRaw(this.raw);
    }

    public float getFloatValue() {
        return floatValue;
    }

    public int getIntValue() {
        return intValue;
    }

}
