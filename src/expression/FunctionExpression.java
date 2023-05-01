package expression;

import expression.type.Type;

public class FunctionExpression implements IExpression {

    private String expression;

    public FunctionExpression() {}

    public String getToken() {
      return Type.FUNCTION;
    }

    public String getExpression() {
      return expression;
    }

    public void setExpression(String expression) {
      this.expression = expression;
    }

    public void save(String fileName) {
    }

    public String toString() {
        StringBuilder strExp = new StringBuilder();
        strExp.append("[")
                .append(getToken())
                .append("] ")
                .append(getExpression());

        return strExp.toString();
    }
}
