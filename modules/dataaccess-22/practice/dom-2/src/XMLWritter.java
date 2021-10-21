import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class XMLWritter {

    private final Cliente[] clientes;

    public XMLWritter(Cliente[] clientes) {
        this.clientes = clientes;
    }

    public Document createDocument() throws Exception {

        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

        Node root = doc.createElement("Clientes");

        for (Cliente c : this.clientes) {

            Node cliente = doc.createElement("Cliente");

            this.appendNode(doc, cliente, "nombre", c.getNombre());
            this.appendNode(doc, cliente, "edad", String.valueOf(c.getEdad()));

            root.appendChild(cliente);

        }

        doc.appendChild(root);

        return doc;
    }

    private Node appendNode(Document doc, Node root, String tagName) {
        Node node = doc.createElement(tagName);
        root.appendChild(node);
        return node;
    }

    private Node appendNode(Document doc, Node root, String tagName, String content) {
        Node node = appendNode(doc, root, tagName);
        node.setTextContent(content);
        return node;
    }

    public static void createFile(Document doc, File file, boolean format) throws IOException, TransformerException, TransformerConfigurationException {

        Transformer tm = TransformerFactory.newInstance().newTransformer();
        tm.setOutputProperty(OutputKeys.INDENT, format ? "yes" : "no");
        
        Source src = new DOMSource(doc);
        Result rlt = new StreamResult(file);

        tm.transform(src, rlt);
    }

}
