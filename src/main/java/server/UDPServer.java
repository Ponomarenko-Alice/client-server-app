package server;

import commands.Command;
import commands.CommandName;
import org.slf4j.LoggerFactory;
import utils.SendingWrapper;
import utils.ConverterBytes;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import org.slf4j.Logger;

public class UDPServer {
    //TODO: delete general exceptions from class
    private static final Logger logger = LoggerFactory.getLogger(UDPServer.class);
    public static void main(String[] args) {
        logger.info("server is running");
        try (DatagramSocket serverSocket = new DatagramSocket(8000)) {
            int x = 0;
            while (x < 100) {
                byte[] receivingDataBuffer = new byte[1024];
                byte[] sendingDataBuffer;

                DatagramPacket inputPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);
                serverSocket.receive(inputPacket);

                receivingDataBuffer = inputPacket.getData();
                logger.info("data from datagramPacket received");
                SendingWrapper recievedSendingWrapper = (SendingWrapper) ConverterBytes.convertBytesToObject(receivingDataBuffer);
                CommandName commandName = recievedSendingWrapper.getCommand().getName();
                Command command = recievedSendingWrapper.getCommand();
                command.setParams(recievedSendingWrapper.getParams());

                logger.info(commandName.getCommandName() + "is received");

                try {
                    sendingDataBuffer = command.execute().getBytes();
                    InetAddress address = inputPacket.getAddress();
                    int senderPort = inputPacket.getPort();

                    DatagramPacket outputPacket = new DatagramPacket(sendingDataBuffer,
                            sendingDataBuffer.length, address, senderPort);
                    serverSocket.send(outputPacket);
                } catch (NumberFormatException e) {
                    logger.warn(e.getMessage());
                }
                x++;
            }
        } catch (IOException e) {
            logger.warn("IO server error:" + e.getMessage());
        } catch (ClassNotFoundException e) {
            logger.warn(e.getMessage());
        }
    }
}
