package EjerciciosDeClase.FirstUnit;
/*

        Exercici 08

        Contexte:
        Realitza un conversor d'euros a pesetes. La quantitat en euros que es vol convertir ha d'estar emmagatzemada en una variable.
        Realitza el conversor invers (de pesetes a euros). La quantitat en pesetes que es vol convertir també ha d'estar emmagatzemada en una variable.

        Alumne: Carlos Pomares Parpal
        Data: 20-10-2020

*/

public class E08 {

    // Variable constant on s'especifica el valor de la peseta contra l'euro.
    static final double PESETA = 166.3860;

    // Varialbe on s'enmagatzema els euros
    static double euros;

    // Varialbe on s'enmagatzema les pesetas
    static double pesetas;

    /**

     El método toEuro permeteix convertir pesetas a euro de forma que a partír
     d'una entrada d'un número específic de pesetas pots pasar-les a euros.

     @param pesetasToConvert El número de pesetas que es vol convertir
     @return La conversió de peseta a euro.

    */

    public double toEuro(double pesetasToConvert){
        return euros = pesetasToConvert / PESETA;
    }

    /**

     El método toPeseta permeteix convertir euros a pesetas de forma que a partír
     d'una entrada d'un número específic de euros pots pasar-les a pesetas.

     @param eurosToConvert El número de euros que es vol convertir
     @return La conversió de euro a peseta.

    */
    public double toPeseta(double eurosToConvert){
        return pesetas = eurosToConvert * PESETA;
    }

    public static void main(String[] args){

        // Cream una instancia del objecte Conversor
        E08 conversor = new E08();

        // Donam sortida al resultat de convertir 5 euros a pesetas.
        System.out.println(conversor.toPeseta(5));

        // Donam sortida al resultat de convertir 831.93 pesetas a euro.
        System.out.println(conversor.toEuro(831.93));
    }
}
