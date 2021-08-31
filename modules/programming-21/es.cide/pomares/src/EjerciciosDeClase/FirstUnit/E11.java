package EjerciciosDeClase.FirstUnit;
/*

	Exercici 11

	Contexte:
	Escriviu un programa que calculi l'àrea d'un rectangle.

	Alumne: Carlos Pomares Parpal
	Data: 21-10-2020

*/

import java.util.Scanner;

public class E11 {
    /*

        L'area de un rectangles es el resultat de la base per l'altura.

    */
    public static void main(String[] args){

        // Nova instància del objecte Scanner per obtenir dades de l'usuari.
        Scanner userIn = new Scanner(System.in);

        // Diferents variables per calcular l'àrea del rectangle i tenir la unitat de medida.
        double base, altura;
        String unidad;

        // Texte per informar a l'usuari.
        System.out.println("AREA DE UN RECTÁNGULO");
        System.out.println("Introduce la BASE y ALTURA");
        System.out.println("Establece la unidad del programa");
        System.out.println("MM(milímetros) - CM(centímetros) - M(metros) - KM(kilómetros)");

        // Obtenim l'unitat.
        System.out.print("UNIDAD: ");
        unidad = userIn.nextLine();

        // Obtenim la base.
        System.out.print("BASE: ");
        base = userIn.nextDouble();

        // Obtenim l'altura.
        System.out.print("ALTURA: ");
        altura = userIn.nextDouble();

        // Donam sortida al resultat del càlcul.
        System.out.println("El área del rectángulo con " + (base + " " + unidad) + " y " + (altura + " " + unidad) + " és de: " + (base * altura) + " " + unidad);
    }
}
