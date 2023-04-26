package expression;

import java.util.Stack;
import expression.operator.OperatorEnum;

public class FunctionExpression implements IExpression {

  private String expression;

  private boolean hasVariable;

  public FunctionExpression() {}

  public String getToken() {
      return "function";
  }

  public String getExpression() {
      return expression;
  }

  public void setExpression(String expression) {
      this.expression = expression;
  }

  public void save(String fileName) {
  }
}
