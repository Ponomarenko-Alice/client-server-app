package utils;

import commands.Command;
import commands.CommandName;
import commands.CommandSetController;
import exceptions.InvalidParameterException;
import server.CollectionController;

import java.io.Serializable;
import java.util.Arrays;


public  class SendingWrapper<T> implements Serializable {
    private  Command command;
    private  T parameter;

    public SendingWrapper(Command command, T parameter) {
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
