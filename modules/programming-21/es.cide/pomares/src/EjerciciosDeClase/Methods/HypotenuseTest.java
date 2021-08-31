package EjerciciosDeClase.Methods;

/*

    Project     Programming21
    Package     EjerciciosDeClase.Methods    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2020-11-24

    DESCRIPTION
    
*/

import java.util.InputMismatchException;
import java.util.Scanner;

/**1
 * @author Carlos Pomares
 */


public class HypotenuseTest {

    final private int MAX_TRIANGLES = 20;
    final private int TRIANGLE_DATA = 2;

    private Scanner userIn;
    private Hypotenuse hypotenuse;
    private double[][] triangles;

    public HypotenuseTest(){
        userIn = new Scanner(System.in);
        hypotenuse = new Hypotenuse();
        triangles = new double[this.MAX_TRIANGLES][this.TRIANGLE_DATA];
        menu();
    }

    private void menu(){
        boolean exit = false;
        while (!exit){
            System.out.println("=========================");

            System.out.print(
                            "\n" + "    Option 1: Calculate Hypotenuse by given sides" +
                            "\n" + "    Option 2: Author" +
                            "\n" + "    Option 3: Help" +
                            "\n" + "    Option 4: Exit" + "\n"
            );

            System.out.print("  Order: ");

            switch (this.userIn.nextInt()){
                case 1 -> {
                    triangleMenu();
                }
                case 2 -> {
                    author();
                }
                case 3 -> {
                    help();
                }
                case 4 -> {
                    System.out.print("Exit...");
                    exit = true;
                }
            }
            System.out.println("=========================");
        }
    }
    private void triangleMenu(){
        int counter = 0;
        boolean exit = false;

        System.out.println("=========================");

        System.out.print(
                "\n" + "Calculate Triangle Hypotenuse" + "\n" +
                        "By Carlos Pomares" + "\n"
        );

        while(counter < triangles.length && !exit){

            System.out.print("  Option 1: Add triangle\n");
            System.out.print("  Option 2: Resume\n");
            System.out.print("  Option 3: Exit\n");

            System.out.print("  Order: ");

            switch (this.userIn.nextInt()){
                case 1 -> {
                    addTriangleMenu(this.userIn);
                }
                case 2 -> {
                    resume();
                }
                case 3 -> {
                    exit = true;
                }
            }
        }

        System.out.println("=========================");
    }

    private void author(){
        System.out.println("\n=========================");
        System.out.print("(c) Carlos Pomares\n");
        System.out.print("www.carlospomares.es\n");
        System.out.println("=========================\n");
    }
    private void help(){
        System.out.println("Not implemented.");
    }
    private void addTriangleMenu(Scanner sc){
        double sideA,sideB;
        try {
            System.out.print("  Side A: ");
            sideA = sc.nextDouble();
            System.out.print("  Side B: ");
            sideB = sc.nextDouble();
            addTriangle(sideA,sideB);
        } catch (InputMismatchException e){ e.printStackTrace(); }
    }
    private void addTriangle(double side1, double side2){
        if(this.triangles.length <= this.MAX_TRIANGLES){
            if(triangles.length == 0){
                this.triangles[0][0] = side1;
                this.triangles[0][1] = side2;
            }
            this.triangles[this.triangles.length - 1][0] = side1;
            this.triangles[this.triangles.length - 1][1] = side2;
        }
    }
    private void resume(){
        System.out.println(this.triangles.length);
        System.out.println("=========================");
        System.out.printf("%-15s %-15s %-15s %-15s",
                "Triangle",
                "Side A",
                "Side B",
                "Hypotenuse");
        for(int i = 0; i < this.triangles.length ; i++){
                System.out.printf("\n%-15d %-15f %-15f %-15f",
                    i + 1,
                    this.triangles[i][0],
                    this.triangles[i][1],
                    hypotenuse.calculateHypotenuse(this.triangles[i][0],this.triangles[i][1]));
        }
        System.out.println("\n=========================");
    }
}
