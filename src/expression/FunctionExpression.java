package expression;

import expression.type.Type;

public class FunctionExpression extends Expression {

    public FunctionExpression() {}

    public String getToken() {
      return Type.FUNCTION;
    }

}
