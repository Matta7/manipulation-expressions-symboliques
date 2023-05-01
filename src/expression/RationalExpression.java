package expression;

import expression.type.Type;

public class RationalExpression extends Expression {

    public RationalExpression() {}

    public String getToken() {
      return Type.RATIONAL;
    }
}