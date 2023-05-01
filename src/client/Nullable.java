package client;

import facade.INullableFacade;
import facade.NullableFacadeImpl;

public class Nullable {
    public static void main(String[] args) {

        INullableFacade nullable = NullableFacadeImpl.getInstance();
        System.out.println(nullable.nullable(args));

        /*IExpression expression = new RationalExpression();
        expression.setExpression("a b * .");
        ExpressionEvaluator evaluator = ExpressionEvaluator.getInstance();
        System.out.println(evaluator.isNullable(expression));*/
    }
}
