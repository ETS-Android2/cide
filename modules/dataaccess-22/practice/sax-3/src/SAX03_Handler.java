import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAX03_Handler extends DefaultHandler {
    
    // Banderas para saber porque atributo estamos pasando.
    private boolean isName, isGroupName, isAlbum;
    // La comida que este parseandose en ese momento.
    private SAX03_Music currentMusic;

    // La lista donde guardar los diferentes objetos Cliente.
    public ArrayList<SAX03_Music> musica = new ArrayList<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        // Si el elemento empieza por "Music" significa que empieza un objeto de tipo
        // Musica.
        // Para ir almacenando la informacion mientras parseamos el documento creamos el
        // objeto
        // Para ir despues asignandole los parámetros.
        if (qName.equals("Music")) {
            this.currentMusic = new SAX03_Music();

            String category = attributes.getValue("category");
            String year = attributes.getValue("year");

            if (category != null) {
                this.currentMusic.setCategory(category);
            }

            if (year != null) {
                this.currentMusic.setYear(Integer.parseInt(year));
            }

        }

        // Bandera para indicar que el atributo por el cual esta pasando es "name"
        if (qName.equals("name")) {
            this.isName = true;
        }

        // Bandera para indicar que el atributo por el cual esta pasando es "group"
        if (qName.equals("group")) {
            this.isGroupName = true;
        }

        // Bandera para indicar que el atributo por el cual esta pasando es "album"
        if (qName.equals("album")) {
            this.isAlbum = true;
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        // Cuando obtengamos "Music" como final de elemento significa que hemos ido
        // almacenando su informacion en el current
        // Lo que significa que debemos agregar a la lista el objeto Musica dado que
        // cuando pase al siguiente objeto
        // creara uno nuevo.
        if (qName.equals("Music")) {
            // Lo agregamos a la lista.
            this.musica.add(this.currentMusic);
        }

        // Bandera para indicar que el atributo por el cual esta pasando es "name"
        if (qName.equals("name")) {
            this.isName = false;
        }

        // Bandera para indicar que el atributo por el cual esta pasando es "group"
        if (qName.equals("group")) {
            this.isGroupName = false;
        }

        // Bandera para indicar que el atributo por el cual esta pasando es "album"
        if (qName.equals("album")) {
            this.isAlbum = false;
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        // Si hemos detectado alguna bandera en el paso de inicio de elemento podemos
        // obtener su contenido
        if (this.isName) {
            // Cuando detectamos "nombre"
            this.currentMusic.setName(new String(ch, start, length)); 
        } else if (this.isGroupName) { 
            // Cuando detectamos "group"
            this.currentMusic.setGroup(new String(ch, start, length));
        } else if (this.isAlbum) {
            // Cuando detectamos "album"
            this.currentMusic.setAlbum(new String(ch, start, length));
        }

    }

    @Override
    public void endDocument() throws SAXException {

        // Cuando terminamos de parsear el documento podemos imprimir la lista de Musica
        for (SAX03_Music m : this.musica) {

            // Mostramos por pantalla el objeto Musica
            System.out.println(String.format("Name: %s", m.getName()));
            System.out.println(String.format("Category: %s", m.getCategory()));
            System.out.println(String.format("Group: %s", m.getGroup()));
            System.out.println(String.format("Album: %s", m.getAlbum()));
            System.out.println(String.format("Year: %d", m.getYear()));

            System.out.println("---------------------------------------");
        }

    }

}
