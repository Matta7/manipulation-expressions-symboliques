package expression;

public class RationalExpression implements IExpression {

  private String expression;

  public RationalExpression() {}

  public String getToken() {
      return "rational";
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