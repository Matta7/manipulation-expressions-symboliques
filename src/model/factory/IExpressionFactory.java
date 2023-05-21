package model.factory;

import model.expression.IExpression;

/**
 * Interface that define our Factory Method.
 */
public interface IExpressionFactory {
    /**
     * Create an ArithmeticExpression.
     * @return an ArithmeticExpression.
     */
    IExpression makeArithmetic();

    /**
     * Create an FunctionExpression.
     * @return an FunctionExpression.
     */
    IExpression makeFunction();

    /**
     * Create an RationalExpression.
     * @return an RationalExpression.
     */
    IExpression makeRational();
}
