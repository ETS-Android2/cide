
import java.io.File;

import org.w3c.dom.Document;

public interface Reader {
    /**
     * 
     * Permite obtener un documento DOM a partir de un fichero XML
     * 
     * @param file Fichero XML
     * @return Documento DOM
     * @throws Exception Si no se puede leer el fichero o no es un formato XML
     */
    Document getDocument(File file) throws Exception;
    /**
     * 
     * Permite leer un conjunto de alumnos de un fichero XML
     * 
     * @param file Fichero XML
     * @return Un array de alumnos
     * @throws Exception Si ocurre algún error
     */
    Alumno[] read(File file) throws Exception;
}
