package EjerciciosDeClase.HashMap;

/*

    Project     Programming21
    Package     EjerciciosDeClase.HashMap    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-01-15

    DESCRIPTION
    
*/

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Carlos Pomares
 */


public class HashMapsE01 {

    public static void start(){

        int intentos = 3;
        boolean access = false;
        Scanner userIn = new Scanner(System.in);

        HashMap<String,String> usuarios = new HashMap<>();

        usuarios.put("carlos","1234");

        do {

            String user, password;

            try {

                System.out.print("User: ");
                user = userIn.nextLine();

                System.out.print("Password: ");
                password = userIn.nextLine();

                for(Map.Entry<String,String> entry : usuarios.entrySet()){
                    if(entry.getKey().equals(user)){
                        if(entry.getValue().equals(password)){
                            System.out.println("Access granted.");
                            access = true;
                        } else {
                            intentos--;
                        }
                    } else {
                        intentos--;
                    }
                }

            } catch (Exception e){
                System.out.println(e.getMessage());
            }



        } while (!access && intentos > 0);

        if(!access)
            System.out.println("Acces denied");


    }

}
