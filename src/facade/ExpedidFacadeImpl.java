package facade;

import expression.type.Type;
import xml.XMLManager;
import expression.ArithmeticExpression;
import expression.IExpression;
import expression.operator.OperatorEnum;
import factory.ExpressionFactory;
import factory.IExpressionFactory;

import java.io.File;
import java.util.EmptyStackException;
import java.util.Stack;

public class ExpedidFacadeImpl implements IExpedidFacade {
    //Attributs
    private Stack<IExpression> stack = new Stack<>();
    private String actualType = "";


    private static ExpedidFacadeImpl expedidFacade = new ExpedidFacadeImpl();

    private ExpedidFacadeImpl() {}

    public static ExpedidFacadeImpl getInstance() {
        return expedidFacade;
    }

    public String enter(String command) throws IllegalArgumentException, IllegalStateException, NumberFormatException {
        String[] separetedCommand = command.split(" ");

        switch (separetedCommand[0]) {
            case "!quit" -> {
                return "quit";
            }

            case "!type" -> {
                if (separetedCommand.length == 1) {
                    return this.type();
                } else if (separetedCommand.length == 2) {
                    try {
                        this.type(separetedCommand[1]);
                        return ("Switched to " + separetedCommand[1] + " type.");
                    } catch (IllegalArgumentException e) {
                        return e.getMessage();
                    }
                } else {
                    return "Wrong number of argument.";
                }
            }

            case "!save" -> {
                if (separetedCommand.length == 2) {
                    try {
                        this.save(separetedCommand[1]);
                        return "Successfully saved file.";
                    } catch (IllegalArgumentException | IllegalStateException e) {
                        return e.getMessage();
                    }
                } else {
                    return "Wrong number of argument.";
                }
            }

            case "!load" -> {
                if (separetedCommand.length == 2) {
                    try {
                        this.load(separetedCommand[1]);
                        return "Successfully loaded file.";
                    } catch (IllegalArgumentException e) {
                        return e.getMessage();
                    }
                } else {
                    return "Wrong number of argument.";
                }
            }
        }

        if (command.startsWith("!")) {
            return "Unknown command \"" + command.split(" ")[0] + "\"";
        }

        if (separetedCommand.length != 1) {
            return "Wrong number of argument.";
        }

        // On suppose qu'on doit ajouter un élément à la pile.
        
        // On vérifie si l'élément est un opérateur
        if (OperatorEnum.isOperator(command)) {
            // Si c'est le cas, on vérifie qu'il est valide avec le type actuel d'expression manipulée
            if (OperatorEnum.getOperator(command).getExpressionType().contains(actualType)) {
                // Si c'est le cas, on vérifie qu'il y a assez d'éléments dans la pile pour appliquer l'opérateur
                if (OperatorEnum.getOperator(command).getArity() <= stack.size()) {
                    // Si c'est le cas, on applique l'opérateur
                    try {
                        IExpression expression = getNewExpression();
                        // On construit l'expression fraichement créée avec les éléments de la pile
                        String res = "";
                        if (OperatorEnum.getOperator(command).getArity() == 2) {
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
                        return e.getMessage();
                    }
                } else {
                    return "Not enough elements in stack to apply operator \"" + command + "\".";
                }
            } else {
                return "Operator \"" + command + "\" is not valid for " + actualType + " expressions.";
            }
        }
        // Sinon, on vérifie si l'élément est valide pour le type d'expression actuel.
        // Dans le cas d'expressions arithmétiques, on vérifie que l'élément est un nombre, au regard du type Double.
        else if (actualType.equals("arith")) {
            try {
                Double.parseDouble(command);
            } catch (NumberFormatException e) {
                return "Invalid number \"" + command + "\".";
            }
            // Si c'est le cas, on crée une nouvelle expression arithmétique avec l'élément
            IExpression expression = getNewExpression();
            expression.setExpression(command);
            stack.push(expression);
        }
        // Même chose que pour les expressions arithmétiques, à l'exception qu'on laisse passer la variable "x"
        else if (actualType.equals("function")) {
            if (command.matches("x")) {
                IExpression expression = getNewExpression();
                expression.setExpression(command);
                stack.push(expression);
            } else {
                try {
                    Double.parseDouble(command);
                } catch (NumberFormatException e) {
                    return "Invalid number \"" + command + "\".";
                }
                IExpression expression = getNewExpression();
                expression.setExpression(command);
                stack.push(expression);
            }
        }
        // Pour les expressions rationnelles, on vérifie que l'élément est soit un caractère alphabétique minuscule ou 1.
        else if (actualType.equals(Type.RATIONAL)) {
            if (command.matches("[a-z]|1")) {
                IExpression expression = getNewExpression();
                expression.setExpression(command);
                stack.push(expression);
            } else {
                return "Invalid number \"" + command + "\".";
            }
        }

        return showStack();
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

    private String showStack() {
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

    public String type() {
        String result = "";
        //On parcourt la liste des opérateurs dans OperatorEnum, en affichant 
        //le symbole, le nom, le type d'expression auquel il s'applique et
        //son arité.
        for (OperatorEnum operator : OperatorEnum.values()) {
            result +="\"" + operator.getSymbol() + "\"" 
            + " " + operator.name() 
            + " " + operator.getExpressionType().toString()
            + " " + operator.getArity() + "\n";
        }
        //On ajoute le type actuel et on retourne le nom des 3 types d'expressions
        result += "Type actuel : " + actualType + "\n";
        result += "Types disponibles : arith, function, rational\n";

        return result;
    }

    public void type(String type) throws IllegalArgumentException {
        if (type.equals("arith") || type.equals("function") || type.equals("rational")) {
            actualType = type;
        } else {
            throw new IllegalArgumentException("Type inconnu");
        }
    }

    public void save(String fileName) throws IllegalStateException, IllegalArgumentException {
        //On vérifie que la pile n'est pas vide
        try {
            stack.peek();
        } catch (EmptyStackException e) {
            throw new IllegalStateException("La pile est vide");
        }
        //On vérifie que le fichier se termine en ".xml" et que celui-ci n'est pas null
        if (fileName == null || !fileName.endsWith(".xml")) {
            throw new IllegalArgumentException("Le nom du fichier est incorrect");
        }

        //On récupère le sommet de la pile sans le pop
        IExpression expression = stack.peek();
        //On sauvegarde l'expression dans le fichier
        expression.save(fileName);
    }

    public void load(String fileName) throws IllegalArgumentException {
        //On vérifie que le fichier se termine en ".xml" et que celui-ci n'est pas null
        if (fileName == null || !fileName.endsWith(".xml")) {
            throw new IllegalArgumentException("Le nom du fichier est incorrect");
        }
        //On vérifie que le fichier existe pour l'ouvrir
        File file = new File(fileName);
        if (!file.exists()) {
            throw new IllegalArgumentException("Le fichier n'existe pas");
        }

        IExpression expression = XMLManager.load(fileName);

        //On ajoute l'expression à la pile
        stack.push(expression);
    }
}
