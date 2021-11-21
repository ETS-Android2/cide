
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

        Document doc = this.readFile(file);

        Node root = doc.getDocumentElement();

        if (!root.hasChildNodes()) return null;

        ArrayList<Alumno> alumnos = new ArrayList<Alumno>();

        for (int i = 0; i < root.getChildNodes().getLength(); i++) {
            
            Alumno alumno = new Alumno();
            Node n = root.getChildNodes().item(i);

            if (n.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            // Get the id attribute
            Attr id = (Attr) n.getAttributes().getNamedItem("codi_alumne");

            if (id != null) {
                alumno.setId(Integer.parseInt(id.getValue()));
            }

            for (int j = 0; j < n.getChildNodes().getLength(); j++) {
                
                Node nl = n.getChildNodes().item(j);

                if (nl.getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }

                switch (nl.getNodeName().toString()) {
                    case "nom_alumne":
                        alumno.setName(nl.getTextContent());
                        break;
                    case "curs":
                        alumno.setGrade(nl.getTextContent());
                        break;
                    case "any_naixement":
                        alumno.setYear(nl.getTextContent());
                        break;
                    case "colegi":
                        alumno.setSchool(nl.getTextContent());
                        break;
                }

            }

            alumnos.add(alumno);
        }

        return alumnos.toArray(new Alumno[alumnos.size()]);
    }
    
}
