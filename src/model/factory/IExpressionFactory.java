package model.factory;

import model.expression.IExpression;

public interface IExpressionFactory {
    IExpression makeArithmetic();
    IExpression makeFunction();
    IExpression makeRational();
}
