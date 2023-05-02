package xml;

import expression.Expression;
import expression.type.Type;

public class XMLToExpressionAdapter extends Expression {

    private String token;

    public XMLToExpressionAdapter() {}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        if (token.equals(Type.ARITHMETIC) || token.equals(Type.FUNCTION) || token.equals(Type.RATIONAL)) {
            this.token = token;
        } else {
            throw new IllegalArgumentException("Unknown type.");
        }
    }
}
