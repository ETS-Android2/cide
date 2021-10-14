import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * 
 * Acceso a Datos - 2021-2022
 * 
 * Práctica 5
 * 
 * Es demana una programa que faci el següent.
 * 
 * Carregui un xml videjocs.xml
 * Crea un mètode per afegir al XML fills videojoc amb els nodes títol, creador, sinopsis, plataforma i l’atribut creat_en.
 * Crea un mètode per mostrar el contingut del arbre actual.
 * Crea un mètode que rebi per paràmetre el títol d’un videojoc i modifiqui el títol al nou títol també introduït per paràmetre.
 * Crea un mètode que rebi per paràmetre el títol d’un videojoc i l’elimini.
 * Finalment crea el mètode que escrigui l'arbre en un nou XML anomentar modificat.xml.
 *
 * El programa principal haurà de fer:
 *
 * Crear un arbre amb 5 nodes videojoc.
 * Modificar 2 videojocs.
 * Eliminar 1 videojoc.
 * Crear un nou xml amb les modificacions.
 * 
 * @author Carlos Pomares (https://www.github.com/pomaretta)
 */

public class Document_carlos_pomares {

    /**
     * 
     * Load document from a file and return it as DOM Document.
     * 
     * @param f the file to read from.
     * @return a Document object of the file.
     * @throws Exception if cannot be loaded.
     */
    public Document loadDocument(File f) throws Exception {
        // Create a new factory.
        DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
        // Create a builder.
        DocumentBuilder db = df.newDocumentBuilder();
        // Parse document and return it.
        return db.parse(f);
    }

    /**
     * 
     * Create a child game, from a set of parameters given by the user.
     * This will create an XML Element, like this next structure.
     * 
     * <Videogame>
     *      <title />
     *      <creator />
     *      <description />
     *      <platform />
     *      <created_at />
     * </Videogame>
     * 
     * @param d the document for creating elements.
     * @param id the id of the game.
     * @param title the title of the game.
     * @param creator the creator of the game.
     * @param description the description of the game.
     * @param platform the platform of the game.
     * @return A game element.
     * @throws Exception if the document is null.
     */
    public Element createChildGame(Document d,Integer id, String title, String creator, String description, String platform) throws Exception {
        
        // Create the Game element
        // <Videogame />
        Element game = d.createElement("Videogame");
        // Set the ID to the Game.
        game.setAttribute("id", String.valueOf(id));

        // Create title tag.
        Element titleTag = createTextTag(d, "title", title);
        
        // Create creator tag.
        Element creatorTag = createTextTag(d, "creator", creator);
        
        // Create description tag.
        Element descriptionTag = createTextTag(d, "description", description);
        
        // Create platform tag.
        Element platformTag = createTextTag(d, "platform", platform);

        // Get the timestamp.
        String date = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        
        // Create the created_at tag.
        Element dateTag = createTextTag(d, "created_at", date);

        // Append childs
        appendChilds(game,new Object[]{
            titleTag,
            creatorTag,
            descriptionTag,
            platformTag,
            dateTag,
        });
     
        return game;
    }

    /**
     * 
     * Shows the current progress of a Node, priting on System.out
     * 
     * @param n the node to show.
     * @throws Exception if the node is null or cannot be loaded.
     */
    public void showDocument(Node n) throws Exception {
        // Get the source object from node.
        Source src = createSource(n);

        // Create the transformer.
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer tm = tf.newTransformer();

        // Set the output to show with formatting.
        tm.setOutputProperty(OutputKeys.INDENT, "yes");
        
        // Get the result that will be written on.
        Result r = new StreamResult(System.out);

        // Transform, that's gonna do the transformation and will place the content to the Result object.
        tm.transform(src, r);
    }

    /**
     * 
     * Modify a Game Title by given the actual title of the game.
     * Will modify the title.
     * 
     * @param games the element containing the games.
     * @param title the title to search by.
     * @param newTitle the new title to apply.
     * @throws Exception if the game doesn't exists.
     */
    public void modifyGameTitle(Element games, String title, String newTitle) throws Exception{

        // Get the element by his title.
        Element game = (Element) this.getGameByTitle(games, title);
        // If null, return an exception.
        if (game == null) throw new Exception("game is null");
    
        // Get the list of title tags, normally will be always 1.
        NodeList nl = game.getElementsByTagName("title");

        // If no title tag found, throw an error.
        if (nl.getLength() == 0) throw new Exception("no title tag found");
        
        // Get the element (the title)
        Element el = (Element) nl.item(0);

        // Apply the new content.
        el.setTextContent(newTitle);

    }

    /**
     * 
     * Removes a game by his title.
     * 
     * @param root the element containing the game.
     * @param title the title search by.
     * @throws Exception if the game doesn't exists.
     */
    public void removeGameByTitle(Element root, String title) throws Exception {
        // Get the element by his title.
        Element el = (Element) this.getGameByTitle(root, title);
        // Remove from root element.
        root.removeChild(el);
    }

    /**
     * 
     * Create a new file containing the XML elements, this will take an Element 
     * that has all the content to write, and a File to output the content.
     * 
     * @param n the node to write to the file.
     * @param out the file to be written.
     * @throws Exception if the file exists or is a directory.
     */
    public void createNewDocument(Node n, File out) throws Exception {
        // Get the source object.
        Source src = createSource(n);
        // Create a new document (export to file).
        createDocument(src, out);
    }

    /**
     * 
     * Create a new Source object by given Node.
     * 
     * @param n the node.
     * @return A Source object of the Node.
     */
    private Source createSource(Node n) {
        // Return a new Source object.
        return new DOMSource(n);
    }

    /**
     * 
     * Create a document with transformers, taking a Source object and a File object.
     * 
     * @param source the source containing the data.
     * @param out the out file to write the data.
     * @throws Exception if the file is a directory or cannot be created.
     */
    private void createDocument(Source source, File out) throws Exception {
        // Initialize transformer.
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer tm = tf.newTransformer();
        
        // Set output keys to correctly format.
        tm.setOutputProperty(OutputKeys.INDENT, "yes");
        // Get the result object by the file.
        Result r = new StreamResult(out);

        // Transform the Source to the Result.
        tm.transform(source, r);
    }

    /**
     * 
     * Create a text tag by given tagName and content.
     * 
     * @param d the document for creating elements.
     * @param tagName the name of the tag.
     * @param content the content to be in the tag.
     * @return An Element that the node name is the tagName and the node text content is the content.
     */
    private Element createTextTag(Document d, String tagName, String content) {
        // Create a new element with the given tagname.
        Element el = d.createElement(tagName);
        // Create a new textNode with the content.
        Text txt = d.createTextNode(content);
        // Append to new element.
        el.appendChild(txt);
        return el;
    }

    /**
     * 
     * Append an iterable of childs to a given Element.
     * 
     * @param el the element to append to.
     * @param childs the childs to be appended.
     */
    private void appendChilds(Element el, Object[] childs) {
        // Iterate elements and append to the root.
        for (Object o : childs) {
            el.appendChild((Node) o);
        }
    }

    /**
     * 
     * Get a Node of a given Element by his gameTitle, searching for title tag.
     * 
     * @param el the element containing the data.
     * @param gameTitle the title to search by.
     * @return A Node that is the Element that match with the given title.
     * @throws Exception If the element does not exist.
     */
    private Node getGameByTitle(Element el, String gameTitle) throws Exception {
        // New XPath object.
        XPath path = XPathFactory.newInstance().newXPath();
        // Search an individual Node by the XPath expression.
        Node n = (Node) path.evaluate(String.format("//Videogame[title='%s']", gameTitle), el, XPathConstants.NODE);
        return n;
    }

    public static void main(String[] args) throws Exception {
        
        // Create new application.
        Document_carlos_pomares app = new Document_carlos_pomares();

        // Get the document from local source.
        Document d = app.loadDocument(new File("./data.xml"));

        // Obtain the root of the document.
        Element root = d.getDocumentElement();

        // Create five games.
        Element gameOne = app.createChildGame(d, 1,"Avengers: Endgame", "Trashcan", "Fight with dumb people!", "nobody cares");
        Element gameTwo = app.createChildGame(d, 2,"Grand Theft Auto VI", "Tiefstar", "Steal, kill, dye", "PS3/PS4/PS5/+");
        Element gameThree = app.createChildGame(d, 3,"Minecraft", "Mojon", "Craft, live and craft again!", "Java?");
        Element gameFour = app.createChildGame(d, 4,"Resident Evil XI", "Capcum", "Zombies.", "I don't know.");
        Element gameFive = app.createChildGame(d, 5,"New World", "Amojon Games", "Break your GPU!", "Why? I love my RTX 3090");

        // Append games.
        root.appendChild(gameOne);
        root.appendChild(gameTwo);
        root.appendChild(gameThree);
        root.appendChild(gameFour);
        root.appendChild(gameFive);

        // Show the current document progress.
        app.showDocument(root);

        // Modify second game and fourth.
        app.modifyGameTitle(root, "Grand Theft Auto VI", "Grand Theft Auto VII");
        app.modifyGameTitle(root, "Minecraft", "Minecroft");

        // Remove one game.
        app.removeGameByTitle(root, "Minecroft");

        // Show the current document progress.
        app.showDocument(root);

        // Export new changes to new file.
        app.createNewDocument(root, new File("./out.xml"));

    }

}