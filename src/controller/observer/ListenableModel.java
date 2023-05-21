package controller.observer;

/**
 * Interface that define a listenable class.
 */
public interface ListenableModel {
    /**
     * Add a listener to the class.
     * @param l Class that is a listener.
     */
    void addModelListener(ModelListener l);

    /**
     * Remove a listener from the class.
     * @param l Class that is a listener.
     */
    void removeModelListener(ModelListener l);
}