package client;

import commands.CommandSetController;
import exceptions.InvalidParameterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.CollectionController;
import server.UDPServer;
import utils.GenericsToTypeConverter;
import utils.SendingWrapper;
import utils.ConverterBytes;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Scanner;

public class UDPClient {
    final private static int SERVER_PORT = 8000;
    final private static String HOST = "localhost";
    private static final Logger logger = LoggerFactory.getLogger(UDPClient.class);

    public static void main(String[] args) {

        logger.info("hi");
        CommandSetController commandSetController = CommandSetController.getInstance();
        try (DatagramSocket clientSocket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName(HOST);
            byte[] sendingDataBuffer;

            byte[] receivingDataBuffer = new byte[1024];
            Scanner scanner = new Scanner(System.in);
            GenericsToTypeConverter genericsToTypeConverter = new GenericsToTypeConverter(CommandSetController.getInstance(), CollectionController.getInstance());
            while (scanner.hasNext()) {
                String lineCommand = scanner.nextLine().trim();
                try {
                    SendingWrapper sendingWrapper = GenericsToTypeConverter.getSendingWrapper(lineCommand);
                    sendingDataBuffer = ConverterBytes.convertObjectToBytes(sendingWrapper);
                    DatagramPacket outputPacket = new DatagramPacket(sendingDataBuffer, sendingDataBuffer.length, serverAddress, SERVER_PORT);

                    clientSocket.send(outputPacket);

                    DatagramPacket receivingPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);
                    clientSocket.receive(receivingPacket);
                    String receivedData = new String(receivingPacket.getData(), receivingPacket.getOffset(), receivingPacket.getLength());
                    System.out.println(receivedData);
                } catch (IllegalArgumentException | InvalidParameterException e) {
                    System.out.println(e.getMessage()); //own exceptions with special output text
                }
            }
        } catch (IOException  e) {
            logger.warn(e.getMessage());
        }
    }
}
