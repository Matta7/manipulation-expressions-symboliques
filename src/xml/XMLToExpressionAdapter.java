package xml;

import expression.IExpression;

public class XMLToExpressionAdapter implements IExpression {

    private String token;

    private String expression;

    public XMLToExpressionAdapter() {}

    public String getToken() {
        return token;
    }

    @Override
    public String getExpression() {
        return null;
    }

    @Override
    public void setExpression(String expression) {

    }

    public void setToken(String token) {
        if (token.equals("arith") || token.equals("rational") || token.equals("functional")) {
            this.token = token;
        } else {
            throw new IllegalArgumentException("Type inconnu");
        }
    }

    public void save(String fileName) {

    }
}
