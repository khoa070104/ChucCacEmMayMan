/**
 * Provides input validation helpers for the dictionary program.
 *
 * @author NCPC
 */
public class Validation {

    /**
     * Checks whether a menu choice is valid (1 to 4).
     *
     * @param choice user input for menu option
     * @return true if the choice is between 1 and 4
     */
    public static boolean isValidMenuChoice(String choice) {
        // Return true only for options 1, 2, 3, or 4
        return choice.equals("1") || choice.equals("2")
                || choice.equals("3") || choice.equals("4");
    }

    /**
     * Checks whether a word input is not empty.
     *
     * @param word text entered by the user
     * @return true if the word is not null and not blank
     */
    public static boolean isNotEmpty(String word) {
        // Return false when input is null or contains only spaces
        return word != null && !word.trim().isEmpty();
    }

    /**
     * Checks whether the user confirmed an update with Y or y.
     *
     * @param answer user confirmation input
     * @return true when the answer is Y or y
     */
    public static boolean isUpdateConfirmed(String answer) {
        // Return true only when the user types Y or y
        return answer != null && answer.trim().equalsIgnoreCase("Y");
    }
}
