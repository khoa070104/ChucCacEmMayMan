package controller;

import common.Constants;
import common.Messages;
import java.util.Scanner;

public final class Inputter {
    private static final Scanner SCANNER = new Scanner(System.in);

    private Inputter() {}

    public static String input(String message) {
        System.out.print(message);
        return SCANNER.nextLine().trim();
    }

    public static String required(String message) {
        while (true) {
            String value = input(message);
            if (!value.isEmpty()) return value;
            System.out.println(Messages.EMPTY);
        }
    }

    public static int integer(String message, int min, int max) {
        while (true) {
            try {
                int value = Integer.parseInt(required(message));
                if (value >= min && value <= max) return value;
                System.out.printf("Value must be from %d to %d!%n", min, max);
            } catch (NumberFormatException e) {
                System.out.println(Messages.INVALID_NUMBER);
            }
        }
    }

    public static double positiveDouble(String message) {
        while (true) {
            try {
                double value = Double.parseDouble(required(message));
                if (Double.isFinite(value) && value > 0) return value;
                System.out.println("Value must be greater than 0!");
            } catch (NumberFormatException e) {
                System.out.println(Messages.INVALID_NUMBER);
            }
        }
    }

    public static Integer optionalYear(String message) {
        while (true) {
            String value = input(message);
            if (value.isEmpty()) return null;
            try {
                int year = Integer.parseInt(value);
                if (year >= Constants.FIRST_VEHICLE_YEAR && year <= Constants.CURRENT_YEAR) {
                    return year;
                }
                System.out.printf("Year must be from %d to %d!%n",
                        Constants.FIRST_VEHICLE_YEAR, Constants.CURRENT_YEAR);
            } catch (NumberFormatException e) {
                System.out.println(Messages.INVALID_NUMBER);
            }
        }
    }

    public static Double optionalPositiveDouble(String message) {
        while (true) {
            String value = input(message);
            if (value.isEmpty()) return null;
            try {
                double number = Double.parseDouble(value);
                if (Double.isFinite(number) && number > 0) return number;
                System.out.println("Value must be greater than 0!");
            } catch (NumberFormatException e) {
                System.out.println(Messages.INVALID_NUMBER);
            }
        }
    }

    public static boolean yesNo(String message) {
        while (true) {
            String value = required(message);
            if (value.equalsIgnoreCase("Y")) return true;
            if (value.equalsIgnoreCase("N")) return false;
            System.out.println("Please enter Y or N!");
        }
    }
}
