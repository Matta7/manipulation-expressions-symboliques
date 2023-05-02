package client;

import controller.facade.INullableFacade;
import controller.facade.NullableFacadeImpl;

public class Nullable {
    public static void main(String[] args) {

        INullableFacade nullable = NullableFacadeImpl.getInstance();
        System.out.println(nullable.nullable(args));

        /*IExpression model.expression = new RationalExpression();
        model.expression.setExpression("a b * .");
        ExpressionEvaluator evaluator = ExpressionEvaluator.getInstance();
        System.out.println(evaluator.isNullable(model.expression));*/
    }
}
