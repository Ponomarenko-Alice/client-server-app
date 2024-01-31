package utils;

import commands.*;
import exceptions.InvalidParameterException;
import server.CollectionController;

import javax.lang.model.type.NullType;
import java.util.Arrays;


public class GenericsToTypeConverter {
    private static CommandSetController commandSetController;
    private static CollectionController collectionController;


    public GenericsToTypeConverter(CommandSetController commandSetController,
                                   CollectionController collectionController) {
        GenericsToTypeConverter.commandSetController = commandSetController;
        GenericsToTypeConverter.collectionController = collectionController;
    }

    public static SendingWrapper<?> getSendingWrapper(String lineCommand) throws IllegalArgumentException, InvalidParameterException {
        String[] lineInputs = lineCommand.split(" ");
        CommandName commandName = CommandName.valueOf(lineInputs[0].toUpperCase());
        String[] parameters = Arrays.copyOfRange(lineInputs, 1, lineInputs.length);
        try {
            return getSendingItem(commandName, parameters);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (InvalidParameterException e) {
            throw new InvalidParameterException(e.getMessage());
        }
    }

    private static SendingWrapper<?> getSendingItem(CommandName commandName, String... parameter)
            throws  InvalidParameterException, IllegalArgumentException {
        switch (commandName) {
            case REMOVE_BY_ID: //id
                RemoveById removeById = new RemoveById(collectionController, parameter);
                if (parameter.length != 0 ) {
                    return new SendingWrapper<>(removeById, Integer.parseInt(parameter[0]));
                } else {
                    throw new InvalidParameterException("Remove_by_id command required 1 parameter");
                }

            case INFO: //no parameter
                InfoCommand infoCommand = new InfoCommand(commandSetController);
                return new SendingWrapper<NullType>(infoCommand, null);

            default:
                throw new IllegalArgumentException("No such command found");
        }
    }
}

