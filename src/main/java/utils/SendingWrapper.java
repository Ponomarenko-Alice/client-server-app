package utils;

import commands.Command;

import java.io.Serializable;


public  class SendingWrapper implements Serializable {
    private  Command command;
    private  String[] params;

    public SendingWrapper(Command command, String[] params) {
        this.command = command;
        this.params = params;
    }

    public String[] getParams() {
        return params;
    }

    public void setParams(String[] params) {
        this.params = params;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }


}
