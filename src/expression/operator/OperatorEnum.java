package expression.operator;

import expression.IExpression;
import expression.ArithmeticExpression;
import expression.RationalExpression;

public enum OperatorEnum {
  ADDITION('+', ArithmeticExpression.class, "binaire"),
  SOUSTRACTION('-', ArithmeticExpression.class, "binaire"),
  MULTIPLICATION('*', ArithmeticExpression.class, "binaire"),
  DIVISION('/', ArithmeticExpression.class, "binaire"),
  NEGATION('~', ArithmeticExpression.class, "unaire"),
  UNION('+', RationalExpression.class, "binaire"),
  CONCATENATION('.', RationalExpression.class, "binaire"),
  ETOILE('*', RationalExpression.class, "unaire"),
  NEUTRAL('$', null, "unaire");

  private final char symbol;
  private final Class<? extends IExpression> expressionClass;
  private final String arity; // On aurait pu faire un enum ou mettre un int directement, certes.

  OperatorEnum(char symbol, Class<? extends IExpression> expressionClass, String arity) {
    this.symbol = symbol;
    this.expressionClass = expressionClass;
    this.arity = arity;
  }

  public char getSymbol() {
    return symbol;
  }

  public Class<? extends IExpression> getExpressionClass() {
    return expressionClass;
  }

  public String getArity() {
    return arity;
  }

  public static OperatorEnum getOperator(char symbol) {
    for (OperatorEnum operator : OperatorEnum.values()) {
      if (operator.getSymbol() == symbol) {
        return operator;
      }
    }
    return null;
  }

  public static boolean isOperator(char symbol) {
    return getOperator(symbol) != null;
  }

  public static boolean isArithmeticOperator(char symbol) {
    OperatorEnum operator = getOperator(symbol);
    return operator != null && operator.getExpressionClass() == ArithmeticExpression.class;
  }

  public static boolean isRationalOperator(char symbol) {
    OperatorEnum operator = getOperator(symbol);
    return operator != null && operator.getExpressionClass() == RationalExpression.class;
  }
}