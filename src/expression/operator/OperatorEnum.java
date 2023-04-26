package expression.operator;

import expression.type.Type;

import java.util.Set;

public enum OperatorEnum {
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
    public static OperatorEnum getOperator(String symbol, String type) {
        for (OperatorEnum operator : OperatorEnum.values()) {
            if (operator.getSymbol().equals(symbol) && operator.getExpressionType().contains(type)) {
                return operator;
            }
        }
        return null;
    }

    public static boolean isOperator(String symbol, String type) {
        return getOperator(symbol, type) != null;
    }

    public static boolean isArithmeticOperator(String symbol) {
        OperatorEnum operator = getOperator(symbol, Type.ARITHMETIC);
        return operator != null;
    }

    public static boolean isFunctionOperator(String symbol) {
        OperatorEnum operator = getOperator(symbol, Type.FUNCTION);
        return operator != null;
    }

    public static boolean isRationalOperator(String symbol) {
        OperatorEnum operator = getOperator(symbol, Type.RATIONAL);
        return operator != null;
    }
}