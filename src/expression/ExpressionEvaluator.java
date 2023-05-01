package expression;

import java.util.Stack;

public class ExpressionEvaluator {
  private static ExpressionEvaluator instance = new ExpressionEvaluator();

  private ExpressionEvaluator() {}

  public static ExpressionEvaluator getInstance() {
    return instance;
  }

  public String evaluate(IExpression expression, String xValue) {
    String result = "";
    if (expression.getToken().equals("arith")) {
      result = calculateExpression(expression);
    } else if (expression.getToken().equals("functional")) {
      // Si "x" est dans l'expression, alors on vérifie que xValue ne soit pas null et on remplace toutes les occurences de "x" par xValue.
      if (expression.getExpression().contains("x")) {
        if (xValue != null) {
          IExpression calculableExpression = new ArithmeticExpression();
          calculableExpression.setExpression(expression.getExpression().replaceAll("x", xValue));
          result = calculateExpression(calculableExpression);
        } else {
          result = "Invalid command, could not replace x, xValue is null";
        }
      } else {
        result = calculateExpression(expression);
      }
    } else if (expression.getToken().equals("rational")) {
      result = "Invalid expression, cannot evaluate rational expressions";
    }
    return result;
  }

  private String calculateExpression(IExpression expression) {
    // On calcule le résultat de l'expression, il s'agit une chaine de caractères contenant des nombres sous forme de chaine de caractères,
    // qui peuvent être convertis en nombres réels, grace a Double.parseDouble (voir ci-dessous) et de symboles d'opérateurs.

    // La notation se fait en notation polonaise inverse, c'est à dire que les opérateurs sont placés après les opérandes.

    // On utilise une pile pour stocker les opérandes et les opérateurs.
    Stack<String> stack = new Stack<String>();
    // On sépare l'expression en un tableau de String, en utilisant l'espace comme séparateur.
    String[] expressionArray = expression.getExpression().split(" ");
    // On parcourt le tableau de String.
    for (int i = 0; i < expressionArray.length; i++) {
      // Si l'élément est un opérateur, on le dépile, on dépile les deux opérandes précédentes, on les convertit en nombres réels, on applique l'opérateur et on empile le résultat.
      if (expressionArray[i].equals("+")) {
        Double operand2 = Double.parseDouble(stack.pop());
        Double operand1 = Double.parseDouble(stack.pop());
        stack.push(Double.toString(operand1 + operand2));
      } else if (expressionArray[i].equals("-")) {
        Double operand2 = Double.parseDouble(stack.pop());
        Double operand1 = Double.parseDouble(stack.pop());
        stack.push(Double.toString(operand1 - operand2));
      } else if (expressionArray[i].equals("*")) {
        Double operand2 = Double.parseDouble(stack.pop());
        Double operand1 = Double.parseDouble(stack.pop());
        stack.push(Double.toString(operand1 * operand2));
      } else if (expressionArray[i].equals("/")) {
        Double operand2 = Double.parseDouble(stack.pop());
        Double operand1 = Double.parseDouble(stack.pop());
        stack.push(Double.toString(operand1 / operand2));
      } else if (expressionArray[i].equals("~")) {
        Double operand = Double.parseDouble(stack.pop());
        stack.push(Double.toString(-operand));
      } else {
        // Si l'élément n'est pas un opérateur, on l'empile.
        stack.push(expressionArray[i]);
      }
    }
    // On retourne le dernier élément de la pile, qui est le résultat de l'expression.
    return stack.pop();
  }

  public Boolean isNullable(IExpression expression) throws IllegalArgumentException {
    if (!expression.getToken().equals("rational")) {
      throw new IllegalArgumentException("Type d'expression non supporté");
    }
    // On vérifie si l'expression est nullable, c'est à dire si elle reconnaît le mot vide.
    // On utilise une pile pour stocker les opérandes et les opérateurs.
    Stack<Boolean> stack = new Stack<Boolean>();
    // On sépare l'expression en un tableau de String, en utilisant l'espace comme séparateur.
    String[] expressionArray = expression.getExpression().split(" ");
    // On parcourt le tableau de String.
    for (int i = 0; i < expressionArray.length; i++) {
      // Si l'élément est un opérateur, on le dépile, on dépile les deux opérandes précédentes, on applique l'opérateur et on empile le résultat.
      if (expressionArray[i].equals("+")) {
        Boolean operand2 = stack.pop();
        Boolean operand1 = stack.pop();
        stack.push(operand1 || operand2);
      } else if (expressionArray[i].equals(".")) {
        Boolean operand2 = stack.pop();
        Boolean operand1 = stack.pop();
        stack.push(operand1 && operand2);
      } else if (expressionArray[i].equals("*")) {
        // Peu importe la valeur de l'opérande, si on le passe à l'étoile de Kleene, on reconnaitra le mot vide.
        stack.pop();
        stack.push(true);
      } else {
        // Si l'élément n'est pas un opérateur, on l'empile.
        if (expressionArray[i].equals("1")) {
          stack.push(true);
        } else {
          stack.push(false);
        }
      }
    }

    // On retourne le dernier élément de la pile, qui est le résultat de l'expression.
    return stack.peek();
  }
}
