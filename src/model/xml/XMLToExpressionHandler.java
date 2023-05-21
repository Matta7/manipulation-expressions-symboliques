package model.xml;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Stack;

/**
 * Class that use DefaultHandler to parse an XML file.
 */
public class XMLToExpressionHandler extends DefaultHandler {

    /**
     * The current value parsed by SAX parser.
     */
    private StringBuilder currentValue = new StringBuilder();

    /**
     * The type of the current expression.
     */
    private String currentType = "";

    /**
     * The current expression with a String type.
     */
    private String currentExpression = "";

    /**
     * A stack to build the expression.
     */
    private Stack<String> operatorStack = new Stack<>();

    /**
     * Get the current expression built.
     * @return the expression built.
     */
    public String getCurrentExpression() {
        return currentExpression;
    }

    /**
     * Get the current type of the expression.
     * @return the type.
     */
    public String getCurrentType() {
        return currentType;
    }

    @Override
    public void endDocument() {
        if (operatorStack.isEmpty() && !currentExpression.isEmpty()) {
            currentExpression = currentExpression.substring(0, currentExpression.length()-1);
        } else {
            throw new IllegalArgumentException("File is invalid.\n");
        }
    }

    @Override
    public void startElement(
            String uri,
            String localName,
            String qName,
            Attributes attributes) {

        // reset the tag value
        currentValue.setLength(0);

        if (qName.equalsIgnoreCase("expression")) {
            String type = attributes.getValue("type");
            currentType = type;
        } else if (qName.equalsIgnoreCase("operation")) {
            String opType = attributes.getValue("type");
            operatorStack.push(opType);
        }
    }

    @Override
    public void endElement(String uri,
                           String localName,
                           String qName) {

        if (qName.equalsIgnoreCase("operand")) {
            String operand = currentValue.toString();
            currentExpression += operand + " ";
        } else if (qName.equalsIgnoreCase("operation")) {
            String operator = operatorStack.pop();
            if (!operator.equals("$")) {
                currentExpression += operator + " ";
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        currentValue.append(ch, start, length);
    }
}
