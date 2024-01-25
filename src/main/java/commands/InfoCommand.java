package commands;

import java.io.Serializable;

public class InfoCommand implements Command, Serializable {
    private final CommandSetController commandSetController;
    private final CommandName name = CommandName.INFO;

    public CommandName getName() {
        return name;
    }

    public InfoCommand(CommandSetController commandSetController) {
        this.commandSetController = commandSetController;
    }

    public String getDescription() {
        return "Show all commands";
    }

    public void execute(){
        for (String key: this.commandSetController.getCommandSet().keySet()) {
            Command command = this.commandSetController.getCommandSet().get(key);
            System.out.println(key + ": " + command.getDescription());
        }
    }
}
