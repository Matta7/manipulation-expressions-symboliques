package model.expression.operator;

import java.util.Set;

public interface IOperator {

    String getSymbol();

    Set<String> getSupportedExpressionTypes();

    int getArity();
  
}
