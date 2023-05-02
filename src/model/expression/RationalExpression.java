package model.expression;

import model.expression.type.Type;

public class RationalExpression extends Expression {

    public RationalExpression() {}

    public String getToken() {
      return Type.RATIONAL;
    }
}