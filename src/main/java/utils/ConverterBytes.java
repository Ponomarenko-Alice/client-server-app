package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;


public class ConverterBytes {
    private static final Logger logger = LoggerFactory.getLogger(ConverterBytes.class);
    public static byte[] convertObjectToBytes(Object obj) {
        ByteArrayOutputStream boas = new ByteArrayOutputStream();
        try (ObjectOutputStream ois = new ObjectOutputStream(boas)) {
            ois.writeObject(obj);
            return boas.toByteArray();
        } catch (IOException e) {
            logger.error("Error in conversion to bytes: \n" + e.getCause());
            System.out.println("Something went wrong. Try again");
        }
        return new byte[0];
    }

    public static Object convertBytesToObject(byte[] bytes) throws IOException, ClassNotFoundException {
        InputStream is = new ByteArrayInputStream(bytes);
        try (ObjectInputStream ois = new ObjectInputStream(is)) {
            return ois.readObject();
        } catch (IOException e) {
            throw new IOException("Error in conversion to objects. \n" + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("Error in conversion to objects. \n " + e.getMessage());
        }
    }

}
