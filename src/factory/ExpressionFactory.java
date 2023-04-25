package factory;

import expression.ArithmeticExpression;
import expression.FunctionExpression;
import expression.IExpression;
import expression.RationalExpression;

public class ExpressionFactory implements IExpressionFactory {

    private static IExpressionFactory factory = new ExpressionFactory();

    private ExpressionFactory() {}

    public static IExpressionFactory getInstance() {
        return factory;
    }

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
