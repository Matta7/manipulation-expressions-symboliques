package controller.observer;

import java.util.*;

/**
 * Abstract class that define a listenable class
 */
public abstract class AbstractListenableModel implements ListenableModel {

    /**
     * List of listeners that listen this.
     */
    private List<ModelListener> listeners;

    public AbstractListenableModel() {
        listeners = new ArrayList<>();
    }

    @Override
    public void addModelListener(ModelListener l) {
        listeners.add(l);
    }

    @Override
    public void removeModelListener(ModelListener l) {
        listeners.remove(l);
    }

    /**
     * Method to call when something has changed, notify all the listeners.
     */
    protected void hasChanged() {
        for (ModelListener l : listeners)
            l.somethingHasChanged(this);
    }
}