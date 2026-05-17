package repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Fruit;
import utils.FileUtils;

/**
 * Data access layer – stores fruits in ArrayList and file.
 */
public class FruitRepository {

    private static final String DATA_FILE = "data/fruits.txt";
    private final ArrayList<Fruit> fruits = new ArrayList<>();

    public FruitRepository() {
        try {
            FileUtils.loadFruits(DATA_FILE, fruits);
        } catch (IOException e) {
            System.out.println("Could not load fruits file: " + e.getMessage());
        }
    }

    public void add(Fruit fruit) throws IOException {
        fruits.add(fruit);
        FileUtils.saveFruit(DATA_FILE, fruit);
    }

    public List<Fruit> findAll() {
        return new ArrayList<>(fruits);
    }

    public Fruit findByIndex(int index) {
        if (index < 0 || index >= fruits.size()) {
            return null;
        }
        return fruits.get(index);
    }

    public boolean isEmpty() {
        return fruits.isEmpty();
    }

    public int size() {
        return fruits.size();
    }

    public boolean existsById(String fruitId) {
        for (Fruit fruit : fruits) {
            if (fruit.getFruitId().equalsIgnoreCase(fruitId)) {
                return true;
            }
        }
        return false;
    }
}
