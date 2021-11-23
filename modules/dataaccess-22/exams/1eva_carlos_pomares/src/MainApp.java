

import java.io.File;
import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * Examen 1 evaluación -- Carlos Pomares
 * Contexto SAX y XPath
 * 
 * Programa para gestionar alumnos en archivos XML.
 * 
 * @author Carlos Pomares (https://www.github.com/pomaretta)
 * 
 */

public class MainApp extends JFrame {
    
    // Para poder identificar que motor usar.
    enum Writers {
        DOM, SAX
    }
    
    // El writer que permitirá escribir en el documento.
    private XMLWriter writer;
    // El reader que permitirá obtener datos del documento.
    private XMLReader reader;

    // El archivo con el cual operar.
    private File file;
    // Los últimos datos leídos de Alumnos.
    private Alumno[] lastCurrentAlumnos;

    /**
     * 
     * Permite establecer que motor se utilizará para escribir y leer.
     * 
     * @param writer El motor que se utilizará para escribir.
     */
    private void setWriter(Writers writer) {
        switch (writer) {
            case DOM:
                this.writer = new DOMWriter();
                this.reader = new DOMReader();
                break;
            case SAX:
                this.writer = new SAXWriter();
                this.reader = new SAXReader();
                break;
        }
    }

    /**
     * 
     * Pide al usuario un input, y devolverá el dato introducido.
     * 
     * @param message el mensaje a mostrar al usuario.
     * @return el dato introducido por el usuario.
     */
    private String requestUserInput(String message) {
        return JOptionPane.showInputDialog(this, message); 
    }

    /**
     * 
     * Establece el archivo con el que operar. Si el archivo existe, devolverá una excepción después de haber asignado el archivo.
     * 
     * @param file el archivo con el que operar.
     * @throws Exception si el archivo existe.
     */
    private void setFile(File file) throws Exception {
        this.file = file;
        if (file.exists()) {
            throw new Exception("File exists");
        }
    }

    /**
     * 
     * Permite pedir al usuario toda la información necesaria para poder obtener un Objeto Alumno.
     * 
     * @return Un Objeto Alumno con toda la información necesaria.
     */
    private Alumno requestAlumno() {

        boolean flag = false;
        int step = 0;

        String[] options = {
            "El identificador del alumno",
            "El nombre del alumno",
            "El curso del alumno",
            "El año de nacimiento del alumno",
            "El colegio del alumno"
        };

        String[] values = new String[5];

        do {
            String input = this.requestUserInput(options[step]);
            values[step] = input;

            // Comprobar que el ID no existe
            if (step == 0) {
                try {
                    if (lastCurrentAlumnos != null) {
                        int requestId = Integer.parseInt(input);
                        if (List.of(this.lastCurrentAlumnos).stream().filter(a -> a.getId() == requestId).findFirst().isPresent()) throw new Exception("Id already in use");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                    continue;
                }
            }

            step++;
            if (step == options.length) {
                flag = true;
            }
        } while (!flag);

        return Alumno.createAlumno(
            Integer.parseInt(values[0]),
            values[1],
            values[3],
            values[2],
            values[4]
        );
    }

    /**
     * 
     * Permite obtener todos los nonbres de los alumnos que están en el fichero XML.
     * 
     * @throws Exception si no se puede leer el fichero.
     */
    private void queryAllNames() throws Exception { 
        
        // La expresión XPath que se utilizará para obtener los nombres de los alumnos.
        String expression = "//alumnes/nom_alumne";

        // Obtener el documento.
        Document doc = this.reader.getDocument(this.file);
        // Obtener los nodos que cumplen la expresión XPath.
        NodeList items = (NodeList) XPathFactory.newInstance().newXPath().evaluate(expression, doc.getDocumentElement(), XPathConstants.NODESET);

        String[] names = new String[items.getLength()];
        for (int i = 0; i < items.getLength(); i++) {
            // Asignamos el contenido del nodo a la posición del array.
            names[i] = items.item(i).getTextContent();
        }

        // Mostramos los nombres con un salto de línea.
        JOptionPane.showMessageDialog(this, String.join("\n", names));
    }

    /**
     * 
     * Dado un elemento que representa un alumno, obtiene sus datos y los devuelve como un objeto Alumno.
     * 
     * @param el elemento que representa un alumno.
     * @return Un objeto Alumno con los datos del alumno.
     */
    private Alumno readAlumnoFromElement(Element el) {
        return Alumno.createAlumno(
            Integer.parseInt(el.getAttribute("codi_alumne")),
            el.getElementsByTagName("nom_alumne").item(0).getTextContent(),
            el.getElementsByTagName("any_naixement").item(0).getTextContent(),
            el.getElementsByTagName("curs").item(0).getTextContent(),
            el.getElementsByTagName("colegi").item(0).getTextContent()
        );
    }

    /**
     * 
     * Muestra todos los alumnos del fichero XML que sean del colegio CIDE.
     * 
     * @throws Exception si no se puede leer el fichero.
     */
    private void queryCideStudents() throws Exception {

        // La expresión XPath que se utilizará para obtener los nombres de los alumnos.
        String expression = "//alumnes[colegi='CIDE']";

        Document doc = this.reader.getDocument(this.file);
        // Obtener los nodos que cumplen la expresión XPath.
        NodeList items = (NodeList) XPathFactory.newInstance().newXPath().evaluate(expression, doc.getDocumentElement(), XPathConstants.NODESET);

        Alumno[] cideStudents = new Alumno[items.getLength()];
        for (int i = 0; i < items.getLength(); i++) {
            // Asignamos el contenido del nodo a la posición del array.
            cideStudents[i] = this.readAlumnoFromElement((Element) items.item(i));   
        }

        // Mostramos los nombres con un salto de línea.
        JOptionPane.showMessageDialog(this, String.join("\n", List.of(cideStudents).stream().map(a -> a.toString()).collect(Collectors.toList())));
    }

    /**
     * 
     * Muestra el alumno con el ID 3.
     * 
     * @throws Exception si no se puede leer el fichero.
     */
    private void queryStudentById3() throws Exception {

        // La expresión XPath que se utilizará para obtener los nombres de los alumnos.
        String expression = "//alumnes[@codi_alumne='3']";

        Document doc = this.reader.getDocument(this.file);
        // Obtener los nodos que cumplen la expresión XPath.
        NodeList items = (NodeList) XPathFactory.newInstance().newXPath().evaluate(expression, doc.getDocumentElement(), XPathConstants.NODESET);

        Alumno[] cideStudents = new Alumno[items.getLength()];
        for (int i = 0; i < items.getLength(); i++) {
            // Asignamos el contenido del nodo a la posición del array.
            cideStudents[i] = this.readAlumnoFromElement((Element) items.item(i));   
        }

        // Muestra el Alumno.
        JOptionPane.showMessageDialog(this, String.join("\n", List.of(cideStudents).stream().map(a -> a.toString()).collect(Collectors.toList())));
    }

    /**
     * 
     * Muestra los alumnos que hayan nacido antes de 1990.
     * 
     * @throws Exception
     */
    private void queryStudentsBefore1990() throws Exception {

        // La expresión XPath que se utilizará para obtener los nombres de los alumnos.
        String expression = "//alumnes[any_naixement<1990]";

        Document doc = this.reader.getDocument(this.file);
        // Obtener los nodos que cumplen la expresión XPath.
        NodeList items = (NodeList) XPathFactory.newInstance().newXPath().evaluate(expression, doc.getDocumentElement(), XPathConstants.NODESET);

        Alumno[] cideStudents = new Alumno[items.getLength()];
        for (int i = 0; i < items.getLength(); i++) {
            cideStudents[i] = this.readAlumnoFromElement((Element) items.item(i));   
        }

        // Muestra los alumnos.
        JOptionPane.showMessageDialog(this, String.join("\n", List.of(cideStudents).stream().map(a -> a.toString()).collect(Collectors.toList())));
    }

    /**
     * 
     * Obteniendo un listado completo de los elementos usando XPath, mostramos el archivo XML.
     * 
     * @param doc El documento XML.
     * @throws Exception Si no se puede mostrar el archivo.
     */
    private void showFileMenu() throws Exception {

        if (this.file == null) throw new Exception("No hay ningún archivo seleccionado.");

        Document doc = this.reader.getDocument(this.file);

        String expression = "//*";

        StringWriter stringWriter = new StringWriter();
        StreamResult result = new StreamResult(stringWriter);

        // Obtenemos los elementos que cumplen la expresión.
        Node items = (Node) XPathFactory.newInstance().newXPath().evaluate(expression, doc.getDocumentElement(), XPathConstants.NODE);
        
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(items), result);
        
        StringBuffer sb = stringWriter.getBuffer();

        // Mostramos los nodos.
        JOptionPane.showMessageDialog(this, sb.toString());
    }

    /**
     * 
     * Un menu para poder editar la información de un Alumno especificado.
     * 
     * @throws Exception si no se puede leer el fichero.
     */
    private void updateMenu() throws Exception {

        int step = 0;
        
        if (this.file == null) throw new Exception("No hay ningún archivo seleccionado.");
        if (lastCurrentAlumnos == null) throw new Exception("No hay alumnos");

        String[] options = {
            "El identificador del alumno",
            "El dato a modificar (nom_alumne, curs, any_naixement, colegi)",
            "El nuevo valor a asignar"
        };

        String[] validModifiers = {
            "nom_alumne",
            "curs",
            "any_naixement",
            "colegi"
        };

        String[] values = new String[3];

        do {
            String input = this.requestUserInput(options[step]);
            values[step] = input;

            try {
                switch (step)
                {
                    case 0:
                        // Si no hay registros anteriores permitimos que se introduzca un nuevo ID.
                        int requestId = Integer.parseInt(input);
                        // Comprobamos que el ID introducido exista.
                        if (!List.of(this.lastCurrentAlumnos).stream().filter(a -> a.getId() == requestId).findFirst().isPresent()) throw new Exception("Id not found");
                        break;
                    case 1:
                        // Comprobamos que el modificador introducido sea válido.
                        if (!List.of(validModifiers).contains(values[1])) JOptionPane.showMessageDialog(this, "Invalid modifier");
                        break;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
                continue;
            }    

            step++;
        } while (step != options.length);

        // Obtenemos el documento XML.
        Document doc = this.reader.getDocument(this.file);

        // Obtenemos el elemento que contiene el alumno.
        Node node = (Node) XPathFactory.newInstance().newXPath().evaluate(
            String.format("//alumnes[@codi_alumne='%s']/%s", values[0], values[1]),
            doc,
            XPathConstants.NODE
        );

        Element element = (Element) node;
        // Modificamos el valor del elemento.
        element.setTextContent(values[2]);

        // Guardamos el documento XML.
        this.writer.writeDocument(doc, file);

        // Asignamos los nuevos alumnos al buffer
        this.lastCurrentAlumnos = this.reader.read(file);
    }

    /**
     * 
     * Menú para consultar datos de los alumnos.
     * 
     */
    private void queryMenu() throws Exception {

        if (this.file == null) throw new Exception("No hay ningún archivo seleccionado.");

        boolean flag = false;

        String[] options = {
            "Consultar todos los nombres de los alumnos",
            "Consultar los alumnos que vayan al colegio CIDE",
            "Consultar el nombre del alumno con código 3",
            "Consultar los alumnos nacidos antes de 1990",
            "Salir"
        };

        do {

            // Mostramos el menú.
            String response = JOptionPane.showInputDialog(
                this,
                List.of(options).stream()
                    .map(s -> String.format("%d. %s", List.of(options).indexOf(s) + 1, s))
                    .collect(java.util.stream.Collectors.joining("\n"))
            );

            int order = 0;
            
            try {
                order = Integer.parseInt(response);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Debe introducir un número");
                continue;
            }

            try {
                
                switch (order) {
                    case 1:
                        this.queryAllNames();
                        break;
                    case 2:
                        this.queryCideStudents();
                        break;
                    case 3:
                        this.queryStudentById3();
                        break;
                    case 4:
                        this.queryStudentsBefore1990();
                        break;
                    case 5:
                        flag = true;
                        break;
                    default:
                        throw new Exception("Opción no implementada");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }

        } while (!flag);

    }

    /**
     * 
     * Menú para poder eliminar un registro de alumnos.
     * 
     * @throws Exception
     */
    private void removeMenu() throws Exception {

        int step = 0;
        
        String[] options = {
            "El identificador del alumno"
        };

        String[] values = new String[1];

        // Si no hay archivo seleccionado, no podemos eliminar nada.
        if (this.file == null) throw new Exception("No hay ningún archivo seleccionado.");

        // Si no hay datos anteriores no permitimos eliminar nada.
        if (lastCurrentAlumnos == null) {
            JOptionPane.showMessageDialog(this, "No hay datos anteriores");
            return;
        }

        do {
            String input = this.requestUserInput(options[step]);
            values[step] = input;

            try {
                switch (step)
                {
                    case 0:
                        int requestId = Integer.parseInt(input);
                        // Comprobamos que el ID introducido exista.
                        if (!List.of(this.lastCurrentAlumnos).stream().filter(a -> a.getId() == requestId).findFirst().isPresent()) throw new Exception("Id not found");
                        break;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
                continue;
            }    

            step++;
        } while (step != options.length);

        // Obtenemos el documento XML.
        Document doc = this.reader.getDocument(this.file);

        // Obtenemos el elemento que contiene el alumno.
        Node node = (Node) XPathFactory.newInstance().newXPath().evaluate(
            String.format("//alumnes[@codi_alumne='%s']", values[0]),
            doc,
            XPathConstants.NODE
        );

        // Eliminamos el elemento.
        doc.getDocumentElement().removeChild(node);
    
        // Guardamos el documento XML.
        this.writer.writeDocument(doc, file);

        // Asignamos los nuevos alumnos al buffer
        this.lastCurrentAlumnos = this.reader.read(file);
    }

    /**
     * 
     * Menú principal del programa.
     * 
     */
    public void mainMenu() {

        boolean flag = false;

        String[] options = {
            "Crear/Recuperar fichero XML",
            "Introducir datos en el fichero XML",
            "Mostrar el contenido del fichero XML",
            "Modificar datos",
            "Consultas",
            "Eliminar un registro",
            "Salir"
        };

        do {

            // Mostramos el menú.
            String response = JOptionPane.showInputDialog(
                this,
                List.of(options).stream()
                    .map(s -> String.format("%d. %s", List.of(options).indexOf(s) + 1, s))
                    .collect(java.util.stream.Collectors.joining("\n"))
            );

            int order = 0;
            
            try {
                order = Integer.parseInt(response);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Debe introducir un número");
                continue;
            }

            try {
                
                switch (order) {
                    case 1:
                        String fileName = requestUserInput("Nombre del fichero XML");
                        this.lastCurrentAlumnos = null;
                        try {
                            this.setFile(new File(fileName));   
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(this, "El fichero existe, obteniendo datos del fichero...");
                            // Obtenemos los datos del fichero.
                            this.lastCurrentAlumnos = this.reader.read(this.file);
                            break;
                        }
                        // Creamos el fichero XML.
                        this.writer.write(new Alumno[0], file);
                        break;
                    case 2:
                        // Comprobamos que se haya creado o recuperado el fichero.
                        if (this.file == null) throw new Exception("No hay ningún archivo seleccionado.");

                        Alumno alumno = this.requestAlumno();
                    
                        // Si no hay datos anteriores, creamos el fichero XML con el nuevo alumno.
                        if (this.lastCurrentAlumnos == null) {
                            this.writer.write(new Alumno[]{alumno}, file);
                            this.lastCurrentAlumnos = new Alumno[]{alumno};
                            break;
                        }

                        // Obtenemos los alumnos actuales mas el nuevo alumno, y los añadimos al fichero XML.
                        Alumno[] newAlumnos = new Alumno[this.lastCurrentAlumnos.length + 1];
                        System.arraycopy(this.lastCurrentAlumnos, 0, newAlumnos, 0, this.lastCurrentAlumnos.length);
                        newAlumnos[this.lastCurrentAlumnos.length] = alumno;

                        this.writer.write(newAlumnos, file);

                        this.lastCurrentAlumnos = newAlumnos;
                        break;
                    case 3:
                        this.showFileMenu();
                        break;
                    case 4:
                        this.updateMenu();
                        break;
                    case 5:
                        this.queryMenu();
                        break;
                    case 6:
                        this.removeMenu();
                        break;
                    case 7:
                        flag = true;
                        break;
                    default:
                        throw new Exception("Opción no implementada");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }

        } while (!flag);

        // Cerramos el JFrame.
        this.dispose();

    }

    public static void main(String[] args) {

        // Instanciamos la clase.
        MainApp app = new MainApp();

        // Establecemos el motor de lectura y escritura.
        app.setWriter(Writers.DOM);

        // Mostramos el menú principal.
        app.mainMenu();
    }

}
