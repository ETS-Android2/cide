package common.specification;

import common.data.Data;

public class Statistics {

    private Data flag;
    private Data data;

    public Statistics(Data flag, Data data) {
        this.flag = flag;
        this.data = data;
    }

    public String getFlag() {
        return flag.getStringValue();
    }

    protected float getData() {
        return data.getFloatValue();
    }

}
