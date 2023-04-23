package facade;

public interface IExpedidFacade {
    // Method that interpret the command that the user entered using expedid editor
    String enter(String command);

    // !quit
    String quit();

    // !type
    String type();

    // !type [symbol]
    void type(String symbol);

    // !save filename
    void save(String fileName);

    // !load filename
    void load(String fileName);
}
