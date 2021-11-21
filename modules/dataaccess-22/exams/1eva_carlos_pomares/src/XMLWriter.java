

import java.io.File;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public abstract class XMLWriter implements Writer {

    /**
     * 
     * Permite escribir un Document a un archivo XML.
     * 
     * @param doc El documento a escribir
     * @param file El fichero XML donde escribir
     * @throws Exception Si no se puede escribir el documento
     */
    public void writeDocument(Document doc, File file) throws Exception {
        Transformer tm = TransformerFactory.newInstance().newTransformer();
        tm.setOutputProperty(OutputKeys.METHOD, "xml");
        tm.transform(new DOMSource(doc), new StreamResult(file));
    }
    
}
