package Projectos.SortAlgorithm;

/*

    Project     Programming21
    Package     Projectos.SortAlgorithm    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2020-12-18

    DESCRIPTION
    
*/

/**
 * @author Carlos Pomares
 */

public class Sort {

    public int[] sortArray(int[] arrayToSort){

        int f;
        int x = 0;

        // Add the max value of the arrayToSort
        int[] count = new int[maxNumberInArray(arrayToSort)];

        for (int j : arrayToSort) {
            count[j - 1]++;
        }

        for(int i = 0; i < count.length; i++){
            f = 1;
            while(count[i] > 0 && f <= count[i]){
                arrayToSort[x++] = i + 1;
                f++;
            }
        }

        return arrayToSort;
    }

    public int maxNumberInArray(int[] array){
        int output = 0;
        for(int number : array){
            if(output <= number)
                output = number;
        }
        return output;
    }

    public static void printArray(int[] array){
        for(int number : array){
            System.out.println(number + "\n");
        }
    }

    public static int[] generateRandomArray(int lengthOfArray){
        int maxNumber = (int)(Math.random() * 1000 + 1);
        int[] output = new int[lengthOfArray];
        for (int i = 0; i < output.length ; i++) {
            output[i] = (int)(Math.random() * maxNumber + 1);
        }
        return output;
    }

    public static int[] generateRandomArray(int lengthOfArray, int maxNumber){
        int[] output = new int[lengthOfArray];
        for (int i = 0; i < output.length ; i++) {
            output[i] = (int)(Math.random() * maxNumber + 1);
        }
        return output;
    }

    public static void main(String[] args) {
        int[] toSort = Sort.generateRandomArray(10,10);
        Sort s = new Sort();
        Sort.printArray(s.sortArray(toSort));
    }

}
