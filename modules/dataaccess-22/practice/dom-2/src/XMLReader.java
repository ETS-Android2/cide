import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLReader {
    
    private final File file;
    
    private Document document;

    public XMLReader(final File file) {
        this.file = file;
    }

    public void showContent() {

        Node root = this.document.getDocumentElement();

        NodeList nodes = root.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            
            if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                
                Element cliente = (Element) nodes.item(i);

                System.out.println(
                    String.format(
                        "%12s %5s",
                        cliente.getElementsByTagName("nombre").item(0).getTextContent(),
                        cliente.getElementsByTagName("edad").item(0).getTextContent()
                    )
                );

            }

        }

    }

    public void read() throws Exception {
        this.document = this.readFile();
    }

    private Document readFile() throws Exception {
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        return db.parse(this.file);
    }

}
