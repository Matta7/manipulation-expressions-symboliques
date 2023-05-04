package controller.facade;

import model.expression.type.Type;
import model.xml.XMLManager;
import model.expression.IExpression;
import model.expression.operator.OperatorEnum;
import model.stackhandler.ExpressionStackHandler;
import view.ExpedidView;

import java.util.EmptyStackException;

public class ExpedidFacadeImpl implements IExpedidFacade {
    //Attributs
    private ExpressionStackHandler handler = ExpressionStackHandler.getInstance();

    private ExpedidView view = new ExpedidView(handler);

    private static ExpedidFacadeImpl expedidFacade = new ExpedidFacadeImpl();

    private ExpedidFacadeImpl() {
        handler.addModelListener(view);
        view.show("");
    }

    public static ExpedidFacadeImpl getInstance() {
        return expedidFacade;
    }

    public void enter(String command) throws IllegalArgumentException, IllegalStateException, NumberFormatException {
        String[] separetedCommand = command.split(" ");
        boolean isCommand = command.startsWith("!");

        if (isCommand) {
            boolean unknownCommand = true;
            switch (separetedCommand[0]) {
                case "!quit" -> {
                    unknownCommand = false;
                    System.exit(0);
                }

                case "!type" -> {
                    unknownCommand = false;
                    if (separetedCommand.length == 1) {
                        view.show(this.type());
                    } else if (separetedCommand.length == 2) {
                        try {
                            this.type(separetedCommand[1]);
                            view.show("Switched to " + separetedCommand[1] + " type.\n");
                        } catch (Exception e) {
                            view.show(e.getMessage());
                        }
                    } else {
                        view.show("Wrong number of argument.\n");
                    }
                }

                case "!save" -> {
                    unknownCommand = false;
                    if (separetedCommand.length == 2) {
                        try {
                            this.save(separetedCommand[1]);
                            view.show("Successfully saved file.\n");
                        } catch (Exception e) {
                            view.show(e.getMessage());
                        }
                    } else {
                        view.show("Wrong number of argument.\n");
                    }
                }

                case "!load" -> {
                    unknownCommand = false;
                    if (separetedCommand.length == 2) {
                        try {
                            this.load(separetedCommand[1]);
                        } catch (IllegalArgumentException e) {
                            view.show(e.getMessage());
                        }
                    } else {
                        view.show("Wrong number of argument.\n");
                    }
                }
            }

            if (unknownCommand) {
                view.show("Unknown command \"" + command.split(" ")[0] + "\".\n");
            }
        } else {
            if (separetedCommand.length != 1) {
                view.show("Wrong number of argument.\n");
            } else {
                // On doit ajouter un élément à la pile.
                try {
                    handler.handleCommand(command);
                } catch (Exception e) {
                    view.show(e.getMessage());
                }
            }
        }
    }

    public String type() {
        String result = "";
        //On parcourt la liste des opérateurs dans OperatorEnum, en affichant 
        //le symbole, le nom, le type d'model.expression auquel il s'applique et
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
            throw new IllegalArgumentException("Unknown type.\n");
        }
    }

    public void save(String fileName) throws IllegalStateException, IllegalArgumentException {
        //On vérifie que la pile n'est pas vide
        try {
            handler.getPeekExpression();
        } catch (EmptyStackException e) {
            throw new IllegalStateException("Stack is empty.\n");
        }

        //On récupère le sommet de la pile sans le pop
        IExpression expression = handler.getPeekExpression();
        //On sauvegarde l'model.expression dans le fichier
        expression.save(fileName);
    }

    public void load(String fileName) throws IllegalArgumentException {
        IExpression expression = XMLManager.load(fileName);

        //On ajoute l'model.expression à la pile
        handler.pushExpression(expression);
    }
}
