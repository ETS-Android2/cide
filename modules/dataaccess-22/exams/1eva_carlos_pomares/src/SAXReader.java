

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class SAXReader extends XMLReader {

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
        SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
        SAXHandler handler = new SAXHandler();
        parser.parse(file, handler);
        return handler.getClientes();
    }
    
}
