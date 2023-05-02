package controller.facade;

import model.expression.evaluator.ExpressionEvaluator;
import model.xml.XMLManager;

public class NullableFacadeImpl implements INullableFacade {
    private static final INullableFacade instance = new NullableFacadeImpl();

    private NullableFacadeImpl() {}

    public static INullableFacade getInstance() {
        return instance;
    }

    public String nullable(String[] args) {
        if (args.length == 1) {
            ExpressionEvaluator evaluator = ExpressionEvaluator.getInstance();
            return evaluator.isNullable(XMLManager.load(args[0])).toString();
        } else {
            return "Wrong number of argument.";
        }
    }
}
