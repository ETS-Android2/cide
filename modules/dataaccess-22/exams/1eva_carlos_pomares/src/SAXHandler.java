
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler extends DefaultHandler {
    
    private boolean isName, isGrade, isYear, isSchool;
    private Alumno currentClient;

    private ArrayList<Alumno> clientes = new ArrayList<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        
        if (qName.equals("alumnes")) {
            this.currentClient = new Alumno();
            this.currentClient.setId(Integer.parseInt(attributes.getValue("codi_alumne")));
        }

        if (qName.equals("nom_alumne")) {
            this.isName = true;
        }

        if (qName.equals("curs")) {
            this.isGrade = true;
        }

        if (qName.equals("any_naixement")) {
            this.isYear = true;
        }

        if (qName.equals("colegi")) {
            this.isSchool = true;
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("alumnes")) {
            this.clientes.add(this.currentClient);
        }

        if (qName.equals("nom_alumne")) {
            this.isName = false;
        }

        if (qName.equals("curs")) {
            this.isGrade = false;
        }

        if (qName.equals("any_naixement")) {
            this.isYear = false;
        }

        if (qName.equals("colegi")) {
            this.isSchool = false;
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        if (this.isName) {
            this.currentClient.setName(new String(ch, start, length));
        }

        if (this.isGrade) {
            this.currentClient.setGrade(new String(ch, start, length));
        }

        if (this.isYear) {
            this.currentClient.setYear(new String(ch, start, length));
        }

        if (this.isSchool) {
            this.currentClient.setSchool(new String(ch, start, length));
        }

    }

    public Alumno[] getClientes() {
        return (clientes.size() == 0) ? null : clientes.toArray(new Alumno[clientes.size()]);
    }

}
