package EjerciciosDeClase.FirstUnit;
/*

	Exercici 13

	Contexte:
	Escriviu un programa que calculi el total d'una factura a partir de la base imposable.

	Alumne: Carlos Pomares Parpal
	Data: 22-10-2020

*/

import java.util.Scanner;

public class E13 {
    public static void main(String[] args){

        final double BASEIMPOSABLE = 1.21; // Aixo es el 21% de la factura.

        Scanner userIn = new Scanner(System.in);
        double total; // Total sense IVA

        // Obtenin la base deductible.
        System.out.print("Introduce el total de la factura: ");
        total = userIn.nextDouble();

        // Retornam el total amb IVA.
        System.out.println("El total de la factura con base imponible es: " + (total * BASEIMPOSABLE) + "€");
    }
}

