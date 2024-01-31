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
