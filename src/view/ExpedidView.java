package view;

import controller.observer.ModelListener;
import model.stackhandler.ExpressionStackHandler;

public class ExpedidView implements ModelListener {

    private ExpressionStackHandler stackHandler;

    public ExpedidView(ExpressionStackHandler stackHandler) {
        this.stackHandler = stackHandler;
    }

    private void showEditor() {
        System.out.print("> ");
    }

    private void showStack() {
        System.out.println(stackHandler.toString());
        if (stackHandler.isActualTypeDefined()) {
            System.out.println("  [" + stackHandler.getActualType() + "]");
        }
        this.showEditor();
    }

    public void show(String str) {
        System.out.println(str);
        this.showStack();
    }

    @Override
    public void somethingHasChanged(Object source) {
        if (source instanceof ExpressionStackHandler) {
            this.showStack();
        }
    }
}
