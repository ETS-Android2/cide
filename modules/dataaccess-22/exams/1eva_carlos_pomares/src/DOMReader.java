
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class DOMReader extends XMLReader {

    /**
     * 
     * Permite leer un archivo XML y obtener el Document.
     * 
     * @param file Fichero XML
     * @return Lista de alumnos
     * @throws ParserConfigurationException Si no se puede obtener la configuración del parser
     * @throws SAXException Si no se puede leer el contenido del fichero
     * @throws IOException Si no se puede leer el fichero
     */
    private Document readFile(File file) throws IOException, ParserConfigurationException, SAXException {
        return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
    }

    @Override
    public Document getDocument(File file) throws Exception {
        return readFile(file);
    }

    @Override
    public Alumno[] read(File file) throws Exception {

        // Obtenemos el documento
        Document doc = this.readFile(file);

        // Obtenemos el nodo raíz.
        Node root = doc.getDocumentElement();

        // Si no tiene hijos (Alumnos), devolvemos un objeto nulo.
        if (!root.hasChildNodes()) return null;

        // Una lista de alumnos.
        ArrayList<Alumno> alumnos = new ArrayList<Alumno>();

        // Recorremos los hijos del nodo raíz.
        for (int i = 0; i < root.getChildNodes().getLength(); i++) {
            
            Alumno alumno = new Alumno();
            // Obtenemos el nodo hijo.
            Node n = root.getChildNodes().item(i);

            // Si el nodo no es un elemento, continuamos.
            if (n.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            // Obtenemos el identificador del alumno.
            Attr id = (Attr) n.getAttributes().getNamedItem("codi_alumne");

            // Si tiene identificador, lo añadimos al alumno.
            if (id != null) {
                alumno.setId(Integer.parseInt(id.getValue()));
            }

            // Recorremos los hijos del nodo. (La información del alumno)
            for (int j = 0; j < n.getChildNodes().getLength(); j++) {
                
                // Obtenemos el nodo hijo.
                Node nl = n.getChildNodes().item(j);

                // Si el nodo no es un elemento, continuamos.
                if (nl.getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }

                // Obtenemos el nombre del nodo para poder asignarlo al alumno.
                switch (nl.getNodeName().toString()) {
                    case "nom_alumne":
                        // Si es el nombre, lo añadimos al alumno.
                        alumno.setName(nl.getTextContent());
                        break;
                    case "curs":
                        // Si es el curso, lo añadimos al alumno.
                        alumno.setGrade(nl.getTextContent());
                        break;
                    case "any_naixement":
                        // Si es el año de nacimiento, lo añadimos al alumno.
                        alumno.setYear(nl.getTextContent());
                        break;
                    case "colegi":
                        // Si es el colegio, lo añadimos al alumno.
                        alumno.setSchool(nl.getTextContent());
                        break;
                }

            }

            alumnos.add(alumno);
        }

        // Devolvemos un array simple para cumplir la interfaz.
        if (alumnos.size() > 0) {
            return alumnos.toArray(new Alumno[alumnos.size()]);
        } else {
            return null;
        }
    }
    
}
