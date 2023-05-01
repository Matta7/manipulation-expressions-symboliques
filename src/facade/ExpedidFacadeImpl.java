package facade;

import expression.type.Type;
import xml.XMLManager;
import expression.IExpression;
import expression.operator.OperatorEnum;
import handler.ExpressionStackHandler;

import java.io.File;
import java.util.EmptyStackException;

public class ExpedidFacadeImpl implements IExpedidFacade {
    //Attributs
    private ExpressionStackHandler handler = ExpressionStackHandler.getInstance();

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
                        System.out.println("Switched to " + separetedCommand[1] + " type.");
                        return handler.showStack();
                    } catch (IllegalArgumentException e) {
                        return e.getMessage();
                    }
                } else {
                    System.out.println("Wrong number of argument.");
                    return handler.showStack();
                }
            }

            case "!save" -> {
                if (separetedCommand.length == 2) {
                    try {
                        this.save(separetedCommand[1]);
                        return "Successfully saved file.";
                    } catch (IllegalArgumentException | IllegalStateException e) {
                        System.out.println(e.getMessage());
                        return handler.showStack();
                    }
                } else {
                    System.out.println("Wrong number of argument.");
                    return handler.showStack();
                }
            }

            case "!load" -> {
                if (separetedCommand.length == 2) {
                    try {
                        this.load(separetedCommand[1]);
                        return handler.showStack();
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        return handler.showStack();
                    }
                } else {
                    System.out.println("Wrong number of argument.");
                    return handler.showStack();
                }
            }
        }

        if (command.startsWith("!")) {
            System.out.println("Unknown command \"" + command.split(" ")[0] + "\"");
            return handler.showStack();
        }

        if (separetedCommand.length != 1) {
            System.out.println("Wrong number of argument.");
            return handler.showStack();
        }

        // On suppose qu'on doit ajouter un élément à la pile.
        handler.handleCommand(command);

        return handler.showStack();
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
        result += "Type actuel : " + handler.getActualType() + "\n";
        result += "Types disponibles : " + Type.ARITHMETIC + ", " + Type.FUNCTION + ", " + Type.RATIONAL +"\n";

        return result;
    }

    public void type(String type) throws IllegalArgumentException {
        if (type.equals(Type.ARITHMETIC) || type.equals(Type.FUNCTION) || type.equals(Type.RATIONAL)) {
            handler.setActualType(type);
        } else {
            throw new IllegalArgumentException("Type inconnu");
        }
    }

    public void save(String fileName) throws IllegalStateException, IllegalArgumentException {
        //On vérifie que la pile n'est pas vide
        try {
            handler.getPeekExpression();
        } catch (EmptyStackException e) {
            throw new IllegalStateException("La pile est vide");
        }
        //On vérifie que le fichier se termine en ".xml" et que celui-ci n'est pas null
        if (fileName == null || !fileName.endsWith(".xml")) {
            throw new IllegalArgumentException("Le nom du fichier est incorrect");
        }

        //On récupère le sommet de la pile sans le pop
        IExpression expression = handler.getPeekExpression();
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
        handler.pushExpression(expression);
    }
}
