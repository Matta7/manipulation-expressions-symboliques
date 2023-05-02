package view;

import controller.observer.ModelListener;
import model.stackhandler.ExpressionStackHandler;

public class ExpedidView implements ModelListener {

    public void showEnterSymbol(ExpressionStackHandler stackHandler) {

    }

    public void showError(String error) {

    }

    @Override
    public void somethingHasChanged(Object source) {
        if (source instanceof ExpressionStackHandler) {
            showEnterSymbol((ExpressionStackHandler) source);
        }
    }
}
