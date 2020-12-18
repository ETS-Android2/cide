package Projectos.SortAlgorithm;

/*

    Project     Programming21
    Package     Projectos.SortAlgorithm    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2020-12-18

    DESCRIPTION
    
*/

import java.util.ArrayList;

/**
 * @author Carlos Pomares
 */

public class Sort {

    public void sortArray(int[] arrayToSort){

        // Add the max value of the arrayToSort
        int[] count = new int[100];

        for(int i = 0; i < arrayToSort.length;i++){
            count[arrayToSort[i] - 1]++;
        }

        for(int i = 0; i < count.length; i++){
            if(count[i] != 0)
                System.out.println(i + 1);
        }
    }

    public static void main(String[] args) {
        int[] toSort = new int[]{1,1,6,3,5,7,9,54,86,23,56};
        Sort s = new Sort();
        s.sortArray(toSort);
    }

}
