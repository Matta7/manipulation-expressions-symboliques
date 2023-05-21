package client;

import controller.facade.CalcFacadeImpl;
import controller.facade.ICalcFacade;

public class Calc {

    public static void main(String[] args) {

        ICalcFacade calc = new CalcFacadeImpl();
        System.out.println(calc.calc(args));

        /*IExpression model.expression = new FunctionExpression();
        model.expression.setExpression("9 x *");
        ExpressionEvaluator evaluator = ExpressionEvaluator.getInstance();
        System.out.println(evaluator.evaluate(model.expression, ""));*/
    }
}
