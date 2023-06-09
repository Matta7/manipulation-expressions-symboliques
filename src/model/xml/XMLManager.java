package model.xml;

import model.expression.IExpression;
import model.xml.validation.AbstractValidation;
import model.xml.validation.FileExistValidation;
import model.xml.validation.FileNameValidation;
import model.xml.validation.ValidXMLValidation;
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
     * Load an XML file using a filename.
     * @param fileName The file name to load
     * @return the IExpression built and loaded.
     */
    public static IExpression load(String fileName) {

        AbstractValidation chain = new FileNameValidation();

        AbstractValidation xmlValidation = new ValidXMLValidation();
        AbstractValidation fileExist = new FileExistValidation();

        fileExist.setSuccessor(xmlValidation);
        chain.setSuccessor(fileExist);

        chain.setFileName(fileName);


        if (chain.isValid()) {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

            try {
                SAXParser saxParser = saxParserFactory.newSAXParser();

                XMLToExpressionHandler handler = new XMLToExpressionHandler();

                saxParser.parse(fileName, handler);

                XMLToExpressionAdapter adapter = new XMLToExpressionAdapter(handler);

                return adapter;
            } catch (Exception e) {
                throw new IllegalArgumentException(e.getMessage() + "\n");
            }
        }
        throw new IllegalArgumentException("Invalid file.\n");
    }

    /**
     * Save an XML file.
     * @param fileName The file name of the saved file.
     * @param expression The expression to save.
     */
    public static void save(String fileName, IExpression expression) throws TransformerException, ParserConfigurationException {

        AbstractValidation chain = new FileNameValidation();
        chain.setFileName(fileName);

        if (chain.isValid()) {
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
    }
}
