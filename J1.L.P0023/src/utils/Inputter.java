package utils;

import java.util.Scanner;

/**
 * Console input helper.
 */
public class Inputter {

    private static final Scanner SCANNER = new Scanner(System.in);

    private Inputter() {
    }

    public static String input(String label) {
        System.out.print(label);
        return SCANNER.nextLine();
    }

    public static String inputRequired(String label) {
        String value;
        do {
            System.out.print(label);
            value = SCANNER.nextLine();
            if (value == null || value.trim().isEmpty()) {
                System.out.println("Input cannot be empty!");
            }
        } while (value == null || value.trim().isEmpty());
        return value.trim();
    }

    public static String inputYesNo(String label) {
        String value;
        do {
            value = inputRequired(label);
            if (!Validator.isYesNo(value)) {
                System.out.println("Please enter Y or N.");
            }
        } while (!Validator.isYesNo(value));
        return value.trim();
    }

    public static double inputPositivePrice(String label) {
        String value;
        do {
            value = inputRequired(label);
            if (!Validator.isPositiveDouble(value)) {
                System.out.println("Invalid price! Must be a positive number.");
            }
        } while (!Validator.isPositiveDouble(value));
        return Double.parseDouble(value);
    }

    public static int inputPositiveQuantity(String label) {
        String value;
        do {
            value = inputRequired(label);
            if (!Validator.isPositiveInteger(value)) {
                System.out.println("Invalid quantity! Must be a positive integer.");
            }
        } while (!Validator.isPositiveInteger(value));
        return Integer.parseInt(value);
    }

    public static int inputPositiveQuantity(String label, int maxQuantity) {
        String value;
        do {
            value = inputRequired(label);
            if (!Validator.isPositiveInteger(value)) {
                System.out.println("Invalid quantity! Must be a positive integer.");
                continue;
            }
            int qty = Integer.parseInt(value);
            if (qty > maxQuantity) {
                System.out.println("Not enough stock! Available: " + maxQuantity);
                value = null;
            }
        } while (value == null || !Validator.isPositiveInteger(value)
                || Integer.parseInt(value) > maxQuantity);
        return Integer.parseInt(value);
    }
}
