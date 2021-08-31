package EjerciciosDeClase.FirstUnit;
/*

	Exercici 14

	Contexte:
	Escriviu un programa que calculi el sou setmanal d'un treballador en base a les hores treballades, a raó de 12€ l'hora.

	Alumne: Carlos Pomares Parpal
	Data: 22-10-2020

*/

import java.util.Scanner;

public class E14 {
    public static void main(String[] args){

        // Asignam el preu com una constant
        final float PREU = 12;

        // Objecte Scanner per obtenir dades de l'usuari.
        Scanner userIn = new Scanner(System.in);

        // Variable per guardar les hores treballades.
        float horasTrabajadas;

        System.out.print("Introduce el número de horas trabajadas: ");
        horasTrabajadas = userIn.nextFloat();

        // Retornam el preu per saber a quant es paga la hora, el nombre de hores treballades, i finalment el total del salari semanal.
        System.out.println("Según el precio de "+ (int)PREU +"€/hora, el número de horas introducidas " +
                (int)horasTrabajadas + "H, el salario semanal del trabajador es un total de: "
                + (horasTrabajadas * PREU) + "€");
    }
}