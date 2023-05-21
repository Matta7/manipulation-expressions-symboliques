package controller.facade;

public interface ICalcFacade {
    /**
     * Calc an expression using filename, and optionnally a xValue, all within args.
     * @param args filename and (xValue) are in this variable.
     * @return the result of the calc.
     */
    String calc(String[] args);
}
