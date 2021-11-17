import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * Acceso a Datos -- 2021/2022
 * 
 * A partir del fichero futbol.xml hacer un programa que recorra el fichero 
 * y podamos hacer búsquedas de información dentro del contenido del fichero XML usando XPATH.
 * 
 * @author Carlos Pomares (https://www.github.com/pomaretta)
 */

public class XPath_carlos_pomares {

    /**
     * 
     * Permite leer un fichero XML y devuelve un documento.
     * 
     * @param f El archivo XML a leer
     * @return El documento XML
     */
    private Document readDocument(File f) throws Exception {
        // Create a new factory.
        DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
        // Create a builder.
        DocumentBuilder db = df.newDocumentBuilder();
        // Parse document and return it.
        return db.parse(f);
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
    private NodeList searchNodeList(String toSearch, Node el) throws Exception {
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
    private Node searchNode(String toSearch, Node el) throws Exception {
        // New XPath object.
        XPath path = XPathFactory.newInstance().newXPath();
        return (Node) path.evaluate(toSearch, el, XPathConstants.NODE);
    }

    /**
     * 
     * Permite obtener un array de Nodos dado un NodeList.
     * 
     * @param list El NodeList a convertir en array
     * @return El array de Nodos
     */
    private Node[] mapElements(NodeList list) {
        Node[] elements = new Node[list.getLength()];
        for (int i = 0; i < list.getLength(); i++) {
            elements[i] = list.item(i);
        }
        return elements;
    }

    /**
     * 
     * Permite mostrar el documento XML completo.
     * 
     * @param doc El documento XML
     * @throws Exception Si hay algún error al mostrar el documento
     */
    private void showDocument(Document doc) throws Exception {

        // Obtenemos el elemento raíz.
        NodeList nodes = searchNodeList("//futbol/*", doc.getDocumentElement());

        // Iteramos las selecciones.
        for (Node selection : this.mapElements(nodes)) {

            if (selection.getNodeType() != Node.ELEMENT_NODE) continue;

            Element el = (Element) selection;

            // Mostramos la selección
            System.out.println(String.format(
                "%sSelección: %s",
                "",
                el.getAttribute("nombre")
            ));

            // Si no tiene hijos, continuamos.
            if (!selection.hasChildNodes()) continue;

            // Iteramos las ligas.
            for (Node league : this.mapElements(selection.getChildNodes())) {

                if (league.getNodeType() != Node.ELEMENT_NODE) continue;

                el = (Element) league;

                // Mostramos la selección
                System.out.println(String.format(
                    "%sLiga: %s",
                    "\t",
                    el.getAttribute("nombre")
                ));
    
                // Si no tiene hijos, continuamos.
                if (!league.hasChildNodes()) continue;

                // Iteramos los equipos.
                for (Node team : this.mapElements(league.getChildNodes())) {

                    if (team.getNodeType() != Node.ELEMENT_NODE) continue;

                    el = (Element) team;
    
                    // Mostramos la selección
                    System.out.println(String.format(
                        "%sEquipo: %s",
                        "\t\t",
                        el.getAttribute("nombre")
                    ));
        
                    // Si no tiene hijos, continuamos.
                    if (!team.hasChildNodes()) continue;
    
                    // Iteramos los jugadores.
                    for (Node player : this.mapElements(team.getChildNodes())) {

                        if (player.getNodeType() != Node.ELEMENT_NODE) continue;

                        el = (Element) player;
        
                        // Mostramos la selección
                        System.out.println(String.format(
                            "%sJugador: %s",
                            "\t\t\t",
                            el.getAttribute("nombre")
                        ));

                    }
    
                }

            }

        }

    }

    /**
     * 
     * Permite mostrar el atributo de un nodo.
     * 
     * @param el El nodo
     * @param attribute El atributo a mostrar
     * @throws Exception Si hay algún error al mostrar el atributo
     */
    private void showElement(Node el, String attribute) throws Exception {
        
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
     * Menu de opciones para filtrar.
     * 
     * @param doc El documento XML
     * @throws Exception Si hay algún error.
     */
    private void searchMenu(Document doc) throws Exception {

        boolean flag = false;

        // Las opciones del menu.
        String[] options = {
            "Buscar por selección",
            "Buscar por liga",
            "Buscar por equipo",
            "Buscar por jugador",
            "Salir"
        };

        do {

            // Mostramos las opciones.
            // Con el formato: [opcion]. [valor]
            for (int i = 0; i < options.length; i++) {
                System.out.println(String.format(
                    "%d. %s",
                    (i + 1),
                    options[i]
                ));
            }

            System.out.print("Seleccione una opción: ");

            int option = 0;

            try {
                option = Integer.parseInt(System.console().readLine());
            } catch (Exception e) {
                System.out.println("Opción inválida");
                continue;
            }

            try {

                switch (option) {
                    // Buscar por selección.
                    case 1:
                        System.out.print("Selección: ");
                        String selection = System.console().readLine();
                        this.showElement(
                            this.searchNode(String.format("//seleccion[@nombre='%s']", selection), doc.getDocumentElement())
                            ,"nombre"
                        );
                        break;
                    // Buscar por liga.
                    case 2:
                        System.out.print("Liga: ");
                        String league = System.console().readLine();
                        this.showElement(
                            this.searchNode(String.format("//liga[@nombre='%s']", league), doc.getDocumentElement())
                            ,"nombre"
                        );
                        break;
                    // Buscar por equipo.
                    case 3:
                        System.out.print("Equipo: ");
                        String team = System.console().readLine();
                        this.showElement(
                            this.searchNode(String.format("//equipo[@nombre='%s']", team), doc.getDocumentElement())
                            ,"nombre"
                        );
                        break;
                    // Buscar por jugador.
                    case 4:
                        System.out.print("Jugador: ");
                        String player = System.console().readLine();
                        this.showElement(
                            this.searchNode(String.format("//jugador[@nombre='%s']", player), doc.getDocumentElement())
                            ,"nombre"
                        );
                        break;
                    // Salir.
                    case 5:
                        flag = true;
                        break;
                    default:
                        System.out.println("Opción inválida");
                        break;
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage()); 
                continue;
            }

        } while (!flag);

    }

    /**
     * 
     * Bucle principal, se encarga de pedir al usuario opciones y ejecutar las acciones.
     * Las opciones son:
     *  - Mostrar el documento XML completo
     *  - Filtrar por selección
     *  - Salir
     * 
     * @param doc El documento XML
     */
    public void mainLoop(Document doc) {
        
        boolean flag = false;

        String[] options = {
            "Mostrar documento",
            "Buscar elemento",
            "Salir",
        };

        do {

            // Mostramos un mensaje con el texto en el centro rodeado de "-"
            System.out.println(
                String.format(
                    "---- %s ----\n",
                    "MENÚ"
                )
            );

            // Mostramos las opciones
            for (int i = 0; i < options.length; i++) {
                System.out.println(String.format(
                    "%d. %s",
                    (i + 1),
                    options[i]
                ));
            }

            // Pedimos una opción
            System.out.print("\nOpción: ");
            int option = 0;

            try {
                option = Integer.parseInt(System.console().readLine());
            } catch (Exception e) {
                System.out.println("Opción no válida");
                continue;
            }

            try {
                switch (option) {
                    case 1:
                        this.showDocument(doc);
                        break;
                    case 2:
                        this.searchMenu(doc);
                        break;
                    case 3:
                        flag = true;
                        break;
                    default:
                        System.out.println("Opción no válida");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (!flag);
        
    }

    public static void main(String[] args) throws Exception {

        // Instanciamos la aplicación.
        XPath_carlos_pomares app = new XPath_carlos_pomares();

        Document doc = null;

        // Leemos el documento XML
        try {
            doc = app.readDocument(new File("./data.xml"));
        } catch (Exception e) {
            System.out.println("No se puede leer el documento");
            System.exit(0);
        }

        // Iniciamos el bucle principal
        app.mainLoop(doc);
    }
}
