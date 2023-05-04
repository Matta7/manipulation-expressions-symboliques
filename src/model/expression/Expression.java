package model.expression;

import model.xml.XMLManager;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public abstract class Expression implements IExpression {
    private String expression;

    public Expression() {}

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void save(String fileName) throws TransformerException, ParserConfigurationException {
        XMLManager.save(fileName, this);
    }

    public String toString() {
        StringBuilder strExp = new StringBuilder();
        strExp.append("[")
                .append(getToken())
                .append("] ")
                .append(getExpression());

        return strExp.toString();
    }
}
