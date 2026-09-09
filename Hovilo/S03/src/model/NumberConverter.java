package model;

/**
 * Converts a numeral in base 2, 8, or 16 to decimal.
 *
 * @author Ho Vi Lo
 * @since 09/09/2026
 */
public class NumberConverter {
    /**
     * Converts text to a decimal long without using a library conversion.
     * Algorithm: scan from left to right and update result = result * base + digit.
     *
     * @param number validated numeral text
     * @param base source base
     * @return decimal value
     * @throws IllegalArgumentException if a character is invalid for the base
     * @throws ArithmeticException if the result exceeds the long range
     */
    public long toDecimal(String number, int base) {
        long result = 0;
        for (char character : number.toUpperCase().toCharArray()) {
            int digit = Character.digit(character, base);
            if (digit < 0) throw new IllegalArgumentException("Invalid digit.");
            result = Math.addExact(Math.multiplyExact(result, base), digit);
        }
        return result;
    }
}
