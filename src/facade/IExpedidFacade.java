package facade;

public interface IExpedidFacade {
    // Method that interpret the command that the user entered using expedid editor
    String enter(String command);

    // !quit
    String quit();

    // !type [symbol]
    String type(String symbol);

    // !save filename
    String save(String fileName);

    // !load filename
    String load(String fileName);
}
