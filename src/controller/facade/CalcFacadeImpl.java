package controller.facade;

import model.expression.evaluator.ExpressionEvaluator;
import model.xml.XMLManager;

/**
 * Interface that the user use with Calc program.
 */
public class CalcFacadeImpl implements ICalcFacade {

    public CalcFacadeImpl() {}

    public String calc(String[] args) {
        if (args.length == 1) {
            ExpressionEvaluator evaluator = new ExpressionEvaluator();
            return evaluator.evaluate(XMLManager.load(args[0]));
        } else if (args.length == 2) {
            ExpressionEvaluator evaluator = new ExpressionEvaluator();
            return evaluator.evaluate(XMLManager.load(args[0]), args[1]);
        } else {
            return "Wrong number of argument.";
        }
    }
}
