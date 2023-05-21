package model.expression;

import model.expression.type.Type;

/**
 * Class that represents a rational expression.
 */
public class RationalExpression extends Expression {

    public RationalExpression() {}

    public String getToken() {
      return Type.RATIONAL;
    }
}