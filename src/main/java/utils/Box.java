package utils;

import commands.Command;

import java.io.Serializable;


public class Box<T> implements Serializable {
    private Command command;
    private T parameter;

    public Box(Command command, T parameter) {
        this.command = command;
        this.parameter = parameter;
    }

    public T getParameter() {
        return parameter;
    }

    public void setParameter(T parameter) {
        this.parameter = parameter;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }
}
