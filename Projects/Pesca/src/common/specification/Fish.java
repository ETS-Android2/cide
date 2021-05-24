package common.specification;

import common.data.Data;

public class Fish {

    /**
     * The identifier of the fish
     */
    private final Data name;

    /**
     * The percentage of catch.
     */
    private final Data percentage;

    /**
     * The lower size of the fish
     */
    private final Data lowerSize;

    /**
     * The higher size of the fish
     */
    private final Data higherSize;

    /**
     * The generated size.
     */
    private final float size;

    /**
     *
     * Used to group some data of the fish.
     *
     * @param name the identifier.
     * @param percentage the percentage of catch
     * @param lowerSize the lower size.
     * @param higherSize the higher size.
     */
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

    /**
     *
     * Generates the size of the fish with the higher and the lower size.
     *
     * @return the size of the fish.
     */
    private float generateSize(){
        return (float) (Math.random() * this.getHigherSize() + this.getLowerSize());
    }

}
