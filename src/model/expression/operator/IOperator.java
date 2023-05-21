package model.expression.operator;

import java.util.Set;

/**
 * Interface that define an operator.
 */
public interface IOperator {

    /**
     * Function that return the symbol of the operator.
     * @return the symbol.
     */
    String getSymbol();

    /**
     * Method that return the type of expression of the operator.
     * @return a Set of String.
     */
    Set<String> getExpressionType();

    /**
     * Method that return the arity of the operator.
     * @return the arity.
     */
    int getArity();
}
