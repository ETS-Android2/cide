package EjerciciosDeClase.Bucles;

/*

    Programming21 --- EjerciciosDeClase.Bucles

    Bucles
    Exercise 13
    
    Description:
    Escriu un programa que llegeixi per paràmetres una llista de 10 nombres
    i calculi quants de nombres són positius i quants són negatius.
    Si detecta que qualsevol entrada no és un nombre sencer positiu
    o negatiu, donarà un missatge i s’aturarà.
    
    version     1.0
    author      Carlos Pomares
    created     2020-11-10

*/

public class SecondUnitE13 {
    public static void start(String[] args){
        String[] nombres = args;

        int[] tipus = new int[2];
        boolean terminat = false;

        for (int i = 0; i < 10 && !terminat; i++) {
            if(Integer.parseInt(nombres[i]) < 0) {
                tipus[0]++;
            } else if(Integer.parseInt(nombres[i]) == 0) {
                System.out.println("Nombre introduit no es negatiu ni positiu.");
                terminat = true;
            } else {
                tipus[1]++;
            }
        }

        System.out.println("En la lista de argumentos, hay " + tipus[0] + " números negativos y " + tipus[1] + " números positivos.");

    }
}
