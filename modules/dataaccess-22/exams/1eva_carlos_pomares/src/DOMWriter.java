
import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class DOMWriter extends XMLWriter {

    /**
     * 
     * Permite escribir un conjunto de alumnos en un fichero XML
     * 
     * @param doc Documento XML
     * @param file Fichero a escribir
     * @throws Exception Si ocurre algún error
     */
    private void createFile(Document doc, File file) throws Exception {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(doc), new StreamResult(file));
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
        Node node = this.appendNode(doc, root, tagName);
        node.setTextContent(content);
        return node;
    }

    /**
     * 
     * Permite agregar un atributo a un nodo.
     * 
     * @param doc el documento para crear elementos.
     * @param node el nodo al cual agregar el atributo.
     * @param name el nombre del atributo.
     * @param value el valor del atributo.
     * @return El atributo agregado al nodo.
     */
    private Attr appendAttribute(Document doc, Node node, String name, String value) {
        Attr attr = doc.createAttribute(name);
        attr.setValue(value);
        node.getAttributes().setNamedItem(attr);
        return attr;
    }

    /**
     * 
     * Crea un documento XML en Objeto Document, el cual sigue la estructura
     * especificada en el ejercicio.
     * 
     * @return Un documento.
     * @throws Exception Si el documento no puede ser creado.
     */
    private Document createDocument(Alumno[] alumnos) throws Exception {

        // Creamos el nuevo Document.
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

        // Creamos el elmento raíz (Clientes).
        Node root = doc.createElement("registre_alumnes");

        // Iteramos por cada cliente y agregamos su información.
        for (Alumno c : alumnos) {

            // Creamos el elemento Cliente, el cual tendrá su información dentro.
            Node cliente = doc.createElement("alumnes");

            this.appendAttribute(doc, cliente, "codi_alumne", String.valueOf(c.getId()));

            // Agregamos el nodo Nombre con su información dentro.
            this.appendNode(doc, cliente, "nom_alumne", c.getName());
            this.appendNode(doc, cliente, "curs", c.getGrade());
            this.appendNode(doc, cliente, "any_naixement", c.getYear());
            this.appendNode(doc, cliente, "colegi", c.getSchool());

            // Agregamos el cliente a la raíz.
            root.appendChild(cliente);

        }

        // Agregamos la raíz al documento.
        doc.appendChild(root);

        // Devolvemos el documento con el contenido.
        return doc;
    }

    @Override
    public void write(Alumno[] alumnos, File file) throws Exception {
        Document doc = this.createDocument(alumnos);
        doc.normalize();
        this.createFile(doc, file);
    }

}
