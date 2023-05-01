package client;

import facade.CalcFacadeImpl;
import facade.ICalcFacade;

public class Calc {

    public static void main(String[] args) {

        ICalcFacade calc = CalcFacadeImpl.getInstance();
        System.out.println(calc.calc(args));

        /*IExpression expression = new FunctionExpression();
        expression.setExpression("9 x *");
        ExpressionEvaluator evaluator = ExpressionEvaluator.getInstance();
        System.out.println(evaluator.evaluate(expression, ""));*/
    }
}
