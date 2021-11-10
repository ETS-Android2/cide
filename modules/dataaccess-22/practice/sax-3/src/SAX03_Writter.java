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

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class SAX03_Writter {
    
    private final SAX03_Music[] musica;

    public SAX03_Writter(SAX03_Music[] musica) {
        this.musica = musica;
    }

    public Document createDocument() throws Exception {
        
        // Creamos el nuevo Document.
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

        // Creamos el elmento raíz.
        Node root = doc.createElement("Library");

        // Iteramos por cada cliente y agregamos su información.
        for (SAX03_Music m : this.musica) {

            
            Node music = doc.createElement("Music");

            this.appendAttr(doc, music, "category", m.getCategory());
            this.appendAttr(doc, music, "year", String.valueOf(m.getYear()));
            this.appendNode(doc, music, "name", m.getName());
            this.appendNode(doc, music, "group", m.getGroup());
            this.appendNode(doc, music, "album", m.getAlbum());
        
            root.appendChild(music);
        }

        doc.appendChild(root);

        return doc;
    }

    /**
     * 
     * Permite agregar un nodo a un elemento dado su tag name.
     * 
     * @param doc el documento para poder crear elementos.
     * @param root el elemento raíz al cual agregar el nodo.
     * @param tagName el tag name del nuevo nodo.
     * @return El nodo creado y agregado al nodo raíz.
     */
    private Node appendNode(Document doc, Node root, String tagName) {
        Node node = doc.createElement(tagName);
        root.appendChild(node);
        return node;
    }

    /**
     * 
     * Permite agregar un nodo a un elemento y establecerle un valor a su contenido.
     * 
     * @param doc el documento para crear elementos.
     * @param root el nodo al cual agregar el nuevo elemento.
     * @param tagName el tag del nuevo nodo.
     * @param content el contenido del nodo.
     * @return El nodo agregado al elemento raíz.
     */
    private Node appendNode(Document doc, Node root, String tagName, String content) {
        Node node = appendNode(doc, root, tagName);
        node.setTextContent(content);
        return node;
    }

    /**
     * 
     * Permite agregar un attributo a un elemento.
     * 
     * @param doc el documento para crear elementos.
     * @param root el nodo al cual agregar el nuevo elemento.
     * @param tagName el tag del nuevo nodo.
     * @return El nodo agregado al elemento raíz.
     */
    private Attr appendAttr(Document doc, Node root, String tagName) {
        Attr attr = doc.createAttribute(tagName);
        root.getAttributes().setNamedItem(attr);
        return attr;
    }

    /**
     * 
     * Permite agregar un attributo a un elemento y establecerle un valor a su contenido.
     * 
     * @param doc el documento para crear elementos.
     * @param root el nodo al cual agregar el nuevo elemento.
     * @param tagName el tag del nuevo nodo.
     * @param content el contenido del nodo.
     * @return El nodo agregado al elemento raíz.
     */
    private Attr appendAttr(Document doc, Node root, String tagName, String content) {
        Attr attr = appendAttr(doc, root, tagName);
        attr.setTextContent(content);
        return attr;
    }

    /**
     * 
     * Permite crear un archivo XML, dado Documento, el archivo donde almacenar los datos
     * y un parámetro que indicará si a la hora de guardar el contenido desea ser formateado.
     * 
     * @param doc el documento a escribir.
     * @param file el archivo donde escribir los datos.
     * @param format si el contenido ha de estar formateado.
     * @throws IOException si no se puede acceder al archivo.
     * @throws TransformerException si el contenido del Document no puede ser transformado.
     * @throws TransformerConfigurationException si no se puede instanciar el Transformer.
     */
    public static void createFile(Document doc, File file, boolean format) throws IOException, TransformerException, TransformerConfigurationException {

        // Instanciamos un transformer con su Factory.
        Transformer tm = TransformerFactory.newInstance().newTransformer();
        // Establecemos si el contenido es formateado.
        tm.setOutputProperty(OutputKeys.INDENT, format ? "yes" : "no");
        
        // Obtenemos el objeto Source dado el documento.
        Source src = new DOMSource(doc);
        // Obtenemos el objeto Result dado el archivo.
        Result rlt = new StreamResult(file);

        // Transformamos el source con el result.
        tm.transform(src, rlt);

    }

}
