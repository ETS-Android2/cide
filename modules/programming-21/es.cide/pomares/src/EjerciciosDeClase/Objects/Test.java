package EjerciciosDeClase.Objects;

/*

    Project     Programming21
    Package     EjerciciosDeClase.Objects
    

    Author      Carlos Pomares
    Date        2020-11-13

    DESCRIPTION
    Creeu una classe que contingui dos atributs un int i un char que no s'inicialitzin. Aquesta
    classe també ha de tenir un mètode que l'únic que farà serà imprimir els valors dels dos atributs.
    Aleshores heu de fer una classe de test i invocar el mètode que hem definit abans per comprovar
    els valors per defecte que java a assignat a les variables no inicialitzades.
    Penseu les coses que ha de fer la classe test abans de poder invocar el mètode definit a la primera classe.

*/

/**
 * @author Carlos Pomares
 */


public class Test {

    int firstValue;
    char secondValue;

    public String obtainValues(){
        return "" + firstValue + ", " + secondValue;
    }

}
