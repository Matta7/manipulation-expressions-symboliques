package model.expression.evaluator;

import model.expression.ArithmeticExpression;
import model.expression.IExpression;
import model.expression.type.Type;

import java.util.Stack;

public class ExpressionEvaluator {
    private static ExpressionEvaluator instance = new ExpressionEvaluator();

    private ExpressionEvaluator() {}

    public static ExpressionEvaluator getInstance() {
        return instance;
    }

    public String evaluate(IExpression expression) {
        if (expression.getToken().equals(Type.ARITHMETIC)) {
            return calculateExpression(expression);
        } else if (expression.getToken().equals(Type.FUNCTION)) {
            // Si "x" est dans l'expression, alors on vérifie que xValue ne soit pas null et on remplace toutes les occurences de "x" par xValue.
            if (expression.getExpression().contains("x")) {
                return "Invalid command, could not replace x, xValue is null";
            } else {
                return calculateExpression(expression);
            }
        } else if (expression.getToken().equals(Type.RATIONAL)) {
            return "Invalid model.expression, cannot evaluate rational expressions";
        } else {
            return "Unknown file type.";
        }
    }

    public String evaluate(IExpression expression, String xValue) {
        if (expression.getToken().equals(Type.ARITHMETIC)) {
            return "Wrong number of argument.";
        } else if (expression.getToken().equals(Type.FUNCTION)) {
            // Si "x" est dans l'expression, alors on vérifie que xValue ne soit pas null et on remplace toutes les occurences de "x" par xValue.
            if (expression.getExpression().contains("x")) {
                if (xValue != null) {
                    IExpression calculableExpression = new ArithmeticExpression();
                    calculableExpression.setExpression(expression.getExpression().replaceAll("x", xValue));
                    return calculateExpression(calculableExpression);
                } else {
                    return "Invalid argument, x is null";
                }
            } else {
                return calculateExpression(expression);
            }
        } else if (expression.getToken().equals(Type.RATIONAL)) {
            return "Invalid expression, cannot evaluate rational expressions";
        } else {
            return "Unknown file type.";
        }
    }

    private String calculateExpression(IExpression expression) throws IllegalArgumentException {
        // On calcule le résultat de l'expression, il s'agit une chaine de caractères contenant des nombres sous forme de chaine de caractères,
        // qui peuvent être convertis en nombres réels, grace a Double.parseDouble (voir ci-dessous) et de symboles d'opérateurs.

        // La notation se fait en notation polonaise inverse, c'est à dire que les opérateurs sont placés après les opérandes.

        // On utilise une pile pour stocker les opérandes et les opérateurs.
        Stack<String> stack = new Stack<>();
        // On sépare l'expression en un tableau de String, en utilisant l'espace comme séparateur.
        String[] expressionArray = expression.getExpression().split(" ");
        // On parcourt le tableau de String.
        for (String symbol : expressionArray) {
            // Si l'élément est un opérateur, on le dépile, on dépile les deux opérandes précédentes, on les convertit en nombres réels, on applique l'opérateur et on empile le résultat.
            switch (symbol) {
                case "+" -> {
                    double operand2 = Double.parseDouble(stack.pop());
                    double operand1 = Double.parseDouble(stack.pop());
                    stack.push(Double.toString(operand1 + operand2));
                }
                case "-" -> {
                    double operand2 = Double.parseDouble(stack.pop());
                    double operand1 = Double.parseDouble(stack.pop());
                    stack.push(Double.toString(operand1 - operand2));
                }
                case "*" -> {
                    double operand2 = Double.parseDouble(stack.pop());
                    double operand1 = Double.parseDouble(stack.pop());
                    stack.push(Double.toString(operand1 * operand2));
                }
                case "/" -> {
                    double operand2 = Double.parseDouble(stack.pop());
                    double operand1 = Double.parseDouble(stack.pop());
                    stack.push(Double.toString(operand1 / operand2));
                }
                case "~" -> {
                    double operand = Double.parseDouble(stack.pop());
                    stack.push(Double.toString(-operand));
                }
                default -> stack.push(symbol);
                // Si l'élément n'est pas un opérateur, on l'empile.
            }
        }
        // On retourne le dernier élément de la pile, qui est le résultat de l'model.expression.

        // On s'assure que la pile ne contient qu'un seul élément, qui est le résultat de l'model.expression.
        if (stack.size() != 1) {
          throw new IllegalArgumentException("Invalid model.expression.");
        }
        return stack.pop();
    }

    public Boolean isNullable(IExpression expression) throws IllegalArgumentException {
    if (!expression.getToken().equals(Type.RATIONAL)) {
      throw new IllegalArgumentException("Expression type not supported.");
    }
    // On vérifie si l'model.expression est nullable, c'est à dire si elle reconnaît le mot vide.
    // On utilise une pile pour stocker les opérandes et les opérateurs.
    Stack<Boolean> stack = new Stack<>();
    // On sépare l'expression en un tableau de String, en utilisant l'espace comme séparateur.
    String[] expressionArray = expression.getExpression().split(" ");
    // On parcourt le tableau de String.
        for (String symbol : expressionArray) {
            // Si l'élément est un opérateur, on le dépile, on dépile les deux opérandes précédentes, on applique l'opérateur et on empile le résultat.
            switch (symbol) {
                case "+" -> {
                    boolean operand2 = stack.pop();
                    boolean operand1 = stack.pop();
                    stack.push(operand1 || operand2);
                }
                case "." -> {
                    boolean operand2 = stack.pop();
                    boolean operand1 = stack.pop();
                    stack.push(operand1 && operand2);
                }
                case "*" -> {
                    // Peu importe la valeur de l'opérande, si on le passe à l'étoile de Kleene, on reconnaitra le mot vide.
                    stack.pop();
                    stack.push(true);
                }
                default ->
                    // Si l'élément n'est pas un opérateur, on l'empile.
                        stack.push(symbol.equals("1"));
            }
        }

    // On retourne le dernier élément de la pile, qui est le résultat de l'expression.
    // On s'assure que la pile ne contienne qu'un seul élément.
    if (stack.size() != 1) {
      throw new IllegalArgumentException("Invalid expression");
    }

    return stack.pop();
    }
}
