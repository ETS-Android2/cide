import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAX_Clientes_carlos_pomares {

    public static void main(String[] args) throws Exception {

        // Creamos el handler
        SAXHandler_carlos_pomares mn = new SAXHandler_carlos_pomares();

        // Creamos el parser
        SAXParser sax = SAXParserFactory.newInstance().newSAXParser();

        // Parseamos el archivo con el handler.
        sax.parse(new File("./clientes.xml"), mn);

    }

}

class SAXHandler_carlos_pomares extends DefaultHandler {

    // Clase Cliente para ir almacenando datos.
    class Cliente {
        public String name;
        public int age;
    }

    // Banderas para saber porque atributo estamos pasando.
    private boolean isName, isAge;
    // La comida que este parseandose en ese momento.
    private Cliente currentClient;

    // La lista donde guardar los diferentes objetos Cliente.
    public ArrayList<Cliente> clientes = new ArrayList<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        // Si el elemento empieza por "Cliente" significa que empieza un objeto de tipo
        // Cliente
        // Para ir almacenando la informacion mientras parseamos el documento creamos el
        // objeto
        // Para ir despues asignandole los parámetros.
        if (qName.equals("Cliente")) {
            this.currentClient = new Cliente();
        }

        // Bandera para indicar que el atributo por el cual esta pasando es "nombre"
        if (qName.equals("nombre")) {
            this.isName = true;
        }

        // Bandera para indicar que el atributo por el cual esta pasando es "edad"
        if (qName.equals("edad")) {
            this.isAge = true;
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        // Cuando obtengamos "Cliente" como final de elemento significa que hemos ido
        // almacenando su informacion en el current
        // Lo que significa que debemos agregar a la lista el objeto Cliente dado que
        // cuando pase al siguiente objeto
        // creara uno nuevo.
        if (qName.equals("Cliente")) {
            // Lo agregamos a la lista.
            this.clientes.add(this.currentClient);
        }

        // Si es "nombre" volvera a poner el flag en falso para el proximo.
        if (qName.equals("nombre")) {
            this.isName = false;
        }

        // Si es "edad" volvera a poner el flag en falso para el proximo.
        if (qName.equals("edad")) {
            this.isAge = false;
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        // Si hemos detectado alguna bandera en el paso de inicio de elemento podemos
        // obtener su contenido
        if (this.isName) {
            // Cuando detectamos "nombre"
            this.currentClient.name = new String(ch, start, length);
        } else if (this.isAge) {
            // Cuando detectamos "edad"
            this.currentClient.age = Integer.parseInt(new String(ch, start, length));
        }

    }

    @Override
    public void endDocument() throws SAXException {

        // Dado que es final de documento podemos mostrar por pantalla los diferentes
        // objetos Cliente
        // que hemos ido obteniendo.
        for (Cliente f : this.clientes) {

            // Hacemos print del nombre del Cliente
            System.out.println(String.format("Nombre: %s", f.name));

            // Hacemos print de la edad del Cliente
            System.out.println(String.format("Edad: %d", f.age));

            System.out.println("---------------------------------------");
        }

    }

}