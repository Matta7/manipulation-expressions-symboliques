package model.expression;

import model.xml.XMLManager;

public abstract class Expression implements IExpression {
    private String expression;

    public Expression() {}

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void save(String fileName) {
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
