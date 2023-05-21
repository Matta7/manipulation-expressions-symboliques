package controller.facade;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public interface IExpedidFacade {
    /**
     * Method that interpret the command that the user entered using expedid editor.
     * @param command to interpret
     */
    void enter(String command);

    /**
     * !type command, return information on type.
     * @return information on type.
     */
    String type();

    /**
     * !type [symbol] command, change the type of the stack.
     * @param symbol type to apply.
     */
    void type(String symbol);

    /**
     * !save filename command, save a file with the name with filename as name.
     * @param fileName File name to save.
     */
    void save(String fileName) throws ParserConfigurationException, TransformerException;

    /**
     * !load filename command, load a file.
     * @param fileName File name to load.
     */
    void load(String fileName);
}
