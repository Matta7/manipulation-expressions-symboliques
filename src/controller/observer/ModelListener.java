package controller.observer;

/**
 * Class that define a listener.
 */
public interface ModelListener {

    /**
     * Method called when hasChanged() from AbstractListenableModel is called.
     * @param source Object that has called the function.
     */
    void somethingHasChanged(Object source);
}