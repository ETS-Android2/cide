package EjerciciosDeClase.FirstUnit;
/*

        Exercici 07

        Contexte:
        Crea la variable nom i assigna-li el teu nom complet. Mostra el valor per pantalla.
        Heu te fer servir el tipus String.

        Una vegada fet això feu el mateix però utilitzant tres variables de tipus String. Una pel nom, una altra per primer llinatge i la tercera pel segon llinatge.
        El resultat (la sortida per pantalla) en ambdós casos ha de ser exactament igual.

        Alumne: Carlos Pomares Parpal
        Data: 20-10-2020

*/
public class E07 {
    public static void main(String[] args) {

        // Nom complet utilitzant només una variable.
        String nomComplet = "Carlos Pomares Parpal";

        // Nom complet utilizant tres variables.
        String nom = "Carlos", primerLlinatge = "Pomares", segonLlinatge = "Parpal";

        System.out.println("El meu nom es: "+nomComplet); // Amb una variable
        System.out.println("El meu nom es: "+nom+" "+primerLlinatge+" "+segonLlinatge); // Amb tres variables

        /*
                Conclusió, sigui amb una o tres variables dona sa mateixa sortida per terminal
                sempre que s'utilizin se mateixes marcas d'estíl (espais).
        */
    }
}
