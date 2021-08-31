package common.specification;

import common.data.Data;

public class Statistics {

    /**
     * The flag to identify the value.
     */
    private Data flag;

    /**
     * The value of the data.
     */
    private float data;

    /**
     *
     * This class is the container to structure the data for
     * parsing in the StatisticResult
     *
     * @param flag the flag to identify the data.
     * @param data the value of the data.
     */
    public Statistics(Data flag, float data) {
        this.flag = flag;
        this.data = data;
    }

    public String getFlag() {
        return flag.getStringValue();
    }

    protected float getData() {
        return data;
    }

}
