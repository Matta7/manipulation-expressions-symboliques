package model.stackhandler;

import controller.observer.AbstractListenableModel;
import model.expression.IExpression;
import model.expression.operator.OperatorHandler;
import model.expression.type.Type;

import java.util.EmptyStackException;

public class ExpressionStackHandler extends AbstractListenableModel {
    private ExpressionStack stack = new ExpressionStack();
    private String actualType = "";

    public ExpressionStackHandler() {}

    public void handleCommand(String command) {
        // On vérifie si l'élément est un opérateur
        if (OperatorHandler.isOperator(command, actualType)) {
            // Si c'est le cas, on vérifie qu'il est valide avec le type actuel d'model.expression manipulée
            if (OperatorHandler.getOperator(command, actualType).getExpressionType().contains(actualType)) {
                // Si c'est le cas, on vérifie qu'il y a assez d'éléments dans la pile pour appliquer l'opérateur
                if (OperatorHandler.getOperator(command, actualType).getArity() <= stack.size()) {
                    if (stack.peek().getToken().equals(actualType)) {
                        // Si c'est le cas, on applique l'opérateur
                        IExpression expression = NewExpression.getNewExpression(actualType);
                        // On construit l'model.expression fraichement créée avec les éléments de la pile
                        String res;
                        if (OperatorHandler.getOperator(command, actualType).getArity() == 2) {
                            IExpression expression2 = stack.pop();
                            if (getPeekExpression().getToken().equals(expression2.getToken())) {
                                IExpression expression1 = stack.pop();
                                res = expression1.getExpression() + " " + expression2.getExpression() + " " + command;
                            } else {
                                pushExpression(expression2);
                                throw new IllegalArgumentException("Expressions have two different types.\n");
                            }
                        } else {
                            IExpression expression1 = stack.pop();
                            res = expression1.getExpression() + " " + command;
                        }
                        expression.setExpression(res);
                        stack.push(expression);
                    } else {
                        throw new IllegalStateException("Actual type and expression stack does not match.\n");
                    }
                } else {
                    throw new IllegalArgumentException("Not enough elements in stack to apply operator \"" + command + "\".\n");
                }
            } else {
                throw new IllegalArgumentException("Operator \"" + command + "\" is not valid for " + actualType + " expressions.\n");
            }
        }
        // Sinon, on vérifie si l'élément est valide pour le type d'expression actuel.
        // Dans le cas d'expressions arithmétiques, on vérifie que l'élément est un nombre, au regard du type Double.
        else if (actualType.equals(Type.ARITHMETIC)) {
            try {
                Double.parseDouble(command);
                // Si c'est le cas, on crée une nouvelle expression arithmétique avec l'élément
                IExpression expression = NewExpression.getNewExpression(actualType);
                expression.setExpression(command);
                stack.push(expression);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Invalid symbol \"" + command + "\".\n");
            }
        }
        // Même chose que pour les expressions arithmétiques, à l'exception qu'on laisse passer la variable "x"
        else if (actualType.equals(Type.FUNCTION)) {
            if (command.matches("x")) {
                IExpression expression = NewExpression.getNewExpression(actualType);
                expression.setExpression(command);
                stack.push(expression);
            } else {
                try {
                    Double.parseDouble(command);
                    IExpression expression = NewExpression.getNewExpression(actualType);
                    expression.setExpression(command);
                    stack.push(expression);
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Invalid symbol \"" + command + "\".\n");
                }
            }
        }
        // Pour les expressions rationnelles, on vérifie que l'élément est soit un caractère alphabétique minuscule ou 1.
        else if (actualType.equals(Type.RATIONAL)) {
            if (command.matches("[a-z]|1")) {
                IExpression expression = NewExpression.getNewExpression(actualType);
                expression.setExpression(command);
                stack.push(expression);
            } else {
                throw new NumberFormatException("Invalid symbol \"" + command + "\".\n");
            }
        }
        hasChanged();
    }


    public String toString() {
        return stack.toString();
    }

    public String getActualType() {
        return actualType;
    }

    public void setActualType(String type) {
        actualType = type;
    }

    public boolean isActualTypeDefined() {
        return actualType.matches(Type.ARITHMETIC + "|" + Type.FUNCTION + "|" + Type.RATIONAL);
    }

    public void pushExpression(IExpression expression) {
        stack.push(expression);
    }

    public IExpression getPeekExpression() throws EmptyStackException {
        return stack.peek();
    }
}
