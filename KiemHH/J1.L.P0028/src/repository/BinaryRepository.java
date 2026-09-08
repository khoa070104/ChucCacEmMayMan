package repository;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class BinaryRepository<T> {
    private final File dataFile;

    public BinaryRepository(String fileName) {
        dataFile = new File(fileName);
    }

    public void save(List<T> records) throws IOException {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(dataFile))) {
            output.writeObject(new ArrayList<>(records));
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> load() throws IOException, ClassNotFoundException {
        if (!dataFile.exists() || dataFile.length() == 0) return new ArrayList<>();
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(dataFile))) {
            Object value = input.readObject();
            return value instanceof List<?> ? (List<T>) value : new ArrayList<>();
        } catch (EOFException exception) {
            return new ArrayList<>();
        }
    }
}
