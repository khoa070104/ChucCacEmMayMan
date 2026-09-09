package model;

/** Counts English letters in text. @author Ho Vi Lo @since 09/09/2026 */
public class LetterCounter {
    /**
     * Counts letters without distinguishing uppercase from lowercase.
     * Algorithm: convert each character to lowercase and use character - 'a'
     * as its position in a 26-element array.
     * @param text input text @return array of counts from a through z
     * @throws IllegalArgumentException if text is null
     */
    public int[] count(String text) {
        if (text == null) throw new IllegalArgumentException("Text must not be null.");
        int[] counts = new int[26];
        for (char character : text.toCharArray()) {
            char lower = Character.toLowerCase(character);
            if (lower >= 'a' && lower <= 'z') counts[lower - 'a']++;
        }
        return counts;
    }
}
