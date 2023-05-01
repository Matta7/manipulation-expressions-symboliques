package client;

import expression.ExpressionEvaluator;
import expression.IExpression;
import expression.RationalExpression;
import xml.XMLManager;

public class Nullable {
    public static void main(String[] args) {

        /*if (args.length == 1) {
            ExpressionEvaluator evaluator = ExpressionEvaluator.getInstance();
            System.out.println(evaluator.evaluate(XMLManager.load(args[0]), null));
        } else {
            System.out.println("Wrong number of argument.\n");
        }*/

        IExpression expression = new RationalExpression();
        expression.setExpression("a b .");
        ExpressionEvaluator evaluator = ExpressionEvaluator.getInstance();
        System.out.println(evaluator.isNullable(expression));
    }
}
