package commands;

public interface Command {
    default String getDescription() {
        return "";
    }

    default void execute() {
    }

    default CommandName getName() {
        return CommandName.NONE;
    }


}
