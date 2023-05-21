package controller.facade;

import model.expression.evaluator.ExpressionEvaluator;
import model.xml.XMLManager;

/**
 * Interface that the user use with Nullable program.
 */
public class NullableFacadeImpl implements INullableFacade {
    public NullableFacadeImpl() {}

    public String nullable(String[] args) {
        if (args.length == 1) {
            ExpressionEvaluator evaluator = new ExpressionEvaluator();
            return evaluator.isNullable(XMLManager.load(args[0])).toString();
        } else {
            return "Wrong number of argument.";
        }
    }
}
