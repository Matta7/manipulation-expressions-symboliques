package xml;

import expression.IExpression;
import factory.ExpressionFactory;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.EmptyStackException;
import java.util.Map;

public class XMLManager {

    /**
     * Load an XML file.
     */
    public static IExpression load(String fileName) {

        //On vérifie que le fichier se termine en ".xml" et que celui-ci n'est pas null
        if (fileName == null || !fileName.endsWith(".xml")) {
            throw new IllegalArgumentException("Le nom du fichier est incorrect");
        }
        //On vérifie que le fichier existe pour l'ouvrir
        File file = new File(fileName);
        if (!file.exists()) {
            throw new IllegalArgumentException("Le fichier n'existe pas");
        }


        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();

            ExpressionHandler handler = new ExpressionHandler();

            saxParser.parse(fileName, handler);

            return handler.getExpression();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Save an XML file.
     */
    public static void save(String fileName, IExpression expression) {
        //On vérifie que le fichier se termine en ".xml" et que celui-ci n'est pas null
        if (fileName == null || !fileName.endsWith(".xml")) {
            throw new IllegalArgumentException("Le nom du fichier est incorrect");
        }
    }

    public static void main(String[] args) {
        String file = "xml/stb23.tp1.xml";
        load(file);
    }
}
