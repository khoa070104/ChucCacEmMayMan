package Utilities;

import Entity.Course;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public final class DataInput {
    private static final Scanner SCANNER = new Scanner(System.in);

    private DataInput() {}

    public static String getString(String prompt) {
        System.out.print(prompt);
        return SCANNER.nextLine().trim();
    }

    public static int getInteger(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(getString(prompt));
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    public static double getDouble(String prompt) {
        while (true) {
            try {
                return Double.parseDouble(getString(prompt));
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    public static LocalDate getDate(String prompt) {
        while (true) {
            try {
                return LocalDate.parse(getString(prompt), Course.DATE_FORMAT);
            } catch (DateTimeParseException ex) {
                System.out.println("Date must follow dd/MM/yyyy.");
            }
        }
    }

    public static boolean getYesNo(String prompt) {
        while (true) {
            String answer = getString(prompt);
            if (answer.equalsIgnoreCase("Y")) return true;
            if (answer.equalsIgnoreCase("N")) return false;
            System.out.println("Please enter Y or N.");
        }
    }
}
