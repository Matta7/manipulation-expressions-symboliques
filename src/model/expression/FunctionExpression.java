package model.expression;

import model.expression.type.Type;

/**
 * Class that represents function expression.
 */
public class FunctionExpression extends Expression {

    public FunctionExpression() {}

    public String getToken() {
      return Type.FUNCTION;
    }

}
