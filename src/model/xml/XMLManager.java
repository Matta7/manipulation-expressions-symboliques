package model.xml;

import model.expression.IExpression;
import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

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
                throw new IllegalArgumentException(e.getMessage() + "\n");
            }
        }
        return null;
    }

    /**
     * Save an XML file.
     */
    public static void save(String fileName, IExpression expression) throws TransformerException, ParserConfigurationException {
        if (!FileValidator.isFileNameValid(fileName)) {
            throw new IllegalArgumentException("File name is invalid.");
        }

        ExpressionToXMLDocumentConverter converter = new ExpressionToXMLDocumentConverter(expression);
        Document document = converter.getDocument();

        // create the .xml file
        //transform the DOM Object to an XML File
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(fileName));

        transformer.transform(domSource, streamResult);
    }

    public static void main(String[] args) {
        String file = "xml/test.xml";
        System.out.println(load(file).toString());
    }
}
