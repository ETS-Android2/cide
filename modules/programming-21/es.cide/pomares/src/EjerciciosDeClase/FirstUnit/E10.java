package EjerciciosDeClase.FirstUnit;
/*

	Exercici 10

	Contexte:
	Crear un programa en Java que faci les operacions suma, resta, multiplicació i divisió a partir dels operadors introduïts per teclat (utilitzant la classe Scanner)

	Alumne: Carlos Pomares Parpal
	Data: 21-10-2020

*/


// Com utilitzam la l'objete Scanner hem d'importarlo.
import java.util.Scanner;

public class E10 {
    public static void main(String[] args){

        // Declaram les dues variables per obtenir dades de l'usuari.
        Scanner userIn = new Scanner(System.in);
        String userOrder;

        // Declaram les dues variables on emmagatzemaran els valors de les operacions utilitzaràn.
        double x,y;

        // Texte per donar informació a l'usuari.
        System.out.println("Introduce la operación que deseas realizar.");
        System.out.println("SUMA - RESTA - DIVISIÓN - MULTIPLICACIÓN");
        System.out.print("ORDEN: ");

        // Lletgim l'ordre.
        userOrder = userIn.nextLine();

        // Feim un switch per no utilitzar if.
        switch(userOrder.toLowerCase()){
            case "suma": case "+":

                // Operaciò de suma on es suma x més y.

                System.out.print("Introduce el primer número entero: ");

                // Obtenim el valor x.
                x = userIn.nextDouble();

                System.out.print("Introduce el número que suma: ");

                // Obtenim el valor y.
                y = userIn.nextDouble();

                System.out.println("La suma de " + x + " más " + y + " es: " + (x+y));
                break;
            case "resta": case "-":

                // Operaciò de resta on es resta x menos y.

                System.out.print("Introduce el primer número entero: ");

                // Obtenim el valor x.
                x = userIn.nextDouble();

                System.out.print("Introduce el número entero que resta: ");

                // Obtenim el valor y.
                y = userIn.nextDouble();

                System.out.println("La resta de " + x + " menos " + y + " es: " + (x-y));
                break;
            case "division": case "/":

                // Utilitzam el bluces do-while per tenir en compte les excepcions de les divisions.

                System.out.print("Introduce el divisor: ");
                // Obtenim el valor x.
                x = userIn.nextDouble();

				/*
					Aquest bucle es basa en que si asignam un valor de 0 en el dividend no es posible.
					Per aixo mentres l'usuari intenti asignar-li el valor de 0 repetira l'assignació
					fins que introdueixi un valor correcte.
				*/
                do {
                    System.out.print("Introduce el dividendo: ");
                    // Obtenim el valor y.
                    y = userIn.nextDouble();
                    if(y == 0){
                        System.out.println("El dividendo no puede ser 0.");
                    }
                } while (y == 0);

                System.out.println("La división de " + x + " entre " + y + " es: " + (x/y));
                break;
            case "multiplicacion": case "*":

                // Aquest modul es per fer multiplicacions.

                System.out.print("Introduce el primer número entero: ");

                // Obtenim el valor x.
                x = userIn.nextDouble();

                System.out.print("Introduce el multiplicador: ");

                // Obtenim el valor y.
                y = userIn.nextDouble();

                System.out.println("La multiplicación de " + x + " por " + y + " es: " + (x*y));
                break;
        }
    }
}