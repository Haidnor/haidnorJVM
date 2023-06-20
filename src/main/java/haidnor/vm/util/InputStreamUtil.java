package haidnor.vm.util;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class InputStreamUtil {

    public static DataInputStream getDataInputStream(String path) {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(path);
        } catch (FileNotFoundException exception) {
            throw new Error(exception);
        }
        return new DataInputStream(fileInputStream);
    }

    public static DataInputStream getDataInputStream(byte[] bytes) {
        ByteArrayInputStream inputStream = getByteArrayInputStream(bytes);
        return new DataInputStream(inputStream);
    }

    public static ByteArrayInputStream getByteArrayInputStream(byte[] bytes) {
        return new ByteArrayInputStream(bytes);
    }

}
