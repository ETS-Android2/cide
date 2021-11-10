import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;

public class SAX03_carlos_pomares extends JFrame {

    /**
     * Properties identifica las diferentes propiedades que se van a pedir
     * al usuario.
     */
    enum Properties {
        GROUP (0),
        CATEGORY (1),
        NAME (2), 
        ALBUM (3),
        YEAR (4);

        private int value;

        private Properties(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }


    /**
     * 
     * Permite asignar un valor a una propiedad de Musica, en función de la
     * posición que ocupa en el ArrayList.
     * 
     * @param musica El objeto de tipo Musica que se va a modificar.
     * @param property La propiedad que se va a modificar.
     * @param value El valor que se va a asignar a la propiedad.
     * @throws Exception Si la propiedad no existe.
     */
    private void setProperty(SAX03_Music musica, Properties property, Object value) throws Exception {
        switch (property) {
            case NAME:
                musica.setName((String) value);
                break;
            case CATEGORY:
                musica.setCategory((String) value);
                break;
            case ALBUM:
                musica.setAlbum((String) value);
                break;
            case GROUP:
                musica.setGroup((String) value);
                break;
            case YEAR:
                musica.setYear(Integer.parseInt((String) value));
                break;
            default:
                throw new Exception("Property not found");
        }
    }

    /**
     * 
     * Permite obtener datos de música del usuario y crear un XML con los datos aportados.
     * Una vez generado el archivo, utilizando SAX, se muestra en pantalla.
     * 
     */
    public void mainLoop() {

        boolean exit = false;
        int option = 0;
        ArrayList<SAX03_Music> music = new ArrayList<SAX03_Music>();

        // Los mensajes que se pedirán al usuario.
        String[] messages = new String[]{
            "Nombre del grupo",
            "Categoria a la que pertenece",
            "Nombre de la canción",
            "Nombre del álbum al que pertenece la canción",
            "Año de publicación",
        };

        SAX03_Music currentMusic = new SAX03_Music();

        do {

            if (option == 5) {
                music.add(currentMusic);

                // Preguntar si quiere seguir introduciendo más canciones
                option = JOptionPane.showConfirmDialog(this, "¿Desea introducir otra canción?", "Nueva canción", JOptionPane.YES_NO_OPTION);

                
                if (option == JOptionPane.YES_OPTION) {
                    // Si quiere seguir introduciendo más canciones, se crea una nueva instancia de Musica
                    currentMusic = new SAX03_Music();
                    option = 0;
                } else {
                    // Si no quiere seguir, se sale del bucle
                    exit = true;
                }
                
                continue;
            }
            
            // Pedir al usuario que ingrese un valor dado el paso en el que se encuentra con un JOptionPane
            String value = JOptionPane.showInputDialog(this, messages[option]);

            try {
                // Asignar el valor al objeto Musica
                setProperty(currentMusic, Properties.values()[option], value);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
                continue;
            }

            // Incrementar el paso en el que se encuentra el usuario
            option++;

        } while (!exit);

        SAX03_Writter writer = new SAX03_Writter(music.toArray(new SAX03_Music[music.size()]));
        Document doc = null; 

        try {
            // Crear el documento XML
            doc = writer.createDocument();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

        try {
            SAX03_Writter.createFile(doc, new File("./datos.xml"), true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

        // Mostrar el documento XML
        SAX03_Handler handler = new SAX03_Handler();
        SAXParser sax = null;

        try {
            sax = SAXParserFactory.newInstance().newSAXParser();
            sax.parse(new File("./datos.xml"), handler);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }


        // Cerrar la ventana
        this.dispose();

    }

    public static void main(String[] args) {
        SAX03_carlos_pomares app = new SAX03_carlos_pomares();
        app.mainLoop();
    }
    
}