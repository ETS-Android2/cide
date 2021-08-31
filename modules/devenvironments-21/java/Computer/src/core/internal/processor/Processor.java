package core.internal.processor;

public class Processor {

    private String manufacturer;
    private String architecture;
    private int cacheCapacity;
    private float baseClock;
    private float maxClock;
    private int cores;
    private int threads;

    public Processor(){
        setArchitecture("x86");
        setBaseClock(2.5f);
        setCores(6);
        setThreads(12);
        setMaxClock(4.2f);
        setManufacturer("Intel");
        setCacheCapacity(34);
    }

    public Processor(String manufacturer, String architecture, int cacheCapacity, float baseClock, float maxClock, int cores, int threads) {
        this.manufacturer = manufacturer;
        this.architecture = architecture;
        this.cacheCapacity = cacheCapacity;
        this.baseClock = baseClock;
        this.maxClock = maxClock;
        this.cores = cores;
        this.threads = threads;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    private void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getArchitecture() {
        return architecture;
    }

    private void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    public int getCacheCapacity() {
        return cacheCapacity;
    }

    private void setCacheCapacity(int cacheCapacity) {
        this.cacheCapacity = cacheCapacity;
    }

    public float getBaseClock() {
        return baseClock;
    }

    private void setBaseClock(float baseClock) {
        this.baseClock = baseClock;
    }

    public float getMaxClock() {
        return maxClock;
    }

    private void setMaxClock(float maxClock) {
        this.maxClock = maxClock;
    }

    private int getCores() {
        return cores;
    }

    private void setCores(int cores) {
        this.cores = cores;
    }

    private int getThreads() {
        return threads;
    }

    private void setThreads(int threads) {
        this.threads = threads;
    }
}
