

import java.io.File;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public abstract class XMLWriter implements Writer {

    public void writeDocument(Document doc, File file) throws Exception {
        Transformer tm = TransformerFactory.newInstance().newTransformer();
        
        // Set output keys to correctly format.
        // tm.setOutputProperty(OutputKeys.INDENT, "yes");
        tm.setOutputProperty(OutputKeys.METHOD, "xml");
        
        // Transform the Source to the Result.
        tm.transform(new DOMSource(doc), new StreamResult(file));
    }
    
}
