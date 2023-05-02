package model.xml;

import model.expression.IExpression;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class XMLManager {

    /**
     * Load an XML file.
     */
    public static IExpression load(String fileName) {

        //On vérifie que le fichier se termine en ".model.xml.xml" et que celui-ci n'est pas null
        if (fileName == null || !fileName.endsWith(".model.xml.xml")) {
            throw new IllegalArgumentException("File name is invalid.");
        }
        //On vérifie que le fichier existe pour l'ouvrir
        File file = new File(fileName);
        if (!file.exists()) {
            throw new IllegalArgumentException("File does not exist.");
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
        //On vérifie que le fichier se termine en ".model.xml.xml" et que celui-ci n'est pas null
        if (fileName == null || !fileName.endsWith(".model.xml.xml")) {
            throw new IllegalArgumentException("File name is invalid.");
        }
    }

    public static void main(String[] args) {
        String file = "xml/stb23.tp1.model.xml.xml";
        load(file);
    }
}
