package commands;

import server.CollectionController;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;

public final class CommandSetController implements Serializable {
    private static CommandSetController INSTANCE;
    private CommandSetController(CollectionController collectionController) {
        commandSet.put("info", new InfoCommand(this));
        commandSet.put("remove_by_id", new RemoveById(collectionController));
        commandSet.put(CommandName.ADD.getCommandName(), new AddCommand(collectionController));
    }

    public static CommandSetController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CommandSetController(CollectionController.getInstance());
        }
        return INSTANCE;
    }

    private final HashMap<String, Command> commandSet = new HashMap<>();

    public HashMap<String, Command> getCommandSet() {
        return this.commandSet;
    }
}
