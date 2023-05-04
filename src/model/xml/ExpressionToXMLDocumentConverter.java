package model.xml;

import model.expression.IExpression;
import model.expression.operator.OperatorEnum;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.Stack;

public class ExpressionToXMLDocumentConverter {

    private final IExpression expression;

    private Stack<Element> elementStack = new Stack<>();

    public ExpressionToXMLDocumentConverter(IExpression expression) {
        this.expression = expression;
    }

    public IExpression getExpression() {
        return expression;
    }

    public Document getDocument() throws ParserConfigurationException {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

        Document document = documentBuilder.newDocument();

        // root element expression
        Element root = document.createElement("expression");
        Attr typeAttr = document.createAttribute("type");
        Attr nsAttr = document.createAttribute("xmlns:expression");

        typeAttr.setValue(expression.getToken());
        nsAttr.setValue("file://schema/expression.xsd");

        root.setAttributeNode(typeAttr);
        root.setAttributeNode(nsAttr);

        document.appendChild(root);

        for (String symbol : expression.getExpression().split(" ")) {
            if (OperatorEnum.isOperator(symbol, expression.getToken())) {
                Element operator = document.createElement("operation");
                Attr opTypeAttr = document.createAttribute("type");
                opTypeAttr.setValue(symbol);
                operator.setAttributeNode(opTypeAttr);

                switch (OperatorEnum.getOperator(symbol, expression.getToken()).getArity()) {
                    case 1 -> {
                        Element e = elementStack.pop();
                        operator.appendChild(e);
                        elementStack.push(operator);
                    }
                    case 2 -> {
                        Element e2 = elementStack.pop();
                        Element e1 = elementStack.pop();
                        if (e1.getTagName().equals("operand") && e2.getTagName().equals("operation")) {
                            operator.appendChild(e2);
                            operator.appendChild(e1);
                        } else {
                            operator.appendChild(e1);
                            operator.appendChild(e2);
                        }
                        elementStack.push(operator);
                    }
                    default -> throw new IllegalArgumentException("File is invalid.\n");
                }
            } else {
                Element operand = document.createElement("operand");
                operand.appendChild(document.createTextNode(symbol));
                elementStack.push(operand);
            }
        }

        root.appendChild(elementStack.pop());

        if (elementStack.isEmpty()) {
            return document;
        }
        throw new IllegalArgumentException("File is invalid.\n");
    }
}
