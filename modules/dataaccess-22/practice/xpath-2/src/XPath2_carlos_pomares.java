import java.io.File;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XPath2_carlos_pomares {
    
    /**
     * 
     * Permite leer un fichero XML y devuelve un documento.
     * 
     * @param f El archivo XML a leer
     * @return El documento XML
     */
    public Document readDocument(File f) throws Exception {
        // Create a new factory.
        DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
        // Create a builder.
        DocumentBuilder db = df.newDocumentBuilder();
        // Parse document and return it.
        return db.parse(f);
    }


    /**
     * 
     * Permite obtener un nodo de un documento XML.
     * 
     * @param toSearch La expresión XPath a buscar
     * @param element El elemento del documento XML
     * @param type El tipo de nodo a buscar
     * @return El nodo encontrado
     * @throws Exception
     */
    public Object search(String toSearch, Node el, QName type) throws Exception {
        XPath path = XPathFactory.newInstance().newXPath();
        return path.evaluate(toSearch, el, type);
    }

    /**
     * 
     * Permite obtener una lista de nodos a partir de una expresión XPath.
     * 
     * @param toSearch La expresión XPath
     * @param el El elemento raíz
     * @return La lista de nodos
     * @throws Exception Si hay algún error en la expresión
     */
    public NodeList searchNodeList(String toSearch, Node el) throws Exception {
        // New XPath object.
        XPath path = XPathFactory.newInstance().newXPath();
        return (NodeList) path.evaluate(toSearch, el, XPathConstants.NODESET);
    }

    /**
     * 
     * Permite obtener un nodo a partir de una expresión XPath.
     * 
     * @param toSearch La expresión XPath
     * @param el El elemento raíz
     * @return El nodo
     * @throws Exception Si hay algún error en la expresión
     */
    public Node searchNode(String toSearch, Node el) throws Exception {
        // New XPath object.
        XPath path = XPathFactory.newInstance().newXPath();
        return (Node) path.evaluate(toSearch, el, XPathConstants.NODE);
    }

    /**
     * 
     * Permite mostrar el atributo de un nodo.
     * 
     * @param el El nodo
     * @param attribute El atributo a mostrar
     * @throws Exception Si hay algún error al mostrar el atributo
     */
    public void showElementAttribute(Node el, String attribute) throws Exception {
        
        if (el.getNodeType() != Node.ELEMENT_NODE) return;

        // Casteamos el nodo a elemento.
        Element e = (Element) el;

        // Mostramos el atributo.
        System.out.println(String.format(
            "$ %s -> %s: %s",
            e.getNodeName(),
            attribute,
            e.getAttribute(attribute)
        ));

    }

    /**
     * 
     * Permite mostrar el texto de un nodo.
     * 
     * @param el El nodo
     * @throws Exception Si hay algún error al mostrar el atributo
     */
    public void showElement(Node el) throws Exception {
        
        if (el.getNodeType() != Node.ELEMENT_NODE) return;

        // Casteamos el nodo a elemento.
        Element e = (Element) el;

        // Mostramos el atributo.
        System.out.println(String.format(
            "$ %s -> %s",
            e.getNodeName(),
            e.getTextContent()
        ));

    }

    public static void main(String[] args) throws Exception {
        
        XPath2_carlos_pomares app = new XPath2_carlos_pomares();

        Document doc = null;
        
        doc = app.readDocument(new File("./datos.xml"));

        // Sacar el nombre de un trabajador a partir de su ID.
        int idTrabajador = 1;
        Node nombreTrabajador = app.searchNode(
            String.format(
                "//Trabajador[@id=%d]/nombre",
                idTrabajador
            ),
            doc.getDocumentElement()
        );
        app.showElement(nombreTrabajador);

        // Sacar el listado de trabajadores mayores de 30 años.
        NodeList listado = (NodeList) app.search(
            "//Trabajador[edad>30]",
            doc.getDocumentElement(),
            XPathConstants.NODESET
        );

        for (int i = 0; i < listado.getLength(); i++) {
            app.showElement(listado.item(i));
        }

        // Sacar el listado de las trabajadoras que hay en la empresa.
        listado = (NodeList) app.search(
            "//Trabajador[genero='Mujer']",
            doc.getDocumentElement(),
            XPathConstants.NODESET
        );

        for (int i = 0; i < listado.getLength(); i++) {
            app.showElement(listado.item(i));
        }

        // Sacar el listado de los trabajadores que tienen como rol  “Java Developer”.

        listado = (NodeList) app.search(
            "//Trabajador[rol='Java Developer']",
            doc.getDocumentElement(),
            XPathConstants.NODESET
        );

        for (int i = 0; i < listado.getLength(); i++) {
            app.showElement(listado.item(i));
        }

    }

}
