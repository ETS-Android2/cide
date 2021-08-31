package EjerciciosDeClase.FirstUnit;
/*

	Exercici 09

	Contexte:
	Realitza un conversor d'euros a pesetes. La quantitat en euros que es vol convertir ha d'estar emmagatzemada en una variable.
	Realitza el conversor invers (de pesetes a euros). La quantitat en pesetes que es vol convertir també ha d'estar emmagatzemada en una variable.
	En aquest has de aconseguir el valors desde teclat.

	Alumne: Carlos Pomares Parpal
	Data: 21-10-2020

*/

import java.util.Scanner;

public class E09 {

    // Variable constant on s'especifica el valor de la peseta contra l'euro.
    final double PESETA = 166.3860;

    /**

     El método toEuro permeteix convertir pesetas a euro de forma que a partír
     d'una entrada d'un número específic de pesetas pots pasar-les a euros.

     @param pesetasToConvert El número de pesetas que es vol convertir
     @return La conversió de peseta a euro.

     */
    public double toEuro(double pesetasToConvert){
        return pesetasToConvert / PESETA;
    }

    /**

     El método toPeseta permeteix convertir euros a pesetas de forma que a partír
     d'una entrada d'un número específic de euros pots pasar-les a pesetas.

     @param eurosToConvert El número de euros que es vol convertir
     @return La conversió de euro a peseta.

     */
    public double toPeseta(double eurosToConvert){
        return eurosToConvert * PESETA;
    }

    public static void main(String[] args){

        // Cream una instancia del objecte Conversor i Scanner
        E09 conversor = new E09();
        Scanner userIn = new Scanner(System.in);

        // Variable per poder tratar ses ordres del usuari.
        String userOrder;

        // Variable per poder tratar la quantitat.
        double cantidad;

        // Serie de instruccions per retornar unes linies per consola.
        System.out.println("Introduce la orden de conversión");
        System.out.println("EURO o PESETA");
        System.out.print("ORDEN: ");

        // L'usuari introdueix l'ordre, pot ser "euro" o "peseta" segons lo que vulgui convertir.
        userOrder = userIn.nextLine().toLowerCase();

        // Amb el switch podem tratar diferents casos d'entrada y no utilitzar if.
        switch (userOrder) {
            // Convertir euro a peseta.
            case "euro" -> {
                // La quantitat de euros.
                System.out.print("Cantidad de euros: ");
                cantidad = userIn.nextDouble();
                System.out.println(cantidad + " EUROS son: " + conversor.toPeseta(cantidad) + " PESETAS.");
            }
            // Convertir pesetas a euro.
            case "peseta" -> {
                // La quantitat de pesetas.
                System.out.print("Cantidad de pesetas: ");
                cantidad = userIn.nextDouble();
                System.out.println(cantidad + " PESETAS son: " + conversor.toEuro(cantidad) + " EUROS.");
            }
        }
    }
}
