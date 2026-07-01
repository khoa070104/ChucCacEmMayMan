import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * Manages the English-Vietnamese dictionary using a HashMap and file storage.
 *
 * @author NCPC
 */
public class Dictionary {

    // Path to the dictionary data file
    private static final String DATA_FILE = "src/dictionary.txt";
    // HashMap stores English words as keys and Vietnamese meanings as values
    private final HashMap<String, String> dictionary = new HashMap<>();

    /**
     * Creates a dictionary and loads existing data from file.
     */
    public Dictionary() {
        // Load saved word pairs when the object is created
        loadData();
    }

    /**
     * Checks whether an English word already exists in the dictionary.
     *
     * @param eng English word to check
     * @return true if the word exists, false otherwise
     */
    public boolean containsWord(String eng) {
        // Return true when the English key is found in the HashMap
        return dictionary.containsKey(eng);
    }

    /**
     * Loads dictionary data from file into the HashMap.
     */
    public void loadData() {
        // Create a File object pointing to the data file
        File file = new File(DATA_FILE);
        // If the file does not exist yet, keep an empty HashMap
        if (!file.exists()) {
            return;
        }

        // Use try-with-resources to read the file safely
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            // Read one line at a time until the end of file
            String line;
            while ((line = reader.readLine()) != null) {
                // Split each line into English and Vietnamese parts
                String[] parts = line.split("\\|", 2);
                // Skip invalid lines that do not contain two parts
                if (parts.length < 2) {
                    continue;
                }
                // Put the word pair into the HashMap
                dictionary.put(parts[0].trim(), parts[1].trim());
            }
        } catch (IOException exception) {
            // Print an error message if reading fails
            System.out.println("Cannot load data file.");
        }
    }

    /**
     * Writes all entries from the HashMap back to the data file.
     */
    public void updateDatabase() {
        // Use try-with-resources to write the file safely
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            // Loop through every key (English word) in the HashMap
            for (String english : dictionary.keySet()) {
                // Write one line in the format: English|Vietnamese
                writer.write(english + "|" + dictionary.get(english));
                // Move to the next line in the file
                writer.newLine();
            }
        } catch (IOException exception) {
            // Print an error message if writing fails
            System.out.println("Cannot update data file.");
        }
    }

    /**
     * Adds a new English-Vietnamese pair to the dictionary.
     *
     * @param eng English word
     * @param vi  Vietnamese meaning
     * @return true when the word is stored successfully
     */
    public boolean addWord(String eng, String vi) {
        // Store the English word and its Vietnamese meaning in the HashMap
        dictionary.put(eng, vi);
        // Save the updated dictionary to the file on disk
        updateDatabase();
        // Return success status
        return true;
    }

    /**
     * Removes an English word and its Vietnamese meaning from the dictionary.
     *
     * @param eng English word to remove
     * @return true if the word existed and was removed, false otherwise
     */
    public boolean removeWord(String eng) {
        // Check whether the English word exists in the dictionary
        if (!dictionary.containsKey(eng)) {
            // Return false when the key is not found
            return false;
        }
        // Remove the word pair from the HashMap
        dictionary.remove(eng);
        // Save the updated dictionary to the file on disk
        updateDatabase();
        // Return true to indicate successful deletion
        return true;
    }

    /**
     * Looks up the Vietnamese meaning of an English word.
     *
     * @param eng English word to search
     * @return Vietnamese meaning, or null if the word is not found
     */
    public String translate(String eng) {
        // Return the Vietnamese value linked to the English key
        return dictionary.get(eng);
    }
}
