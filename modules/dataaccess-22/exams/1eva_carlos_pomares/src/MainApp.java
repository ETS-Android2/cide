

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

public class MainApp extends JFrame {
    
    enum Writers {
        DOM, SAX
    }
    
    private XMLWriter writer;
    private XMLReader reader;

    // App functionality
    private File file;
    private Alumno[] lastCurrentAlumnos;

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

    private String requestUserInput(String message) {
        return JOptionPane.showInputDialog(this, message); 
    }

    private void setFile(File file) throws Exception {
        this.file = file;
        if (file.exists()) {
            throw new Exception("File exists");
        }
    }

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
    
    private void updateMenu() throws Exception {

        int step = 0;

        String[] options = {
            "El identificador del alumno",
            "El dato a modificar",
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
                        if (lastCurrentAlumnos == null) continue;
                        int requestId = Integer.parseInt(input);
                        if (!List.of(this.lastCurrentAlumnos).stream().filter(a -> a.getId() == requestId).findFirst().isPresent()) throw new Exception("Id not found");
                        break;
                    case 1:
                        if (!List.of(validModifiers).contains(values[1])) JOptionPane.showMessageDialog(this, "Invalid modifier");
                        break;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
                continue;
            }    

            step++;
        } while (step != options.length);

        Document doc = this.reader.getDocument(this.file);

        Node node = (Node) XPathFactory.newInstance().newXPath().evaluate(
            String.format("//alumnes[@codi_alumne='%s']/%s", values[0], values[1]),
            doc,
            XPathConstants.NODE
        );

        Element element = (Element) node;
        element.setTextContent(values[2]);

        // Write to file
        this.writer.writeDocument(doc, file);

