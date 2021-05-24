package common.specification;

import common.data.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class StatisticResult {

    /**
     * The maximum size of all the fish.
     */
    private float max;
    /**
     * The minimum size of all the fish
     */
    private float min;
    /**
     * The average size of all the fish.
     */
    private float average;
    /**
     * The mean size of all the fish.
     */
    private float mean;
    /**
     * The sizes of all the fish group by type of fish.
     */
    private HashMap<String, Float> fishSizes;
    /**
     * The number of catches of all the fish group by the fish identifier.
     */
    private HashMap<String, Float> fishCatches;
    /**
     * The raw statistics.
     */
    private ArrayList<Statistics> statistics;

    /**
     *
     * The class to parse all the statistics.
     *
     * @param statistics the input data.
     */
    public StatisticResult(ArrayList<Statistics> statistics) {
        this.statistics = statistics;
        this.fishSizes = new HashMap<>();
        this.fishCatches = new HashMap<>();
        this.parseStatistic();
    }

    /**
     *
     * The method that iterates on the data an calculates the different metrics.
     *
     */
    private void parseStatistic(){

        float average = 0;

        for(Statistics s : this.statistics){

            // MAX
            if(max < s.getData()){
                max = s.getData();
            }

            // FIRST MIN
            if(min == 0){
                min = s.getData();
            }

            // MIN
            if(min > s.getData()){
                min = s.getData();
            }

            // FISH SIZES
            if (!fishSizes.containsKey(s.getFlag())){
                fishSizes.put(s.getFlag(),s.getData());
            } else if(fishSizes.containsKey(s.getFlag()) && fishSizes.get(s.getFlag()) < s.getData()){
                fishSizes.replace(s.getFlag(),s.getData());
            }

            // FISH CATCHES
            if (!fishCatches.containsKey(s.getFlag())){
                fishCatches.put(s.getFlag(),1f);
            } else if(fishCatches.containsKey(s.getFlag())){
                fishCatches.replace(s.getFlag(),fishCatches.get(s.getFlag()) + 1);
            }

            average += s.getData();

        }

        // AVERAGE
        this.average = average / this.statistics.size();

        // MEAN
        ArrayList<Statistics> mean = this.statistics;
        mean.sort(Comparator.comparing(Statistics::getData));

        if(mean.size() > 1){
            this.mean = mean.get((mean.size() / 2) - 1).getData() + mean.get(mean.size() / 2).getData() / 2;
        }

    }

    public float getMax() {
        return max;
    }

    public float getMin() {
        return min;
    }

    public float getAverage() {
        return average;
    }

    public float getMean() {
        return mean;
    }

    public HashMap<String, Float> getFishSizes() {
        return fishSizes;
    }

    public HashMap<String, Float> getFishCatches() {
        return fishCatches;
    }

}
