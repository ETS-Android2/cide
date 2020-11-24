package EjerciciosDeClase.Methods;

/*

    Project     Programming21
    Package     EjerciciosDeClase.Methods    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2020-11-24

    DESCRIPTION
    (Hypotenuse Calculations) Define a method hypotenuse
    that calculates the hypotenuse of a right triangle
     when the lengths of the other two sides are given.
     The method should take two arguments of type double
     and return the hypotenuse as a double. Incorporate
     this method into an application that reads values for
     side1 and side2 and performs the calculation with the
     hypotenuse method. Use Math methods pow and sqrt to
     determine the length of the hypotenuse for each of the
     triangles in the following figure [Note: Class Math also
     provides method hypot to perform this calculation,
     so you can make an additional code to check your results
     by using this class]
    
*/

/**
 * @author Carlos Pomares
 */


public class Hypotenuse {
    public double calculateHypotenuse(double side1, double side2){
        return Math.sqrt(Math.pow(side1,2) + Math.pow(side2,2));
    }
}
