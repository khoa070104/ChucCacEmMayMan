package controller;

import common.Constants;
import common.Messages;
import model.Shoe;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShoeList {
    private final List<Shoe> shoes;
    private final String filename;
    private boolean changed;

    public ShoeList() {
        shoes = new ArrayList<>();
        filename = Constants.SHOE_FILE;
        loadFromFile();
    }

    public List<Shoe> getAll() {
        return new ArrayList<>(shoes);
    }

    public Shoe findById(String id) {
        for (Shoe shoe : shoes) {
            if (shoe.getId().equalsIgnoreCase(id)) {
                return shoe;
            }
        }
        return null;
    }

    public void add(Shoe shoe) {
        if (findById(shoe.getId()) != null) {
            throw new IllegalArgumentException(Messages.ERR_DUPLICATE_ID);
        }
        shoes.add(shoe);
        changed = true;
    }

    public boolean remove(String id) {
        Shoe shoe = findById(id);
        if (shoe == null) {
            return false;
        }
        shoes.remove(shoe);
        changed = true;
        return true;
    }

    public List<Shoe> findByMaximumPrice(double maximumPrice) {
        List<Shoe> result = new ArrayList<>();
        for (Shoe shoe : shoes) {
            if (shoe.getPrice() <= maximumPrice) {
                result.add(shoe);
            }
        }
        return result;
    }

    public List<Shoe> findByMinimumQuantity(int minimumQuantity) {
        List<Shoe> result = new ArrayList<>();
        for (Shoe shoe : shoes) {
            if (shoe.getQuantity() > minimumQuantity) {
                result.add(shoe);
            }
        }
        return result;
    }

    public void markChanged() {
        changed = true;
    }

    public boolean isChanged() {
        return changed;
    }

    public void loadFromFile() {
        File file = new File(filename);
        if (!file.exists()) {
            return;
        }
        shoes.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",", -1);
                if (fields.length != 5) {
                    continue;
                }
                try {
                    String id = fields[0].trim();
                    String name = fields[1].trim();
                    String brand = fields[2].trim();
                    double price = Double.parseDouble(fields[3].trim());
                    int quantity = Integer.parseInt(fields[4].trim());
                    if (id.matches(Constants.SHOE_ID_REGEX) && !name.isEmpty()
                            && !brand.isEmpty() && price > 0 && quantity >= 0
                            && findById(id) == null) {
                        shoes.add(new Shoe(id, name, brand, price, quantity));
                    }
                } catch (NumberFormatException ignored) {
                    // Ignore malformed records and continue loading valid data.
                }
            }
            changed = false;
        } catch (IOException e) {
            System.out.println(Messages.ERR_FILE_READ);
        }
    }

    public boolean saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Shoe shoe : shoes) {
                writer.write(shoe.toFileString());
                writer.newLine();
            }
            changed = false;
            return true;
        } catch (IOException e) {
            System.out.println(Messages.ERR_FILE_WRITE);
            return false;
        }
    }
}
