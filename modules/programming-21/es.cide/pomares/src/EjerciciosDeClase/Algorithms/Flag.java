package EjerciciosDeClase.Algorithms;

/*

    Project     Programming21
    Package     EjerciciosDeClase.Algorithms    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2020-11-19

    DESCRIPTION
    
*/

import java.util.Scanner;

/**
 * @author Carlos Pomares
 */


public class Flag {

    public Flag(){
        runFlag();
    }

    public void runFlag(){
        boolean flag = false;
        while(!flag){

            Scanner sc = new Scanner(System.in);

            int option = sc.nextInt();

            if(option==5){
                flag = true;
            } else{
                System.out.println("My app.");
            }
        }
    }

}
