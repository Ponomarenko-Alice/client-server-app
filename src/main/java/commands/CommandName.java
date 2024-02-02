package commands;

public enum CommandName {

    REMOVE_BY_ID("remove_by_id"),
    INFO("info"),
    ADD("add"),
    NONE("none");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
