package model.expression.operator;

import model.expression.type.Type;

/**
 * Class that contains methods to do some operations with operators.
 */
public class OperatorHandler {

    /**
     * Get the Operator Object.
     * @param symbol the symbol of the operator.
     * @param type type of the operator.
     * @return The operator.
     */
    public static IOperator getOperator(String symbol, String type) {
        for (IOperator operator : OperatorEnum.values()) {
            if (operator.getSymbol().equals(symbol) && operator.getExpressionType().contains(type)) {
                return operator;
            }
        }
        return null;
    }

    /**
     * Return true if the operator is an operator.
     * @param symbol the symbol of the operator.
     * @param type type of the operator.
     * @return true or false.
     */
    public static boolean isOperator(String symbol, String type) {
        return getOperator(symbol, type) != null;
    }

    public static boolean isArithmeticOperator(String symbol) {
        IOperator operator = getOperator(symbol, Type.ARITHMETIC);
        return operator != null;
    }

    public static boolean isFunctionOperator(String symbol) {
        IOperator operator = getOperator(symbol, Type.FUNCTION);
        return operator != null;
    }

    public static boolean isRationalOperator(String symbol) {
        IOperator operator = getOperator(symbol, Type.RATIONAL);
        return operator != null;
    }
}
