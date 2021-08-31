package EjerciciosDeClase.FirstUnit;
/*

	Exercici 15

	Contexte:
	Escriviu un programa que calculi el volum d'un con segons la fórmula : 1/3 de Pi pel radi al quadrat per l'alçada
	Pel valor de Pi, feu servir una constant.
	Nota: penseu sense cercar a internet com podem elevar al quadrat el radi.

	Alumne: Carlos Pomares Parpal
	Data: 22-10-2020

*/

import java.util.Scanner;

public class E15 {
    public static void main(String[] args){
        // Formula: (PI * (r)2 * alçada) / 3

        Scanner userIn = new Scanner(System.in);

        // Asignam la constant de PI
        final double PI = Math.PI;

        // Dos varialbes per el radi i l'alçada.
        double radi,alcada;

        // Obtenim el radi.
        System.out.print("Introdueix el radi del con: ");
        radi = userIn.nextDouble();

        // Obtenim l'alçada
        System.out.print("Introdueix l'alçada del con: ");
        alcada = userIn.nextDouble();

        // Retornam la següent formula (PI * (r)2 * alçada) / 3
        System.out.println("El volum del con amb, radi: " + radi + "; alçada: " + alcada + "; El resultat del volum es: " + ((PI*(radi*radi)*alcada) / 3));
    }
}
