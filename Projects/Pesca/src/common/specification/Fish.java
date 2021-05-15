package common.specification;

import common.data.Data;

public class Fish {

    private final Data name;
    private final Data percentage;
    private final Data lowerSize;
    private final Data higherSize;

    public Fish(Data name, Data percentage, Data lowerSize, Data higherSize) {
        this.name = name;
        this.percentage = percentage;
        this.lowerSize = lowerSize;
        this.higherSize = higherSize;
    }

    public Data getName() {
        return name;
    }

    public Data getPercentage() {
        return percentage;
    }

    public Data getLowerSize() {
        return lowerSize;
    }

    public Data getHigherSize() {
        return higherSize;
    }

}
