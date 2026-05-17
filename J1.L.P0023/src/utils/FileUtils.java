package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import model.Fruit;

/**
 * File read/write helpers for fruit persistence.
 */
public class FileUtils {

    private FileUtils() {
    }

    public static void ensureParentDir(String filePath) throws IOException {
        File file = new File(filePath);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public static void saveFruit(String filePath, Fruit fruit) throws IOException {
        ensureParentDir(filePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(serialize(fruit));
            writer.newLine();
        }
    }

    public static String serialize(Fruit fruit) {
        return fruit.getFruitId() + "|"
                + fruit.getFruitName() + "|"
                + fruit.getPrice() + "|"
                + fruit.getQuantity() + "|"
                + fruit.getOrigin();
    }

    public static Fruit deserialize(String line) {
        if (line == null || line.trim().isEmpty()) {
            return null;
        }
        String[] parts = line.split("\\|", -1);
        if (parts.length < 5) {
            return null;
        }
        return new Fruit(parts[0], parts[1],
                Double.parseDouble(parts[2]),
                Integer.parseInt(parts[3]),
                parts[4]);
    }

    public static void loadFruits(String filePath, ArrayList<Fruit> target) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Fruit fruit = deserialize(line);
                if (fruit != null) {
                    target.add(fruit);
                }
            }
        }
    }
}
