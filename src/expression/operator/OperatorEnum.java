package expression.operator;

import expression.IExpression;
import expression.ArithmeticExpression;
import expression.RationalExpression;

public enum OperatorEnum {
  ADDITION('+', ArithmeticExpression.class, "binary"),
  SOUSTRACTION('-', ArithmeticExpression.class, "binary"),
  MULTIPLICATION('*', ArithmeticExpression.class, "binary"),
  DIVISION('/', ArithmeticExpression.class, "binary"),
  NEGATION('~', ArithmeticExpression.class, "unary"),
  UNION('+', RationalExpression.class, "binary"),
  CONCATENATION('.', RationalExpression.class, "binary"),
  ETOILE('*', RationalExpression.class, "unary");

  private final char symbol;
  private final Class<? extends IExpression> expressionClass;
  private final String arity;

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