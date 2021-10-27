import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAX01_carlos_pomares {
    
    public static void main(String[] args) throws Exception {
        
        // Creamos el handler
        SAXHandler_carlos_pomares mn = new SAXHandler_carlos_pomares();

        // Creamos el parser
        SAXParser sax = SAXParserFactory.newInstance().newSAXParser();

        // Parseamos el archivo con el handler.
        sax.parse(new File("./breakfast.xml"), mn);

    }

}

class SAXHandler_carlos_pomares extends DefaultHandler {

    // Clase Food para ir almacenando datos.
    class Food {
        public String name;
        public String price;
        public String description;
        public String calories;
    }

    // Banderas para saber porque atributo estamos pasando.
    private boolean isName, isPrice, isDescription, isCalories;
    // La comida que este parseandose en ese momento.
    private Food currentFood;

    // La lista donde guardar los diferentes objetos comida.
    public ArrayList<Food> food = new ArrayList<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        // Si el elemento empieza por "food" significa que empieza un objeto de tipo Food
        // Para ir almacenando la informacion mientras parseamos el documento creamos el objeto
        // Para ir despues asignandole los parametros.
        if(qName.equals("food")) {
            this.currentFood= new Food();
        }

        // Bandera para indicar que el atributo por el cual esta pasando es "name"
        if (qName.equals("name")) { this.isName = true; }

        // Bandera para indicar que el atributo por el cual esta pasando es "price"
        if (qName.equals("price")) { this.isPrice = true; }

        // Bandera para indicar que el atributo por el cual esta pasando es "description"
        if (qName.equals("description")) { this.isDescription = true; }

        // Bandera para indicar que el atributo por el cual esta pasando es "calories"
        if (qName.equals("calories")) { this.isCalories = true; }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        
        // Cuando obtengamos "food" como final de elemento significa que hemos ido almacenando su informacion en el current
        // Lo que significa que debemos agregar a la lista el objeto Food dado que cuando pase al siguiente objeto
        // creara uno nuevo.
        if(qName.equals("food")) {
            // Lo agregamos a la lista.
            this.food.add(this.currentFood);
        }

        // Si es "name" volvera a poner el flag en falso para el proximo.
        if (qName.equals("name")) { this.isName = false; }
        
        // Si es "price" volvera a poner el flag en falso para el proximo.
        if (qName.equals("price")) { this.isPrice = false; }
        
        // Si es "description" volvera a poner el flag en falso para el proximo.
        if (qName.equals("description")) { this.isDescription = false; }
        
        // Si es "calories" volvera a poner el flag en falso para el proximo.
        if (qName.equals("calories")) { this.isCalories = false; }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        
        // Si hemos detectado alguna bandera en el paso de inicio de elemento podemos obtener su contenido

        if (this.isName) {
            // Cuando detectamos "name
            this.currentFood.name = new String(ch, start, length);
        } else if (this.isPrice) {
            // Cuando detectamos "price"
            this.currentFood.price = new String(ch, start, length);
        } else if (this.isDescription) {
            // Cuando detectamos "description"
            this.currentFood.description = new String(ch, start, length);
        } else if (this.isCalories) {
            // Cuando detectamos "calories"
            this.currentFood.calories = new String(ch, start, length);
        }

    }

    @Override
    public void endDocument() throws SAXException {
        
        // Dado que es final de documento podemos mostrar por pantalla los diferentes objetos Food
        // que hemos ido obteniendo.
        for(Food f : this.food) {
            
            // Hacemos print del nombre de la comida
            System.out.println(
                String.format("Name: %s", f.name));
            
                // Hacemos print del precio de la comida
            System.out.println(
                String.format("Price: %s", f.price));
            
                // Hacemos print de la descripcion de la comida
            System.out.println(
                String.format("Description: %s", f.description));

            // Hacemos print de las calorias de la comida
            System.out.println(
                String.format("Calories: %s", f.calories));
            
            System.out.println("---------------------------------------");
        }

    }

}