package facade;

import expression.ExpressionEvaluator;
import xml.XMLManager;

public class CalcFacadeImpl implements ICalcFacade {

    private static final ICalcFacade instance = new CalcFacadeImpl();

    private CalcFacadeImpl() {}

    public static ICalcFacade getInstance() {
        return instance;
    }

    public String calc(String[] args) {
        if (args.length == 1) {
            ExpressionEvaluator evaluator = ExpressionEvaluator.getInstance();
            return evaluator.evaluate(XMLManager.load(args[0]));
        } else if (args.length == 2) {
            ExpressionEvaluator evaluator = ExpressionEvaluator.getInstance();
            return evaluator.evaluate(XMLManager.load(args[0]), args[1]);
        } else {
            return "Wrong number of argument.";
        }
    }
}
