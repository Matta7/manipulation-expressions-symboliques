package factory;

import expression.IExpression;

public interface IExpressionFactory {
    IExpression makeArithmetic();
    IExpression makeFunction();
    IExpression makeRational();
}
