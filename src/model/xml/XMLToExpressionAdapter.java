package model.xml;

import model.expression.Expression;
import model.expression.IExpression;

public class XMLToExpressionAdapter extends Expression implements IExpression {

    private final XMLToExpressionHandler handler;

    public XMLToExpressionAdapter(XMLToExpressionHandler handler) {
        this.handler = handler;
    }

    public String getToken() {
        return handler.getCurrentType();
    }

    public String getExpression() {
        return handler.getCurrentExpression();
    }
}
