package expression;

import java.util.Stack;
import expression.operator.OperatorEnum;

public class FunctionExpression implements IExpression {

  public FunctionExpression() {
  }

  public String getToken() {
    return "func";
  }

  public void buildTree(String exp) {

  }

  public void save(String fileName) {
  }

  @Override
  public String getString() {
    return "";
  }
}
