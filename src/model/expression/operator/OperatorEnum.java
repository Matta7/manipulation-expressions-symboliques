package model.expression.operator;

import model.expression.type.Type;

import java.util.Set;

/**
 * Enum that define some operators.
 */
public enum OperatorEnum implements IOperator {

    /**
     * Add operator "+".
     */
    ADDITION("+", Set.of(Type.ARITHMETIC, Type.FUNCTION), 2),

    /**
     * Sub operator "-".
     */
    SOUSTRACTION("-", Set.of(Type.ARITHMETIC, Type.FUNCTION), 2),

    /**
     * Mul operator "*".
     */
    MULTIPLICATION("*", Set.of(Type.ARITHMETIC, Type.FUNCTION), 2),

    /**
     * Div operator "/".
     */
    DIVISION("/", Set.of(Type.ARITHMETIC, Type.FUNCTION), 2),

    /**
     * Neg operator "~".
     */
    NEGATION("~", Set.of(Type.ARITHMETIC, Type.FUNCTION), 1),

    /**
     * Union operator "+".
     */
    UNION("+", Set.of(Type.RATIONAL), 2),

    /**
     * Concat operator ".".
     */
    CONCATENATION(".", Set.of(Type.RATIONAL), 2),

    /**
     * Kleene Star operator "*".
     */
    ETOILE("*", Set.of(Type.RATIONAL), 1);
    //NEUTRAL("$", null, 1);

    private final String symbol;
    private final Set<String> expressionType;
    private final int arity;

    OperatorEnum(String symbol, Set<String> expressionType, int arity) {
        this.symbol = symbol;
        this.expressionType = expressionType;
        this.arity = arity;
    }

    public String getSymbol() {
        return symbol;
    }

    public Set<String> getExpressionType() {
        return expressionType;
    }

    public int getArity() {
        return arity;
    }
}