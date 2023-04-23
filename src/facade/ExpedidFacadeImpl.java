package facade;

import java.util.EmptyStackException;
import java.util.Stack;

import adapter.XMLToExpressionAdapter;

import java.io.File;

import expression.IExpression;
import expression.operator.OperatorEnum;

public class ExpedidFacadeImpl implements IExpedidFacade {
    //Attributs
    private Stack<IExpression> stack = new Stack<IExpression>();
    private String actualType = "";

    private static ExpedidFacadeImpl expedidFacade = new ExpedidFacadeImpl();

    private ExpedidFacadeImpl() {

    }

    public static ExpedidFacadeImpl getInstance() {
        return expedidFacade;
    }

    public String enter(String command) {
        if (command == "!quit") {
            return quit();
        } else if (command.startsWith("!type")) {
            return null;
        }

        return null;
    }

    public String quit() {
        return null;
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

        XMLToExpressionAdapter adapter = new XMLToExpressionAdapter(file);

        //On ajoute l'expression à la pile
        stack.push(adapter);
    }
}
