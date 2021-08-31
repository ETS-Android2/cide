package common.data;

public class Data {

    /**
     * The raw value of data.
     */
    private final Byte[] raw;

    /**
     * Float value of raw data.
     */
    private Float floatValue;

    /**
     * Integer value of raw data.
     */
    private Integer intValue;

    /**
     *
     * Defines a class that contains one data,
     * without needs.
     *
     * @param raw the input data.
     */
    public Data(Byte[] raw) {
        this.raw = raw;
        parseData();
    }

    /**
     *
     * Builds an string with the input data.
     *
     * @param flow the raw data.
     * @return String of the data.
     */
    private String parseRaw(Byte[] flow){
        String raw = "";
        for(byte c : flow){
            raw += (char) c;
        }
        return normalize(raw);
    }

    /**
     *
     * Escape 0x0d character, found some problems with
     * data in registers.txt.
     *
     * @param s the string to normalize.
     * @return the string normalized.
     */
    private String normalize(String s){
        return s.replace((char) 0x0d,'&').replaceAll("&","");
    }

    /**
     * Tries to parse the input data in float and int value.
     * Without need order to do that.
     */
    private void parseData(){
        try {
            this.intValue = Integer.parseInt(parseRaw(this.raw));
        } catch (Exception e){
            // May isn't integer value and can throw mismatch value exception.
        }
        try {
            this.floatValue = Float.parseFloat(parseRaw(this.raw));
        } catch (Exception e){
            // May isn't float value and can throw mismatch value exception.
        }
    }

    public Byte[] getRaw() {
        return this.raw;
    }

    /**
     * @return the string value of the raw data.
     */
    public String getStringValue() {
        return parseRaw(this.raw);
    }

    public float getFloatValue() {
        return this.floatValue;
    }

    public int getIntValue() {
        return this.intValue;
    }

}
