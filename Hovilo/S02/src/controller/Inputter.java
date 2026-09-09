package controller;

import java.util.Scanner;

/**
 * Reads and validates console input.
 *
 * @author Ho Vi Lo
 * @since 09/09/2026
 */
public class Inputter {
    private final Scanner scanner;

    /**
     * Creates an input helper for standard input.
     */
    public Inputter() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads a non-empty line.
     *
     * @param message prompt displayed to the user
     * @return entered non-empty text
     */
    public String readRequired(String message) {
        while (true) {
            System.out.print(message);
            String value = scanner.nextLine();
            if (!value.trim().isEmpty()) {
                return value;
            }
            System.out.println("String must not be empty.");
        }
    }

    /**
     * Waits for Enter or detects the Escape character followed by Enter.
     *
     * @return true to continue, false to exit
     */
    public boolean wantToContinue() {
        System.out.println("Press Enter to continue another reverse, ESC to exit.");
        return !scanner.nextLine().contains("\u001B");
    }
}
