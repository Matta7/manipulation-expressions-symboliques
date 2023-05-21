package model.stackhandler;

import model.expression.IExpression;
import model.expression.type.Type;
import model.factory.ExpressionFactory;
import model.factory.IExpressionFactory;

/**
 * Class that build an expression using a type.
 */
public class NewExpression {

    /**
     * Return an IExpression using a type, and using the factory.
     * @param type the type of IExpression to build.
     * @return the expression built.
     */
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
