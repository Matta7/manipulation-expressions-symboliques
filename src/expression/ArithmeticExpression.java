package expression;

import expression.operator.OperatorEnum;

public class ArithmeticExpression implements IExpression {

    private String expression;

    public ArithmeticExpression() {}

    public String getToken() {
        return "arith";
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void save(String fileName) {
    }
}
