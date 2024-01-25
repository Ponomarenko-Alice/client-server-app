package utils;

import java.io.*;

public class ConverterBytes {
    public static byte[] convertObjectToBytes(Object obj) {

        ByteArrayOutputStream boas = new ByteArrayOutputStream();
        try (ObjectOutputStream ois = new ObjectOutputStream(boas)) {
            ois.writeObject(obj);
            return boas.toByteArray();
        } catch (IOException e) {
            System.out.println("Error in conversion to bytes");
        }

        return new byte[0];
    }

    public static Object convertBytesToObject(byte[] bytes) {
        InputStream is = new ByteArrayInputStream(bytes);
        try (ObjectInputStream ois = new ObjectInputStream(is)) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException ioe) {
            System.out.println("Error in conversion to objects");
        }
        return null;
    }

}
