package model.xml;

import model.expression.Expression;
import model.expression.IExpression;

/**
 * A class that adapt XML data to IExpression.
 */
public class XMLToExpressionAdapter extends Expression implements IExpression {

    /**
     * The class to adapt.
     */
    private final XMLToExpressionHandler handler;

    public XMLToExpressionAdapter(XMLToExpressionHandler handler) {
        this.handler = handler;
    }

    @Override
    public String getToken() {
        return handler.getCurrentType();
    }

    @Override
    public String getExpression() {
        return handler.getCurrentExpression();
    }
}
