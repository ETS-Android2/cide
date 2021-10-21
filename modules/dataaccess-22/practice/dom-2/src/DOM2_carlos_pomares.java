import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.w3c.dom.Document;

/**
 * 
 * Acceso a Datos - 2021-2022
 * 
 * Práctica DOM
 * Tenemos una clase Cliente sobre la cual vamos a trabajar para convertirla en un XML y luego leerla por pantalla. Para ello vamos a realiza las siguientes tareas:
 * Almacenar varios objetos clientes cuya información sea: nombre y edad, en un fichero  clientes.dat. (crear al menos 5 clientes)
 * Crea un documento XML usando DOM del fichero creado anteriormente.
 * Implementa una clase que permita leer el documento XML del apartado anterior.
 * 
 * @author Carlos Pomares (https://www.github.com/pomaretta)
 * 
 */

public class DOM2_carlos_pomares {

    /**
     * 
     * Permite crear un archivo binario que guardará los valores de los diferentes objetos
     * de tipo Cliente.
     * 
     * @param clientes los clientes a guardar en el archivo binario.
     * @param file el archivo donde guardar los datos.
     * @throws IOException si el archivo no puede ser creado.
     */
    public void createBinaryData(Cliente[] clientes, File file) throws IOException {
        // Creamos el nuevo stream de salida.
        ObjectOutputStream oob = new ObjectOutputStream(new FileOutputStream(file));
        // Iteramos los clientes y se van escribiendo los objetos en el stream.
        for (Cliente c : clientes) {
            // Escribimos el objeto.
            oob.writeObject(c);
        }
        // Cerramos el stream
        oob.close();
    }

    /**
     * 
     * Permite obtener objetos de tipo Cliente de un archivo binario.
     * 
     * @param file el archivo de donde obtener los datos.
     * @return un array de clientes.
     * @throws IOException si no puede acceder al archivo.
     * @throws ClassNotFoundException si no encuentra la clase que intentamos obtener del archivo.
     */
    public Cliente[] getClientFromBinaryFile(File file) throws IOException, ClassNotFoundException {

        // Stream para leer el archivo binario y poder obtener objetos.
        ObjectInputStream oib = new ObjectInputStream(new FileInputStream(file));
        // Array de objetos cliente, dado que no sabemos el size de los clientes, hacemos un array exrtensible.
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();

        // Iteramos hasta encontrarnos con el final del archivo, mediante la EOFException.
        while (true) {
            try {
                // Agregará un objeto cliente en cada iteración.
                clientes.add((Cliente) oib.readObject());
            } catch (EOFException e) {
                // Si se encuentra con la excepción EOFException significará que ha llegado
                // al final del archivo y romperemos el bucle.
                break;
            }
        }
    
        // Cerramos el stream de datos.
        oib.close();

        // Devolvemos el ArrayList convertido a un array primitivo.
        return clientes.toArray(new Cliente[0]);
    }

    public static void main(String[] args) throws Exception {
        
        // Creamos la app.
        DOM2_carlos_pomares app = new DOM2_carlos_pomares();

        // Archivo donde almacenar el binario.
        File binaryFile = new File("./clientes.dat");
        File xmlFile = new File("./clientes.xml");

        // Creamos clientes de muestra.
        Cliente[] clientes = new Cliente[]{
            new Cliente("Carlos", 56),
            new Cliente("Paco", 23),
            new Cliente("Pepe", 34),
            new Cliente("Juan", 76),
            new Cliente("Joshua", 12),
        };

        // Creamos el archivo binario con los clientes.
        app.createBinaryData(clientes, binaryFile);

        // Obtenemos los clientes del archivo creado reciente.
        Cliente[] decodedClients = app.getClientFromBinaryFile(binaryFile);

        // Creamos el writter para poder escribir los clientes en formato XML.
        XMLWritter xw = new XMLWritter(decodedClients);
        // Creamos el documento XML.
        Document doc = xw.createDocument();
        // Creamos un archivo .XML dado Documento.
        XMLWritter.createFile(doc, xmlFile, true);

        // Creamos el reader que permitirá leer el XML reciente y poder ver su contenido.
        XMLReader xr = new XMLReader(xmlFile);
        // Leemos el archivo.
        xr.read();
        // Mostramos el contenido del XML, solo la información de los clientes.
        xr.showContent();

    }
    
}
