package handler;

import java.util.EmptyStackException;
import java.util.Stack;

import expression.IExpression;
import expression.operator.OperatorEnum;
import expression.type.Type;
import factory.ExpressionFactory;
import factory.IExpressionFactory;

public class ExpressionStackHandler {
  //Attributs
  private static ExpressionStackHandler handler = new ExpressionStackHandler();
  private Stack<IExpression> stack = new Stack<>();
  private String actualType = "";

  private ExpressionStackHandler() {}

  public static ExpressionStackHandler getInstance() {
    return handler;
  }

  public void handleCommand(String command) {
    // On vérifie si l'élément est un opérateur
    if (OperatorEnum.isOperator(command, actualType)) {
      // Si c'est le cas, on vérifie qu'il est valide avec le type actuel d'expression manipulée
      if (OperatorEnum.getOperator(command, actualType).getExpressionType().contains(actualType)) {
        // Si c'est le cas, on vérifie qu'il y a assez d'éléments dans la pile pour appliquer l'opérateur
        if (OperatorEnum.getOperator(command, actualType).getArity() <= stack.size()) {
          // Si c'est le cas, on applique l'opérateur
          try {
            IExpression expression = getNewExpression();
            // On construit l'expression fraichement créée avec les éléments de la pile
            String res = "";
            if (OperatorEnum.getOperator(command, actualType).getArity() == 2) {
              IExpression expression2 = stack.pop();
              IExpression expression1 = stack.pop();
              res = expression1.getExpression() + " " + expression2.getExpression() + " " + command;
            } else {
              IExpression expression1 = stack.pop();
              res = expression1.getExpression() + " " + command;
            }
            expression.setExpression(res);
            stack.push(expression);
          } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
          }
        } else {
          System.out.println("Not enough elements in stack to apply operator \"" + command + "\".");
        }
    } else {
      System.out.println("Operator \"" + command + "\" is not valid for " + actualType + " expressions.");
    }
  }
  // Sinon, on vérifie si l'élément est valide pour le type d'expression actuel.
  // Dans le cas d'expressions arithmétiques, on vérifie que l'élément est un nombre, au regard du type Double.
  else if (actualType.equals(Type.ARITHMETIC)) {
    try {
      Double.parseDouble(command);
      // Si c'est le cas, on crée une nouvelle expression arithmétique avec l'élément
      IExpression expression = getNewExpression();
      expression.setExpression(command);
      stack.push(expression);
    } catch (NumberFormatException e) {
      System.out.println("Invalid number \"" + command + "\".");
    }
  }
  // Même chose que pour les expressions arithmétiques, à l'exception qu'on laisse passer la variable "x"
    else if (actualType.equals(Type.FUNCTION)) {
      if (command.matches("x")) {
        IExpression expression = getNewExpression();
        expression.setExpression(command);
        stack.push(expression);
      } else {
        try {
          Double.parseDouble(command);
          IExpression expression = getNewExpression();
          expression.setExpression(command);
          stack.push(expression);
        } catch (NumberFormatException e) {
          System.out.println("Invalid number \"" + command + "\".");
        }
      }
    }
    // Pour les expressions rationnelles, on vérifie que l'élément est soit un caractère alphabétique minuscule ou 1.
    else if (actualType.equals(Type.RATIONAL)) {
      if (command.matches("[a-z]|1")) {
        IExpression expression = getNewExpression();
        expression.setExpression(command);
        stack.push(expression);
      } else {
        System.out.println("Invalid number \"" + command + "\".");
      }
    }
  }


  public String showStack() {

    if (stack.isEmpty()) {
      return "Stack is empty.";
    }
    
    int stackIndex = stack.size();
    StringBuilder stackStr = new StringBuilder();

    for (IExpression expression : stack) {
      stackIndex-=1;
      stackStr.append(stackIndex)
              .append(" : ")
              .append(expression.toString())
              .append("\n");
    }

    return stackStr.toString();
  }
    
  private IExpression getNewExpression() throws IllegalArgumentException {
    IExpressionFactory factory = ExpressionFactory.getInstance();

    switch (actualType) {
        case Type.ARITHMETIC -> {
            return factory.makeArithmetic();
        }

        case Type.FUNCTION -> {
            return factory.makeFunction();
        }

        case (Type.RATIONAL) -> {
            return factory.makeRational();
        }

        default -> throw new IllegalStateException("No type defined");
    }
  }

  public String getActualType() {
    return actualType;
  }

  public void setActualType(String type) {
    actualType = type;
  }

  public void pushExpression(IExpression expression) {
    stack.push(expression);
  }

  public IExpression getPeekExpression() throws EmptyStackException {
    return stack.peek();
  }
}
