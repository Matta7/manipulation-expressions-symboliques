package model.xml;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class FileValidator {

    public static boolean isFileNameValid(String fileName) {
        //On vérifie que le fichier se termine en ".xml" et que celui-ci n'est pas null
        if (fileName == null || !fileName.endsWith(".xml")) {
            throw new IllegalArgumentException("File name is invalid.\n");
        }

        return true;
    }

    public static boolean fileExists(String fileName) {
        //On vérifie que le fichier existe pour l'ouvrir
        File file = new File(fileName);
        if (!file.exists()) {
            throw new IllegalArgumentException("File does not exist.\n");
        }

        return true;
    }

    public static boolean validateXMLSchema(String xsdPath, String xmlPath){
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (IOException | SAXException e) {
            System.out.println(e.getMessage());
            return false;
            //throw new IllegalArgumentException("File is not valid.\n");
        }
        return true;
    }
}
