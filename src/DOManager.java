import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class DOManager {
    private Document doc;
    private ArrayList<Alumno> alumnos;

    public DOManager(ArrayList<Alumno> alumnos) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            this.doc = builder.newDocument();
            this.alumnos = alumnos;

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void createDocument() {
        Element alumns = this.doc.createElement("alumnos");
        int contador = 1;
        this.doc.appendChild(alumns);
        for (Alumno alumno : this.alumnos) {
            Element alumn = this.doc.createElement("alumno");
            alumns.appendChild(alumn);
            alumn.setAttribute("codigo", String.valueOf(contador));
            Element name = this.doc.createElement("nombre");
            alumn.appendChild(name);
            name.appendChild(this.doc.createTextNode(alumno.getNombre()));
            Element age = this.doc.createElement("edad");
            alumn.appendChild(age);
            age.appendChild(this.doc.createTextNode(String.valueOf(alumno.getEdad())));
            Element direction = this.doc.createElement("direccion");
            alumn.appendChild(direction);
            Element street = this.doc.createElement("calle");
            direction.appendChild(street);
            street.setTextContent(alumno.getDireccion().getCalle());
            Element region = this.doc.createElement("provincia");
            direction.appendChild(region);
            street.setTextContent(alumno.getDireccion().getProvincia());
            contador++;
        }


    }

    public void transformMyDocumentToXML() throws IOException {
        TransformerFactory factoria = TransformerFactory.newInstance(); //Creamos fabrica
        Transformer transformer = null; //Creamos un transformador
        try {
            transformer = factoria.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        Source source = new DOMSource(doc);
        File f = new File("fichero.xml"); //Abrimos fichero
        FileWriter fw = new FileWriter(f);
        PrintWriter pw = new PrintWriter(fw);
        Result result = new StreamResult(pw);
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        try {
            transformer.transform(source, result); //Esta línea transforma el fichero en un XML
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
