package model.factory;

import model.expression.ArithmeticExpression;
import model.expression.FunctionExpression;
import model.expression.IExpression;
import model.expression.RationalExpression;

/**
 * Class that build an IExpression with the choice of the type.
 */
public class ExpressionFactory implements IExpressionFactory {

    public ExpressionFactory() {}


    @Override
    public IExpression makeArithmetic() {
        return new ArithmeticExpression();
    }

    @Override
    public IExpression makeFunction() {
        return new FunctionExpression();
    }

    @Override
    public IExpression makeRational() {
        return new RationalExpression();
    }
}
