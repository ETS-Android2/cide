package EjerciciosDeClase.FirstUnit;

/*

        Ejercicio 6
        1ºDAM, Programación

        Hecho por: Carlos Pomares
        Fecha: 16/10/2020

*/

// El objeto Operacions tiene como objetivo realizar diferentes operaciones matemáticas.

public class E06 {
    /**

     En el método main se declaran dos variables para después poder realizar operaciones
     matemáticas simples con ellas. Como por ejemplo: Suma, Resta, Multiplicación y División.

     */
    public static void main(String[] args) {

        // Declaramos dos variable x e y, asignamos los valores 144 y 999.
        int x = 7, y = 5;

        // Damos salida a los diferentes valores para confirmar cuales són.
        System.out.println("El valor x es " + x + "; El valor y es " + y);

        // Las siguientes instrucciones nos darán por salida las operaciones que indican.
        System.out.println("La suma de " + x + " más " + y + " es: " + (x+y)); // Suma
        System.out.println("La resta de " + x + " menos " + y + " es: " + (x-y)); // Resta
        System.out.println("La multiplicación de "+x+" por "+y+" es: "+ (x*y)); // Mutiplicación
        System.out.println("La división entera de "+x+" entre "+y+" es: "+ (x/(double)y)); // División

    }

}
