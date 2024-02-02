package commands;

public interface Command {
    default String getDescription() {
        return "";
    }

    default String execute() {
        return "";
    }

    default CommandName getName() {
        return CommandName.NONE;
    }

    default void setParams(String[] params) {
    }


}
