package expression;

import expression.type.Type;

public class ArithmeticExpression implements IExpression {

    private String expression;

    public ArithmeticExpression() {}

    public String getToken() {
        return Type.ARITHMETIC;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void save(String fileName) {
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
