package model.stackhandler;

import model.expression.IExpression;
import model.expression.type.Type;
import model.factory.ExpressionFactory;
import model.factory.IExpressionFactory;

public class NewExpression {
    public static IExpression getNewExpression(String type) throws IllegalArgumentException {
        IExpressionFactory factory = new ExpressionFactory();

        switch (type) {
            case Type.ARITHMETIC -> {
                return factory.makeArithmetic();
            }

            case Type.FUNCTION -> {
                return factory.makeFunction();
            }

            case (Type.RATIONAL) -> {
                return factory.makeRational();
            }

            default -> throw new IllegalStateException("No type defined.\n");
        }
    }
}
