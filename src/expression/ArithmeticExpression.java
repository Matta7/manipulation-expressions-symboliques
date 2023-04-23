package expression;

import java.util.Stack;
import expression.operator.OperatorEnum;

public class ArithmeticExpression implements IExpression {

  public ArithmeticExpression() {
  }

  public String getToken() {
    return "arith";
  }

  public void save(String fileName) {
  }
}