        this.lastCurrentAlumnos = this.reader.read(file);
    }

    private void showFile(Document doc) throws Exception {

        String expression = "//*";

        StringWriter stringWriter = new StringWriter();
        StreamResult result = new StreamResult(stringWriter);

        Node items = (Node) XPathFactory.newInstance().newXPath().evaluate(expression, doc.getDocumentElement(), XPathConstants.NODE);
        
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(items), result);
        
        StringBuffer sb = stringWriter.getBuffer();

        JOptionPane.showMessageDialog(this, sb.toString());

    }

    private void queryAllNames() throws Exception { 
            
        String expression = "//alumnes/nom_alumne";

        Document doc = this.reader.getDocument(this.file);
        NodeList items = (NodeList) XPathFactory.newInstance().newXPath().evaluate(expression, doc.getDocumentElement(), XPathConstants.NODESET);
        String[] names = new String[items.getLength()];

        for (int i = 0; i < items.getLength(); i++) {
            names[i] = items.item(i).getTextContent();
        }

        // Show the names with a new line separation
        JOptionPane.showMessageDialog(this, String.join("\n", names));

    }

    private Alumno readAlumnoFromElement(Element el) {
        return Alumno.createAlumno(
            Integer.parseInt(el.getAttribute("codi_alumne")),
            el.getElementsByTagName("nom_alumne").item(0).getTextContent(),
            el.getElementsByTagName("any_naixement").item(0).getTextContent(),
            el.getElementsByTagName("curs").item(0).getTextContent(),
            el.getElementsByTagName("colegi").item(0).getTextContent()
        );
    }

    private void queryCideStudents() throws Exception {

        String expression = "//alumnes[colegi='CIDE']";

        Document doc = this.reader.getDocument(this.file);
        NodeList items = (NodeList) XPathFactory.newInstance().newXPath().evaluate(expression, doc.getDocumentElement(), XPathConstants.NODESET);

        Alumno[] cideStudents = new Alumno[items.getLength()];

        for (int i = 0; i < items.getLength(); i++) {
            cideStudents[i] = this.readAlumnoFromElement((Element) items.item(i));   
        }

        JOptionPane.showMessageDialog(this, String.join("\n", List.of(cideStudents).stream().map(a -> a.toString()).collect(Collectors.toList())));

    }

    private void queryStudentById3() throws Exception {

        String expression = "//alumnes[@codi_alumne='3']";

        Document doc = this.reader.getDocument(this.file);
        NodeList items = (NodeList) XPathFactory.newInstance().newXPath().evaluate(expression, doc.getDocumentElement(), XPathConstants.NODESET);

        Alumno[] cideStudents = new Alumno[items.getLength()];

        for (int i = 0; i < items.getLength(); i++) {
            cideStudents[i] = this.readAlumnoFromElement((Element) items.item(i));   
        }

        JOptionPane.showMessageDialog(this, String.join("\n", List.of(cideStudents).stream().map(a -> a.toString()).collect(Collectors.toList())));

    }

    private void queryStudentsBefore1990() throws Exception {

        String expression = "//alumnes[any_naixement<=1990]";

        Document doc = this.reader.getDocument(this.file);
        NodeList items = (NodeList) XPathFactory.newInstance().newXPath().evaluate(expression, doc.getDocumentElement(), XPathConstants.NODESET);

        Alumno[] cideStudents = new Alumno[items.getLength()];

        for (int i = 0; i < items.getLength(); i++) {
            cideStudents[i] = this.readAlumnoFromElement((Element) items.item(i));   
        }

        JOptionPane.showMessageDialog(this, String.join("\n", List.of(cideStudents).stream().map(a -> a.toString()).collect(Collectors.toList())));

    }

    private void queryMenu() {

        boolean flag = false;

        String[] options = {
            "Consultar todos los nombres de los alumnos",
            "Consultar los alumnos que vayan al colegio CIDE",
            "Consultar el nombre del alumno con código 3",
            "Consultar los alumnos nacidos antes de 1990",
            "Salir"
        };

        do {

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

    private void removeMenu() throws Exception {

        int step = 0;
        
        String[] options = {
            "El identificador del alumno"
        };

        String[] values = new String[1];

        do {
            String input = this.requestUserInput(options[step]);
            values[step] = input;

            try {
                switch (step)
                {
                    case 0:
                        if (lastCurrentAlumnos == null) continue;
                        int requestId = Integer.parseInt(input);
                        if (!List.of(this.lastCurrentAlumnos).stream().filter(a -> a.getId() == requestId).findFirst().isPresent()) throw new Exception("Id not found");
                        break;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
                continue;
            }    

            step++;
        } while (step != options.length);

        Document doc = this.reader.getDocument(this.file);

        Node node = (Node) XPathFactory.newInstance().newXPath().evaluate(
            String.format("//alumnes[@codi_alumne='%s']", values[0]),
            doc,
            XPathConstants.NODE
        );

        doc.getDocumentElement().removeChild(node);
    
        // Write to file
        this.writer.writeDocument(doc, file);

        this.lastCurrentAlumnos = this.reader.read(file);
    }

    public void mainMenu() {

        boolean flag = false;

        String[] options = {
            "Crear fichero XML",
            "Introducir datos en el fichero XML",
            "Mostrar el contenido del fichero XML",
            "Modificar datos",
            "Consultas",
            "Eliminar un registro",
            "Salir"
        };

        do {

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
                        try {
                            this.setFile(new File(fileName));   
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(this, "El fichero existe, obteniendo datos del fichero...");
                            this.lastCurrentAlumnos = this.reader.read(this.file);
                            break;
                        }
                        this.writer.write(new Alumno[0], file);
                        break;
                    case 2:
                        Alumno alumno = this.requestAlumno();
                    
                        if (this.lastCurrentAlumnos == null) {
                            this.writer.write(new Alumno[]{alumno}, file);
                            this.lastCurrentAlumnos = new Alumno[]{alumno};
                            break;
                        }

                        Alumno[] newAlumnos = new Alumno[this.lastCurrentAlumnos.length + 1];
                        System.arraycopy(this.lastCurrentAlumnos, 0, newAlumnos, 0, this.lastCurrentAlumnos.length);
                        newAlumnos[this.lastCurrentAlumnos.length] = alumno;

                        this.writer.write(newAlumnos, file);

                        this.lastCurrentAlumnos = newAlumnos;
                        break;
                    case 3:
                        this.showFile(this.reader.getDocument(file));
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

        // Close the application
        this.dispose();

    }

    public static void main(String[] args) {
        MainApp app = new MainApp();
        app.setWriter(Writers.DOM);
        app.mainMenu();
    }

}
