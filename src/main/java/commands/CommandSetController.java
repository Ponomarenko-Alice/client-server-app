package commands;

import server.CollectionController;

import java.io.Serializable;
import java.util.HashMap;

public final class CommandSetController implements Serializable {


    private static CommandSetController INSTANCE;

    private CommandSetController() {
    }

    public static CommandSetController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CommandSetController();
        }
        return INSTANCE;
    }

    private final HashMap<String, Command> commandSet = new HashMap<>();

    public CommandSetController(CollectionController collectionController) {
        commandSet.put("info", new InfoCommand(this));
        commandSet.put("remove_by_id", new RemoveById(collectionController));

    }

    public HashMap<String, Command> getCommandSet() {
        return this.commandSet;
    }
}
