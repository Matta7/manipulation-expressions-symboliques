package model.expression.operator;

import model.expression.type.Type;

import java.util.Set;

public enum OperatorEnum implements IOperator {
    ADDITION("+", Set.of(Type.ARITHMETIC, Type.FUNCTION), 2),
    SOUSTRACTION("-", Set.of(Type.ARITHMETIC, Type.FUNCTION), 2),
    MULTIPLICATION("*", Set.of(Type.ARITHMETIC, Type.FUNCTION), 2),
    DIVISION("/", Set.of(Type.ARITHMETIC, Type.FUNCTION), 2),
    NEGATION("~", Set.of(Type.ARITHMETIC, Type.FUNCTION), 1),
    UNION("+", Set.of(Type.RATIONAL), 2),
    CONCATENATION(".", Set.of(Type.RATIONAL), 2),
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