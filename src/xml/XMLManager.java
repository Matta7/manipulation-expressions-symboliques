package xml;

import expression.IExpression;
import factory.ExpressionFactory;
import org.w3c.dom.Document;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.Map;

public class XMLManager {

    /**
     * Load an XML file.
     */
    public static IExpression load(String fileName) {

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

    }

    public static void main(String[] args) {
        String file = "xml/stb23.tp1.xml";
        load(file);
    }
}
