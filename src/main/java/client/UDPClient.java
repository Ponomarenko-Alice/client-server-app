package client;

import commands.CommandName;
import exceptions.InvalidCommandException;
import exceptions.InvalidParameterException;
import server.CollectionController;
import commands.CommandSetController;
import utils.Box;
import utils.ConverterBytes;
import utils.GenericsToTypeConverter;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Scanner;

public class UDPClient {

    final private static int SERVER_PORT = 8000;

    public static void main(String[] args) {
        try (DatagramSocket clientSocket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName("localhost");
            byte[] sendingDataBuffer;
            byte[] receivingDataBuffer = new byte[1024];
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String lineCommand = scanner.nextLine().trim();
                try {
                    Box<?> box = getBox(lineCommand);
                    sendingDataBuffer = ConverterBytes.convertObjectToBytes(box);
                    DatagramPacket outputPacket = new DatagramPacket(sendingDataBuffer, sendingDataBuffer.length, serverAddress, SERVER_PORT);

                    clientSocket.send(outputPacket);

                    DatagramPacket receivingPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);
                    clientSocket.receive(receivingPacket);

                    String receivedData = new String(receivingPacket.getData(), receivingPacket.getOffset(), receivingPacket.getLength());
                    System.out.println(receivedData);
                } catch (InvalidCommandException | InvalidParameterException e) {
                    System.out.println(e.getMessage());
                }


            }
        } catch (IOException  e) {
            System.out.println(e.getMessage());
        }

    }

    private static Box<?> getBox(String lineCommand) throws InvalidCommandException, InvalidParameterException {
        String[] lineInputs = lineCommand.split(" ");
        // TODO: process IllegalArgumentException
        CommandName commandName = CommandName.valueOf(lineInputs[0].toUpperCase());
        String[] parameters = Arrays.copyOfRange(lineInputs, 1, lineInputs.length);
        GenericsToTypeConverter converter = new GenericsToTypeConverter();
        try {
            return converter.getSendingItem(CommandSetController.getInstance(), CollectionController.getInstance(), commandName, parameters);
        } catch (InvalidCommandException | InvalidParameterException e) {
            throw e;

        }

    }
}
