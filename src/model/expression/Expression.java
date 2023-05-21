package model.expression;

/**
 * Class that define all expression method globally.
 */
public abstract class Expression implements IExpression {
    private String expression;

    public Expression() {}

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
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
