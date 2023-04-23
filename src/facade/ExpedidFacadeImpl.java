package facade;

public class ExpedidFacadeImpl implements IExpedidFacade {

    private static ExpedidFacadeImpl expedidFacade = new ExpedidFacadeImpl();



    private ExpedidFacadeImpl() {}

    public static ExpedidFacadeImpl getInstance() {
        return expedidFacade;
    }

    @Override
    public String enter(String command) {
        if (command == "!quit") {
            return quit();
        } else if (command.startsWith("!type")) {
            return null;
        }

        return null;
    }

    @Override
    public String quit() {
        return null;
    }

    @Override
    public String type() {
        return null;
    }

    @Override
    public String type(String symbol) {
        return null;
    }

    @Override
    public String save(String fileName) {
        return null;
    }

    @Override
    public String load(String fileName) {
        return null;
    }
}
