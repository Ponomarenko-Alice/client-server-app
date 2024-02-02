package utils;

import commands.*;
import exceptions.InvalidParameterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.CollectionController;

import javax.lang.model.type.NullType;
import java.util.Arrays;


public class GenericsToTypeConverter {
    private final static Logger logger = LoggerFactory.getLogger(GenericsToTypeConverter.class);
    private static CommandSetController commandSetController;
    private static CollectionController collectionController;


    public GenericsToTypeConverter(CommandSetController commandSetController,
                                   CollectionController collectionController) {
        GenericsToTypeConverter.commandSetController = commandSetController;
        GenericsToTypeConverter.collectionController = collectionController;
    }

    public static SendingWrapper getSendingWrapper(String lineCommand) throws IllegalArgumentException, InvalidParameterException {
        String[] lineInputs = lineCommand.split(" ");
        try {
            CommandName commandName = CommandName.valueOf(lineInputs[0].toUpperCase());
            String[] parameters = Arrays.copyOfRange(lineInputs, 1, lineInputs.length);
            return getSendingItem(commandName, parameters);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Not found required command");
        } catch (InvalidParameterException e) {
            throw new InvalidParameterException(e.getMessage());
        }
    }

    private static SendingWrapper getSendingItem(CommandName commandName, String... parameter)
            throws InvalidParameterException {
        switch (commandName) {
            case REMOVE_BY_ID: //id
                RemoveById removeById = new RemoveById(collectionController, parameter);
                if (parameter.length != 0) {
                    return new SendingWrapper(removeById, parameter);
                } else {
                    throw new InvalidParameterException("Remove_by_id command required 1 parameter");
                }

            case INFO: //no parameter
                InfoCommand infoCommand = new InfoCommand(commandSetController);
                return new SendingWrapper(infoCommand, null);
        }
        return null;
    }
}

