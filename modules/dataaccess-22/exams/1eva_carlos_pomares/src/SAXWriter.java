
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

        // Empezamos el documento
        writer.writeStartDocument();
        // Empezamos el elemento raiz
        writer.writeStartElement("registre_alumnes");

        // Por cada alumno escribimos un elemento
        for (Alumno alumno : alumnos) {
            writer.writeStartElement("alumnes");

            // Asignamos el atributo codi_alumne
            writer.writeAttribute("codi_alumne", String.valueOf(alumno.getId()));

            // Escribimos el nombre
            writer.writeStartElement("nom_alumne");
            writer.writeCharacters(alumno.getName());
            writer.writeEndElement();

            // Escribimos el curso
            writer.writeStartElement("curs");
            writer.writeCharacters(alumno.getGrade());
            writer.writeEndElement();

            // Escribimos el año de nacimiento
            writer.writeStartElement("any_naixement");
            writer.writeCharacters(alumno.getYear());
            writer.writeEndElement();

            // Escribimos el colegio
            writer.writeStartElement("colegi");
            writer.writeCharacters(alumno.getSchool());
            writer.writeEndElement();

            // Cerramos el elemento alumnes
            writer.writeEndElement();
        }

        // Cerramos el elemento raiz
        writer.writeEndElement();
        // Cerramos el documento
        writer.writeEndDocument();

        writer.close();
    }
    
}
