package model.expression;

/**
 * Interface that define an expression.
 */
public interface IExpression {

  /**
   * Get the type of the expression.
   * @return the type.
   */
  String getToken();

  /**
   * Get the expression.
   * @return the expression.
   */
  String getExpression();

  /**
   * Set the expression.
   * @param expression the expression to set.
   */
  void setExpression(String expression);

}
