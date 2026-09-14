package controller;

import common.Messages;
import java.util.Scanner;

public final class Inputter {
    private static final Scanner SCANNER = new Scanner(System.in);

    private Inputter() {}

    public static String input(String label) {
        System.out.print(label);
        return SCANNER.nextLine().trim();
    }

    public static String inputRequired(String label) {
        while (true) {
            String value = input(label);
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println(Messages.ERR_EMPTY);
        }
    }

    public static int inputInt(String label, int minValue) {
        while (true) {
            try {
                int value = Integer.parseInt(inputRequired(label));
                if (value >= minValue) {
                    return value;
                }
                System.out.println("Value must be greater than or equal to " + minValue + "!");
            } catch (NumberFormatException e) {
                System.out.println(Messages.ERR_INVALID_NUMBER);
            }
        }
    }

    public static double inputDouble(String label, double minExclusive) {
        while (true) {
            try {
                double value = Double.parseDouble(inputRequired(label));
                if (Double.isFinite(value) && value > minExclusive) {
                    return value;
                }
                System.out.println("Value must be greater than " + minExclusive + "!");
            } catch (NumberFormatException e) {
                System.out.println(Messages.ERR_INVALID_NUMBER);
            }
        }
    }

    public static Integer inputOptionalInt(String label, int minValue) {
        while (true) {
            String input = input(label);
            if (input.isEmpty()) {
                return null;
            }
            try {
                int value = Integer.parseInt(input);
                if (value >= minValue) {
                    return value;
                }
                System.out.println("Value must be greater than or equal to " + minValue + "!");
            } catch (NumberFormatException e) {
                System.out.println(Messages.ERR_INVALID_NUMBER);
            }
        }
    }

    public static Double inputOptionalDouble(String label, double minExclusive) {
        while (true) {
            String input = input(label);
            if (input.isEmpty()) {
                return null;
            }
            try {
                double value = Double.parseDouble(input);
                if (Double.isFinite(value) && value > minExclusive) {
                    return value;
                }
                System.out.println("Value must be greater than " + minExclusive + "!");
            } catch (NumberFormatException e) {
                System.out.println(Messages.ERR_INVALID_NUMBER);
            }
        }
    }

    public static boolean inputYesNo(String label) {
        while (true) {
            String value = inputRequired(label);
            if (value.equalsIgnoreCase("Y")) {
                return true;
            }
            if (value.equalsIgnoreCase("N")) {
                return false;
            }
            System.out.println("Please enter Y or N!");
        }
    }
}
