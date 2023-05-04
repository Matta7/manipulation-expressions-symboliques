package controller.facade;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public interface IExpedidFacade {
    // Method that interpret the command that the user entered using expedid editor
    void enter(String command);

    // !type
    String type();

    // !type [symbol]
    void type(String symbol);

    // !save filename
    void save(String fileName) throws ParserConfigurationException, TransformerException;

    // !load filename
    void load(String fileName);
}
