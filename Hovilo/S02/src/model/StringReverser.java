package model;

/**
 * Reverses the order of words while preserving every character in each word.
 *
 * @author Ho Vi Lo
 * @since 09/09/2026
 */
public class StringReverser {
    /**
     * Reverses words separated by spaces or underscores. Separators are kept
     * between the reversed words in their original left-to-right order.
     * Algorithm: collect words, collect separators, then join the words from
     * right to left with the separators from left to right.
     *
     * @param text original text
     * @return text whose word order is reversed
     * @throws IllegalArgumentException if text is null or blank
     */
    public String reverse(String text) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("String must not be empty.");
        }
        String[] words = text.split("[ _]+");
        String[] separators = text.split("[^ _]+");
        StringBuilder result = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            result.append(words[i]);
            int separatorIndex = words.length - 1 - i;
            if (i > 0) {
                String separator =
                        separatorIndex + 1 < separators.length
                                ? separators[separatorIndex + 1]
                                : " ";
                result.append(separator);
            }
        }
        return result.toString();
    }
}
