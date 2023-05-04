package model.xml;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Stack;

public class ExpressionHandler extends DefaultHandler {

    private StringBuilder currentValue = new StringBuilder();

    private String currentExpression = "";

    private Stack<String> operatorStack = new Stack<>();

    private XMLToExpressionAdapter adapter = new XMLToExpressionAdapter();

    public XMLToExpressionAdapter getExpression() {
        return adapter;
    }

    @Override
    public void endDocument() {
        if (operatorStack.isEmpty() && !currentExpression.isEmpty()) {
            currentExpression = currentExpression.substring(0, currentExpression.length()-1);
            adapter.setExpression(currentExpression.toString());
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
            adapter.setToken(type);
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
