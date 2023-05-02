package model.expression;

import model.expression.type.Type;

public class FunctionExpression extends Expression {

    public FunctionExpression() {}

    public String getToken() {
      return Type.FUNCTION;
    }

}
