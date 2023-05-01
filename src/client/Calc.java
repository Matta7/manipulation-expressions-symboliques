package client;

import expression.ExpressionEvaluator;
import xml.XMLManager;

public class Calc {

    public static void main(String[] args) {

        if (args.length == 1) {
            ExpressionEvaluator evaluator = ExpressionEvaluator.getInstance();
            System.out.println(evaluator.evaluate(XMLManager.load(args[0]), null));
        } else if (args.length == 2) {
            ExpressionEvaluator evaluator = ExpressionEvaluator.getInstance();
            System.out.println(evaluator.evaluate(XMLManager.load(args[0]), args[1]));
        } else {
            System.out.println("Wrong number of argument.\n");
        }
        /*IExpression expression = new FunctionExpression();
        expression.setExpression("9 x *");
        ExpressionEvaluator evaluator = ExpressionEvaluator.getInstance();
        System.out.println(evaluator.evaluate(expression, ""));*/
    }
}
