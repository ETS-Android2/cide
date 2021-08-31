package EmpresaLite;

/*

    Project     ED21
    Package     EmpresaLite
    

    Author      Carlos Pomares
    Date        2020-11-14

    DESCRIPTION
    
*/

import java.util.Scanner;

/**
 * @author Carlos Pomares
 */


public class EmpresaLite {

    static Scanner userIn;

    public EmpresaLite(){
        userIn = new Scanner(System.in);
        mainMenu();
    }

    private void mainMenu(){

        String userOrder;
        boolean exitMenu = false;

        do {

            System.out.println("1: Crear usuario");
            System.out.println("2: Buscar usuario");
            System.out.println("3: Cambiar contraseña");
            System.out.println("4: Dar de baja");
            System.out.println("5: Salir");

            System.out.print("Introduce tu opción: ");
            userOrder = userIn.nextLine();

            try {
                switch (Integer.parseInt(userOrder)){
                    case 1 -> {
                        System.out.println("Crear usuario.");
                    }
                    case 2 -> {
                        System.out.println("Buscar usuario.");
                    }
                    case 3 -> {
                        System.out.println("Cambiar contraseña.");
                    }
                    case 4 -> {
                        System.out.println("Dar de baja.");
                    }
                    case 5 -> {
                        System.out.println("Salir.");
                        exitMenu = true;
                    }
                }
            } catch (Exception e){e.printStackTrace();}

        } while(!exitMenu);
    }
}
