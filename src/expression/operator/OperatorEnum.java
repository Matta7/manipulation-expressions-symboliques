package expression.operator;

import expression.IExpression;
import expression.ArithmeticExpression;
import expression.FunctionExpression;
import expression.RationalExpression;

import java.util.Set;

public enum OperatorEnum {
  ADDITION("+", Set.of("arith", "function"), 2),
  SOUSTRACTION("-", Set.of("arith", "function"), 2),
  MULTIPLICATION("*", Set.of("arith", "function"), 2),
  DIVISION("/", Set.of("arith", "function"), 2),
  NEGATION("~", Set.of("arith", "function"), 1),
  UNION("+", Set.of("rational"), 2),
  CONCATENATION(".", Set.of("rational"), 2),
  ETOILE("*", Set.of("rational"), 1);
  //NEUTRAL("$", null, 1);

  private final String symbol;
  private final Set<String> expressionType;
  private final int arity;

  OperatorEnum(String symbol, Set<String> expressionType, int arity) {
    this.symbol = symbol;
    this.expressionType = expressionType;
    this.arity = arity;
  }

  public String getSymbol() {
    return symbol;
  }

  public Set<String> getExpressionType() {
    return expressionType;
  }

  public int getArity() {
    return arity;
  }

  public static OperatorEnum getOperator(String symbol) {
    for (OperatorEnum operator : OperatorEnum.values()) {
      if (operator.getSymbol().equals(symbol)) {
        return operator;
      }
    }
    return null;
  }

  public static boolean isOperator(String symbol) {
    return getOperator(symbol) != null;
  }

  public static boolean isArithmeticOperator(String symbol) {
    OperatorEnum operator = getOperator(symbol);
    return operator != null && operator.getExpressionType().contains("arith");
  }

  public static boolean isFunctionOperator(String symbol) {
    OperatorEnum operator = getOperator(symbol);
    return operator != null && operator.getExpressionType().contains("function");
  }

  public static boolean isRationalOperator(String symbol) {
    OperatorEnum operator = getOperator(symbol);
    return operator != null && operator.getExpressionType().contains("rational");
  }
}