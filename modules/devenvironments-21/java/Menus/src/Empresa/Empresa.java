package Empresa;

/*

    Project     ED21
    Package     Empresa
    

    Author      Carlos Pomares
    Date        2020-11-14

    DESCRIPTION
    
*/

import java.util.Scanner;

/**
 * @author Carlos Pomares
 */

public class Empresa {

    static Scanner userIn;
    static String userOrder;

    public Empresa(){
        userIn = new Scanner(System.in);
        mainMenu();
    }

    private void mainMenu(){

        boolean exitMenu = false;

        do {

            System.out.println("1: Gestión de empleados");
            System.out.println("2: Gestión de productos");
            System.out.println("3: Gestión de compra/venta");
            System.out.println("4: Salir");

            System.out.print("\nOpción: ");

            userOrder = userIn.nextLine();

            try {
                switch (Integer.parseInt(userOrder)){
                    case 1 -> {
                        menuEmpleados();
                    }
                    case 2 -> {
                        menuProductos();
                    }
                    case 3 -> {
                        menuCompra();
                    }
                    case 4 -> {
                        exitMenu = true;
                    }
                }

            } catch (Exception e) { e.printStackTrace(); }

        } while (!exitMenu);

    }

    private void menuEmpleados(){
        boolean exitMenu = false;

        System.out.println("\n--- Menú RRHH ---\n\n");

        do {

            System.out.println("1: Dar de alta empleado");
            System.out.println("2: Modificar un empleado");
            System.out.println("3: Dar de baja un usuario");
            System.out.println("4: Salir");

            System.out.print("\nOpción: ");

            userOrder = userIn.nextLine();

            try {
                switch (Integer.parseInt(userOrder)){
                    case 1 -> {
                        System.out.println("Dar de alta empleado");
                    }
                    case 2 -> {
                        System.out.println("2: Modificar un empleado");
                    }
                    case 3 -> {
                        System.out.println("3: Dar de baja un usuario");
                    }
                    case 4 -> {
                        exitMenu = true;
                    }
                }

            } catch (Exception e) { e.printStackTrace(); }

        } while (!exitMenu);
    }

    private void menuProductos(){
        boolean exitMenu = false;

        System.out.println("\n--- Menú Productos ---\n\n");

        do {

            System.out.println("1: Dar de alta producto");
            System.out.println("2: Modificar un producto");
            System.out.println("3: Dar de baja un producto");
            System.out.println("4: Salir");

            System.out.print("\nOpción: ");

            userOrder = userIn.nextLine();

            try {
                switch (Integer.parseInt(userOrder)){
                    case 1 -> {
                        System.out.println("Dar de alta producto");
                    }
                    case 2 -> {
                        System.out.println("Modificar un producto");
                    }
                    case 3 -> {
                        System.out.println("Dar de baja un producto");
                    }
                    case 4 -> {
                        exitMenu = true;
                    }
                }
            } catch (Exception e) { e.printStackTrace(); }
        } while (!exitMenu);
    }

    private void menuCompra(){
        boolean exitMenu = false;

        System.out.println("\n--- Menú Productos ---\n\n");

        do {

            System.out.println("1: Gestión de peticiones");
            System.out.println("2: Estadísticas y gráficas");
            System.out.println("3: Salir");

            System.out.print("\nOpción: ");

            userOrder = userIn.nextLine();

            try {
                switch (Integer.parseInt(userOrder)){
                    case 1 -> {
                        System.out.println("Dar de alta producto");
                    }
                    case 2 -> {
                        System.out.println("Modificar un producto");
                    }
                    case 3 -> {
                        exitMenu = true;
                    }
                }
            } catch (Exception e) { e.printStackTrace(); }
        } while (!exitMenu);
    }

}
