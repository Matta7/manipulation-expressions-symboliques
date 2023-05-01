package expression;

import expression.type.Type;

public class ArithmeticExpression extends Expression {

    public ArithmeticExpression() {}

    public String getToken() {
        return Type.ARITHMETIC;
    }
}
