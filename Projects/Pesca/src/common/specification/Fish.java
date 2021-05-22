package common.specification;

import common.data.Data;

public class Fish {

    private final Data name;
    private final Data percentage;
    private final Data lowerSize;
    private final Data higherSize;
    private final float size;

    public Fish(Data name, Data percentage, Data lowerSize, Data higherSize) {
        this.name = name;
        this.percentage = percentage;
        this.lowerSize = lowerSize;
        this.higherSize = higherSize;
        this.size = generateSize();
    }

    public String getName() {
        return name.getStringValue();
    }

    public float getPercentage() {
        return percentage.getFloatValue();
    }

    public float getSize() {
        return size;
    }

    private float getLowerSize() {
        return lowerSize.getFloatValue();
    }

    private float getHigherSize() {
        return higherSize.getFloatValue();
    }

    private float generateSize(){
        return (float) (Math.random() * this.getHigherSize() + this.getLowerSize());
    }

}
