package controller;

import java.util.Scanner;

/**
 * Reads validated console input for S03.
 *
 * @author Ho Vi Lo
 * @since 09/09/2026
 */
public class Inputter {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Reads a menu choice from 1 through 4.
     *
     * @param message prompt to display
     * @return valid menu choice
     */
    public int readChoice(String message) {
        while (true) {
            System.out.print(message);
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= 1 && choice <= 4) return choice;
            } catch (NumberFormatException exception) {
                // The common error message below is enough for beginners.
            }
            System.out.println("Please enter an integer from 1 to 4.");
        }
    }

    /**
     * Reads a number valid for the given base.
     *
     * @param message prompt to display
     * @param base numeral base, which must be 2, 8, or 16
     * @return validated number text
     * @throws IllegalArgumentException if the base is unsupported
     */
    public String readNumber(String message, int base) {
        String pattern;
        if (base == 2) pattern = "[01]+";
        else if (base == 8) pattern = "[0-7]+";
        else if (base == 16) pattern = "[0-9a-fA-F]+";
        else throw new IllegalArgumentException("Unsupported numeral base.");
        while (true) {
            System.out.print(message);
            String value = scanner.nextLine().trim();
            if (value.matches(pattern)) return value;
            System.out.println("The number is not valid in base " + base + ".");
        }
    }
}
