package common.specification;

public class Statistics {

    private float sum;
    private float mean;
    private float average;
    private float max;
    private float min;

    public Statistics(float sum, float mean, float average, float max, float min) {
        this.sum = sum;
        this.mean = mean;
        this.average = average;
        this.max = max;
        this.min = min;
    }

    public float getSum() {
        return sum;
    }

    public float getMean() {
        return mean;
    }

    public float getAverage() {
        return average;
    }

    public float getMax() {
        return max;
    }

    public float getMin() {
        return min;
    }
}
