package xml;

import expression.IExpression;

public class XMLToExpressionAdapter implements IExpression {

    private String token;

    public XMLToExpressionAdapter() {}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        if (token.equals("arith") || token.equals("rat") || token.equals("func")) {
            this.token = token;
        } else {
            throw new IllegalArgumentException("Type inconnu");
        }
    }

    public void buildTree(String exp) {

    }

    public void save(String fileName) {

    }

    @Override
    public String getString() {
        return "";
    }
}
