package facade;

import java.util.Objects;

public class ExpedidFacadeImpl implements IExpedidFacade {

    private static ExpedidFacadeImpl expedidFacade = new ExpedidFacadeImpl();



    private ExpedidFacadeImpl() {}

    public static ExpedidFacadeImpl getInstance() {
        return expedidFacade;
    }

    @Override
    public String enter(String command) {
        if (Objects.equals(command, "!quit")) {
            return this.quit();

        } else if (command.startsWith("!type")) {
            String[] separetedCommand = command.split(" ");
            if(separetedCommand.length == 1) {
                return this.type();
            } else if (separetedCommand.length == 2) {
                try {
                    this.type(separetedCommand[1]);
                    return ("Switched to "+ separetedCommand[1] + " type.");
                } catch (IllegalArgumentException e) {
                    return e.getMessage();
                }
            } else {
                return "Error: Wrong number of argument.";
            }

        } else if (command.startsWith("!save")) {
            String[] separetedCommand = command.split(" ");
            if (separetedCommand.length == 2) {
                try {
                    this.save(separetedCommand[1]);
                    return "Successfully saved file.";
                } catch (IllegalArgumentException e) {
                    return e.getMessage();
                }
            } else {
                return "Error: Wrong number of argument.";
            }

        } else if (command.startsWith("!load")) {
            String[] separetedCommand = command.split(" ");
            if (separetedCommand.length == 2) {
                try {
                    this.load(separetedCommand[1]);
                    return "Successfully loaded file.";
                } catch (IllegalArgumentException e) {
                    return e.getMessage();
                }
            } else {
                return "Error: Wrong number of argument.";
            }

        } else if (command.startsWith("!")){
            return "Error: Unknown command \"" + command.split(" ")[0] + "\"";

        } else {
            // Write something at the top of the stack.
            try {
                return null;
            } catch (IllegalArgumentException e) {
                return e.getMessage();
            }
        }
    }

    @Override
    public String quit() {
        return "quit";
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
