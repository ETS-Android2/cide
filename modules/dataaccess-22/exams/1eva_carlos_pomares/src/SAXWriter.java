
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

public class SAXWriter extends XMLWriter {

    @Override
    public void write(Alumno[] alumnos, File file) throws Exception {
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = factory.createXMLStreamWriter(new OutputStreamWriter(new FileOutputStream(file)));

        // Escribe los alumnos

        writer.writeStartDocument();
        writer.writeStartElement("registre_alumnes");

        for (Alumno alumno : alumnos) {
            writer.writeStartElement("alumnes");
            writer.writeAttribute("codi_alumne", String.valueOf(alumno.getId()));

            writer.writeStartElement("nom_alumne");
            writer.writeCharacters(alumno.getName());
            writer.writeEndElement();

            writer.writeStartElement("curs");
            writer.writeCharacters(alumno.getGrade());
            writer.writeEndElement();

            writer.writeStartElement("any_naixement");
            writer.writeCharacters(alumno.getYear());
            writer.writeEndElement();

            writer.writeStartElement("colegi");
            writer.writeCharacters(alumno.getSchool());
            writer.writeEndElement();

            writer.writeEndElement();
        }

        writer.writeEndElement();
        writer.writeEndDocument();

        writer.close();
    }
    
}
