package model.expression;

import model.expression.type.Type;

/**
 * Class that represents an arithmetic expression.
 */
public class ArithmeticExpression extends Expression {

    public ArithmeticExpression() {}

    public String getToken() {
        return Type.ARITHMETIC;
    }
}
