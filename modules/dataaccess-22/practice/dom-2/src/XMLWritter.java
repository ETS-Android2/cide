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

/**
 * 
 * Acceso a Datos - 2021-2022
 * 
 * Práctica DOM
 * 
 * Una clase que nos permita crear el archivo XML a partir de la información de un número de clientes.
 * 
 * @author Carlos Pomares (https://www.github.com/pomaretta)
 * 
 */
public class XMLWritter {

    // Los clientes a escribir.
    private final Cliente[] clientes;

    /**
     * 
     * XMLWritter permite crear un archivo XML con los diferentes clientes
     * que se le pasen como parámetros.
     * 
     * @param clientes los clientes a utilizar para crear el archivo XML.
     */
    public XMLWritter(Cliente[] clientes) {
        this.clientes = clientes;
    }

    /**
     * 
     * Creamos un documento XML en Objeto Document, el cual sigue la estructura
     * especificada en el ejercicio
     * 
     * Clientes
     *  - Cliente
     *      - Nombre
     *      - Edad
     * 
     * @return Un documento que sigue la estructura.
     * @throws Exception Si el documento no puede ser creado.
     */
    public Document createDocument() throws Exception {

        // Creamos el nuevo Document.
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

        // Creamos el elmento raíz (Clientes).
        Node root = doc.createElement("Clientes");

        // Iteramos por cada cliente y agregamos su información.
        for (Cliente c : this.clientes) {

            // Creamos el elemento Cliente, el cual tendrá su información dentro.
            Node cliente = doc.createElement("Cliente");

            // Agregamos el nodo Nombre con su información dentro.
            this.appendNode(doc, cliente, "nombre", c.getNombre());
            // Agregamos el nodo Edad con su información dentro.
            this.appendNode(doc, cliente, "edad", String.valueOf(c.getEdad()));

            // Agregamos el cliente a la raíz.
            root.appendChild(cliente);

        }

        // Agregamos la raíz al documento.
        doc.appendChild(root);

        // Devolvemos el documento con el contenido.
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
