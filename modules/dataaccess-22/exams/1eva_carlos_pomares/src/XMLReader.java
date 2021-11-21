
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public abstract class XMLReader implements Reader {

    /**
     * 
     * Permite obtener un Document dado un archivo XML.
     * 
     * @param file El fichero XML a leer
     * @return El Document del fichero XML
     */
    public Document getDocument(File file) throws Exception {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        return builder.parse(file);
    }

}
