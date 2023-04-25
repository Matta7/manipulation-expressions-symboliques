package facade;

import xml.XMLManager;
import expression.IExpression;
import expression.operator.OperatorEnum;
import factory.ExpressionFactory;
import factory.IExpressionFactory;

import java.io.File;
import java.util.EmptyStackException;
import java.util.Iterator;
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

    public String enter(String command) {
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
        } else {
            if (separetedCommand.length != 1) {
                return "Wrong number of argument.";
            }
            // Write something at the top of the stack.
            try {
                System.out.println(separetedCommand[0]);
                IExpression expression = getNewExpression();
                stack.push(expression);
                showStack();
                return null;
            } catch (IllegalStateException e) {
                return e.getMessage();
            }
        }
    }

    private IExpression getNewExpression() {
        IExpressionFactory factory = ExpressionFactory.getInstance();

        switch (actualType) {
            case "arith" -> {
                return factory.makeArithmetic();
            }

            case "func" -> {
                return factory.makeFunction();
            }

            case "rat" -> {
                return factory.makeRational();
            }

            default -> throw new IllegalStateException("No type defined");
        }
    }

    private void showStack() {
        int stackLen = stack.size();

        Iterator<IExpression> value = stack.iterator();
        while (value.hasNext()) {
            System.out.println(stackLen + " : [" + actualType + "] " + "salutoui");
        }
    }

    public String type() {
        String result = "";
        //On parcourt la liste des opérateurs dans OperatorEnum, en affichant 
        //le symbole, le nom, le type d'expression auquel il s'applique et
        //son arité.
        for (OperatorEnum operator : OperatorEnum.values()) {
            result +="\"" + operator.getSymbol() + "\"" 
            + " " + operator.name() 
            + " " + operator.getExpressionClass().getSimpleName() 
            + " " + operator.getArity() + "\n";
        }
        //On ajoute le type actuel et on retourne le nom des 3 types d'expressions
        result += "Type actuel : " + actualType + "\n";
        result += "Types disponibles : arith, rat, func\n";

        return result;
    }

    public void type(String type) throws IllegalArgumentException {
        if (type.equals("arith") || type.equals("rat") || type.equals("func")) {
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

        //XMLToExpressionAdapter adapter = new XMLToExpressionAdapter(fileName);

        //On ajoute l'expression à la pile
        stack.push(expression);
    }
}
