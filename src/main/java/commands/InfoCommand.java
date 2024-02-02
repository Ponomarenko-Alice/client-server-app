package commands;

import java.io.Serializable;

public class InfoCommand implements Command, Serializable {
    private final CommandSetController commandSetController;
    private final CommandName name = CommandName.INFO;
    private String[] params;

    public CommandName getName() {
        return name;
    }

    public void setParams(String[] params) {
        this.params = params;
    }

    public InfoCommand(CommandSetController commandSetController) {
        this.commandSetController = commandSetController;
    }

    public String getDescription() {
        return "Show all commands";
    }

    public String execute(){
        String answer = "";
        for (String key: this.commandSetController.getCommandSet().keySet()) {
            Command command = this.commandSetController.getCommandSet().get(key);
            answer += key + ": " + command.getDescription() +"\n";
        }
        return answer;
    }
}
