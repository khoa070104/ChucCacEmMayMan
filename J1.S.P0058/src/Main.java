import java.util.Scanner;

/**
 * Entry point for the Simple English-Vietnamese Dictionary (J1.S.P0058).
 *
 * @author NCPC
 */
public class Main {

    // Dictionary object handles HashMap and file operations
    private final Dictionary dictionary = new Dictionary();
    // Scanner reads input from the keyboard
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Program entry point.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // Create the program object and start the menu loop
        new Main().run();
    }

    /**
     * Displays the main menu and handles user choices until Exit is selected.
     */
    public void run() {
        // Loop until the user chooses option 4 (Exit)
        while (true) {
            // Print the main menu title and options
            System.out.println("========= Dictionary program =========");
            System.out.println("1. Add Word");
            System.out.println("2. Delete Word");
            System.out.println("3. Translate");
            System.out.println("4. Exit");
            // Ask the user to enter a menu option
            System.out.print("Enter your choice: ");
            // Read the selected option as text
            String choice = scanner.nextLine().trim();

            // Reject invalid menu choices and show the menu again
            if (!Validation.isValidMenuChoice(choice)) {
                continue;
            }

            // Handle each valid menu option
            switch (choice) {
                case "1":
                    // Call the add-word screen
                    handleAddWord();
                    break;
                case "2":
                    // Call the delete-word screen
                    handleDeleteWord();
                    break;
                case "3":
                    // Call the translate screen
                    handleTranslate();
                    break;
                case "4":
                    // Stop the program
                    return;
                default:
                    break;
            }
        }
    }

    /**
     * Reads English and Vietnamese words, then adds or updates the dictionary.
     */
    private void handleAddWord() {
        // Print the add-word section title
        System.out.println("----------- Add -----------");
        // Ask for the English word
        System.out.print("Enter English: ");
        // Read the English word from input
        String english = scanner.nextLine().trim();
        // Validate that English input is not empty
        if (!Validation.isNotEmpty(english)) {
            return;
        }

        // Ask for the Vietnamese meaning
        System.out.print("Enter Vietnamese: ");
        // Read the Vietnamese meaning from input
        String vietnamese = scanner.nextLine().trim();
        // Validate that Vietnamese input is not empty
        if (!Validation.isNotEmpty(vietnamese)) {
            return;
        }

        // Check whether the English word already exists in the dictionary
        if (dictionary.containsWord(english)) {
            // Ask the user whether to update the existing meaning
            System.out.print("Word already exists. Update meaning? (Y/N): ");
            // Read the update confirmation answer
            String answer = scanner.nextLine().trim();
            // Stop without changing data when the user refuses to update
            if (!Validation.isUpdateConfirmed(answer)) {
                return;
            }
        }

        // Add or update the word pair and save to file
        if (dictionary.addWord(english, vietnamese)) {
            // Inform the user that the operation succeeded
            System.out.println("Successful");
        }
    }

    /**
     * Reads an English word and removes it from the dictionary.
     */
    private void handleDeleteWord() {
        // Print the delete-word section title
        System.out.println("----------- Delete -----------");
        // Ask for the English word to delete
        System.out.print("Enter English: ");
        // Read the English word from input
        String english = scanner.nextLine().trim();
        // Validate that English input is not empty
        if (!Validation.isNotEmpty(english)) {
            return;
        }

        // Try to remove the word and check the result
        if (dictionary.removeWord(english)) {
            // Inform the user that deletion succeeded
            System.out.println("Successful");
        } else {
            // Inform the user that the key was not found in the database
            System.out.println("The key does not exist in the database.");
        }
    }

    /**
     * Reads an English word and displays its Vietnamese translation.
     */
    private void handleTranslate() {
        // Print the translate section title
        System.out.println("----------- Translate -----------");
        // Ask for the English word to look up
        System.out.print("Enter English: ");
        // Read the English word from input
        String english = scanner.nextLine().trim();
        // Validate that English input is not empty
        if (!Validation.isNotEmpty(english)) {
            return;
        }

        // Find the Vietnamese meaning in the dictionary
        String vietnamese = dictionary.translate(english);
        // Show the translation or an empty result when not found
        if (vietnamese == null) {
            // Display empty output when the word is not in the dictionary
            System.out.println("Vietnamese: ");
        } else {
            // Display the Vietnamese meaning when the word is found
            System.out.println("Vietnamese: " + vietnamese);
        }
    }
}
