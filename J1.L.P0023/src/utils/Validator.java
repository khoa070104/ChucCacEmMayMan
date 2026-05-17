package utils;

/**
 * Validation utilities for fruit shop data.
 */
public class Validator {

    private Validator() {
    }

    public static boolean isYesNo(String input) {
        return input != null && input.trim().matches("[YyNn]");
    }

    public static boolean isContinue(String input) {
        return input != null && input.trim().equalsIgnoreCase("Y");
    }

    public static boolean isPositiveDouble(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }
        try {
            return Double.parseDouble(input.trim()) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isPositiveInteger(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }
        try {
            return Integer.parseInt(input.trim()) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidMenuChoice(String input, int min, int max) {
        if (input == null || !input.matches("\\d+")) {
            return false;
        }
        int choice = Integer.parseInt(input);
        return choice >= min && choice <= max;
    }
}
