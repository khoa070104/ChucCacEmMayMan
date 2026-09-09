package controller;

import java.util.Scanner;

/** Reads validated student data. @author Ho Vi Lo @since 09/09/2026 */
public class Inputter {
    private final Scanner scanner = new Scanner(System.in);

    /** @param message prompt @return non-empty text */
    public String readRequired(String message) {
        while (true) {
            System.out.print(message);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) return value;
            System.out.println("Input must not be empty.");
        }
    }

    /** @param subject subject label @return mark in the range from 0 to 10 */
    public double readMark(String subject) {
        while (true) {
            System.out.print(subject + ": ");
            try {
                double mark = Double.parseDouble(scanner.nextLine().trim());
                if (mark >= 0 && mark <= 10) return mark;
                System.out.println(
                        mark > 10
                                ? subject + " must be less than or equal to ten."
                                : subject + " must be greater than or equal to zero.");
            } catch (NumberFormatException exception) {
                System.out.println(subject + " must be a number.");
            }
        }
    }

    /** @param message prompt @return true for Y and false for N */
    public boolean readYesNo(String message) {
        while (true) {
            System.out.print(message);
            String value = scanner.nextLine().trim();
            if (value.equalsIgnoreCase("Y")) return true;
            if (value.equalsIgnoreCase("N")) return false;
            System.out.println("Please enter Y or N.");
        }
    }
}
