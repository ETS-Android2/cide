import java.io.File;
import java.io.FileWriter;

/**
 * 
 * Acceso a Datos - 2021-2022
 * 
 * Práctica 1
 * 
 * Uso de FileWriter para escribir XML en un archivo formateado.
 * 
 * @author Carlos Pomares (http://www.github.com/pomaretta)
 */

public class Formatted_carlos_pomares {
    
    public static void main(String[] args) throws Exception {

        /* 
            Creamos el objeto Writer de tipo FileWriter             
            que nos permitirá insertar datos de tipo carácter 
            en un archivo especificado con la clase File.
         */
        FileWriter writer = new FileWriter(new File("./test.xml"));

        // Escribimos los datos dentro del archivo a través del método write.
        writer.write("<xml version=\"1.0\" encoding=\"UTF-8\">\n\t<Libros>\n\t\t<Libro>\n\t\t\t<Titulo> El Capote </Titulo>\n\t\t</Libro>\n\t</Libros>\n</xml>");

        // Cerramos el stream para evitar problemas de flujos.
        writer.close();

    }

}
