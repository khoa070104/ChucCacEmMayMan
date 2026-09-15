package data;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public final class BinaryStore {
    private BinaryStore() { }

    public static <T extends Serializable> void save(String fileName, Map<String, T> data)
            throws IOException {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(fileName))) {
            output.writeObject(new LinkedHashMap<>(data));
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> LinkedHashMap<String, T> load(String fileName)
            throws IOException, ClassNotFoundException {
        File file = new File(fileName);
        if (!file.exists() || file.length() == 0) return new LinkedHashMap<>();
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(file))) {
            return (LinkedHashMap<String, T>) input.readObject();
        }
    }
}
