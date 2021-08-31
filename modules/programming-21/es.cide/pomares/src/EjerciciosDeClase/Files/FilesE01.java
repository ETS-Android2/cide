package EjerciciosDeClase.Files;

/*

    Project     Programming21
    Package     EjerciciosDeClase.Files    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-01-15

    DESCRIPTION
    
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

/**
 * @author Carlos Pomares
 */


public class FilesE01 {
    public static void start(){
        // CODE

        try {

            BufferedReader br = new BufferedReader(new FileReader(
                    "C:\\Development\\repo\\github\\school\\PROG21\\es.cide\\pomares\\src\\EjerciciosDeClase\\Files\\file.txt"
            ));

            String linea = "";

            while(linea != null){
                System.out.println(linea);
                linea = br.readLine();
            }

            br.close();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
