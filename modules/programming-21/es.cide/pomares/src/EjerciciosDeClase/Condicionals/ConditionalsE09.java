package EjerciciosDeClase.Condicionals;

/*

 Exercicis de condicionals
 Exercici 09

 Contexte --
 Fes un programa que resolgui una eqüació de segon grau (tipus ax2+bx+c=0.

 @author     Carlos Pomares
 Date        2020-11-7

 */

public class ConditionalsE09 {
    public static void start(){

        double a = 1, b = -10, c = 24;

        double[] x = new double[3];

        x[0] = b * -1;

        if((b*b) - (4 * a * c) <= 0){
            System.out.println("Raiz negativa.");
        } else {
            x[1] = Math.sqrt((b*b) - (4 * a * c));
            x[2] = 2 * a;

            System.out.println("x1: " + ((x[0] + x[1]) / x[2]) + " x2: " + ((x[0] - x[1]) / x[2]));
        }
    }
}
