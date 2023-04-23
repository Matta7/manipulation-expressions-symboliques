package expression;

import java.util.Stack;
import expression.operator.OperatorEnum;

public class FunctionExpression implements IExpression {

  public FunctionExpression() {
  }

  public String getToken() {
    return "func";
  }

  public void save(String fileName) {
  }
}
