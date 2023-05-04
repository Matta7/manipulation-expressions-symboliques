package model.xml;

import model.expression.IExpression;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class XMLManager {

    /**
     * Load an XML file.
     */
    public static IExpression load(String fileName) {
        String xsdPath = "xml/schema/expression.xsd";

        if (FileValidator.isFileNameValid(fileName) && FileValidator.fileExists(fileName) && FileValidator.validateXMLSchema(xsdPath, fileName)) {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

            try {
                SAXParser saxParser = saxParserFactory.newSAXParser();

                ExpressionHandler handler = new ExpressionHandler();

                saxParser.parse(fileName, handler);

                return handler.getExpression();
            } catch (Exception e) {
                e.printStackTrace();
                throw new IllegalArgumentException("Cannot read file " + fileName + ".\n");
            }
        }
        return null;
    }

    /**
     * Save an XML file.
     */
    public static void save(String fileName, IExpression expression) {
        //On vérifie que le fichier se termine en ".model.xml.xml" et que celui-ci n'est pas null
        if (FileValidator.isFileNameValid(fileName)) {
            throw new IllegalArgumentException("File name is invalid.");
        }
    }

    public static void main(String[] args) {
        String file = "xml/test.xml";
        System.out.println(load(file).toString());
    }
}
