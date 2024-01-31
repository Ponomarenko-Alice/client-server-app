package server;

import commands.CommandName;
import utils.SendingWrapper;
import utils.ConverterBytes;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class UDPServer {
    public static void main(String[] args) {
        try (DatagramSocket serverSocket = new DatagramSocket(8000)) {
            int x = 0;
            while (x < 100) {
                byte[] receivingDataBuffer = new byte[1024];
                byte[] sendingDataBuffer;

                DatagramPacket inputPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);
                serverSocket.receive(inputPacket);

                receivingDataBuffer = inputPacket.getData();
                SendingWrapper<?> recievedSendingWrapper = (SendingWrapper<?>) ConverterBytes.convertBytesToObject(receivingDataBuffer);
                CommandName commandName = recievedSendingWrapper.getCommand().getName();
                System.out.println(commandName + " is received");

                sendingDataBuffer = commandName.getCommandName().toUpperCase().getBytes();

                InetAddress address = inputPacket.getAddress();
                int senderPort = inputPacket.getPort();

                DatagramPacket outputPacket = new DatagramPacket(sendingDataBuffer,
                        sendingDataBuffer.length, address, senderPort);
                serverSocket.send(outputPacket);
                x++;

            }
        } catch (IOException e) {
            System.out.println("IO server error:");
            System.out.println(Arrays.toString(e.getStackTrace()));
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
