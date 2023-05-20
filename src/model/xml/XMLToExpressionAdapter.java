package model.xml;

import model.expression.Expression;
import model.expression.IExpression;
import model.expression.type.Type;

public class XMLToExpressionAdapter extends Expression implements IExpression {

    private String token;

    public XMLToExpressionAdapter(XMLToExpressionHandler handler) {
        this.setToken(handler.getCurrentType());
        this.setExpression(handler.getCurrentExpression());
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        if (token.equals(Type.ARITHMETIC) || token.equals(Type.FUNCTION) || token.equals(Type.RATIONAL)) {
            this.token = token;
        } else {
            throw new IllegalArgumentException("Unknown type\n.");
        }
    }
}
