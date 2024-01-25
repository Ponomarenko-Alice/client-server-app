package utils;

import commands.*;
import exceptions.InvalidCommandException;
import exceptions.InvalidParameterException;
import server.CollectionController;
import utils.Box;

import javax.lang.model.type.NullType;
import java.util.Map;


public class GenericsToTypeConverter {

    private String commandName;
    private String parameter;


    // add (Product)
    // String
    // id - Integer


    public Box<?> getSendingItem(CommandSetController commandSetController,
                                 CollectionController collectionController,
                                 CommandName commandName,
                                 String... parameter) throws InvalidCommandException, InvalidParameterException {
        Map<String, Command> map = CommandSetController.getInstance().getCommandSet();

        switch (commandName) {
            case REMOVE_BY_ID: //id
                RemoveById removeById = new RemoveById(collectionController, parameter);
                if (parameter.length != 0 ) {
                    return new Box<>(removeById, Integer.parseInt(parameter[0]));
                } else {
                    throw new InvalidParameterException("Remove_by_id command required 1 parameter");
                }

            case INFO: //no parameter
                InfoCommand infoCommand = new InfoCommand(commandSetController);
                return new Box<NullType>(infoCommand, null);

            default:
                throw new InvalidCommandException("No such command found");
        }
    }
}

