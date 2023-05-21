package view;

import controller.observer.ModelListener;
import model.stackhandler.ExpressionStackHandler;

/**
 * Class that show all we have to show, using Expedid program.
 */
public class ExpedidView implements ModelListener {

    /**
     * An instance of the model.
     */
    private ExpressionStackHandler stackHandler;

    public ExpedidView(ExpressionStackHandler stackHandler) {
        this.stackHandler = stackHandler;
    }

    /**
     * Method that show a character.
     */
    private void showEditor() {
        System.out.print("> ");
    }

    /**
     * Method that show the stack.
     */
    private void showStack() {
        System.out.println(stackHandler.toString());
        if (stackHandler.isActualTypeDefined()) {
            System.out.println("  [" + stackHandler.getActualType() + "]");
        }
        this.showEditor();
    }

    /**
     * Method that show something.
     * @param str The thing to show.
     */
    public void show(String str) {
        System.out.println(str);
        this.showStack();
    }

    @Override
    public void somethingHasChanged(Object source) {
        this.showStack();
    }
}