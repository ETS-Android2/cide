import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 * Acceso a Datos - 2021-2022
 * 
 * Práctica DOM
 * 
 * Una clase que permita leer el archivo XML y mostrar por pantalla su contenido.
 * 
 * @author Carlos Pomares (https://www.github.com/pomaretta)
 * 
 */

public class XMLReader {
    
    // El archivo para leer.
    private final File file;
    
    // El documento XML en un objeto Document.
    private Document document;

    /**
     * 
     * XMLReader permite leer un archivo XML y mostrar por pantanlla su contenido.
     * 
     * @param file el archivo a leer.
     */
    public XMLReader(final File file) {
        this.file = file;
    }

    /**
     * 
     * Permite mostrar el contenido del documento XML por pantalla.
     * Mostrará los clientes del archivo.
     * 
     */
    public void showContent() {

        // Obtenemos el elemento raíz del documento.
        Node root = this.document.getDocumentElement();

        // Obtenemos los hijos del nodo raíz.
        NodeList nodes = root.getChildNodes();

        // Iteramos por el contenido de la lista, pasando por cada nodo.
        for (int i = 0; i < nodes.getLength(); i++) {
            
            // Comprovamos que el nodo es un elemento para evitar objetos que no lo son.
            if (nodes.item(i).getNodeType() != Node.ELEMENT_NODE) continue;

            // Obtenemos el nodo como objeto Element
            Element cliente = (Element) nodes.item(i);

            // Mostramos por pantalla el nombre y edad del cliente.
            System.out.println(
                String.format(
                    "%12s %5s",
                    cliente.getElementsByTagName("nombre").item(0).getTextContent(),
                    cliente.getElementsByTagName("edad").item(0).getTextContent()
                )
            );

        }

    }

    /**
     * 
     * Permite leer el archivo del objeto.
     * 
     * @throws IOException If any IO errors occur.
     * @throws SAXException If any parse errors occur.
     * @throws IllegalArgumentException If the file is null.
     * @throws ParserConfigurationException if a DocumentBuilder cannot be created which satisfies the configuration requested.
     */
    public void read() throws IOException, SAXException, IllegalArgumentException, ParserConfigurationException {
        this.document = this.readFile(this.file);
    }

    public Document getDocument() {
        return document;
    }

    /**
     * 
     * Permite instanciar un DocumentBuilder para poder parsear el archivo del objeto.
     * 
     * @return un Document que referencia el contenido del archivo.
     * @throws IOException If any IO errors occur.
     * @throws SAXException If any parse errors occur.
     * @throws IllegalArgumentException If the file is null.
     * @throws ParserConfigurationException if a DocumentBuilder cannot be created which satisfies the configuration requested.
     */
    protected Document readFile(File f) throws IOException, SAXException, IllegalArgumentException, ParserConfigurationException {
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        return db.parse(f);
    }

}
