package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.function.Predicate;

public class InputReader {
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/uuuu");
    private final Scanner scanner;

    public InputReader() {
        scanner = new Scanner(System.in);
    }

    public String readRequired(String prompt, Predicate<String> validator, String errorMessage) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (validator.test(value)) return value;
            System.out.println(errorMessage);
        }
    }

    public String readOptional(String prompt, Predicate<String> validator, String errorMessage) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (value.isEmpty() || validator.test(value)) return value;
            System.out.println(errorMessage);
        }
    }

    public int readPositiveInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value > 0) return value;
            } catch (NumberFormatException exception) {
                // A common message is shown below.
            }
            System.out.println("Please enter an integer greater than zero.");
        }
    }

    public Integer readOptionalPositiveInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) return null;
            try {
                int value = Integer.parseInt(input);
                if (value > 0) return value;
            } catch (NumberFormatException exception) {
                // A common message is shown below.
            }
            System.out.println("Please enter an integer greater than zero, or leave blank.");
        }
    }

    public LocalDate readFutureDate(String prompt, boolean optional) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (optional && input.isEmpty()) return null;
            try {
                LocalDate date = LocalDate.parse(input, DATE_FORMAT);
                if (Validator.isFutureDate(date)) return date;
            } catch (DateTimeParseException exception) {
                // A common message is shown below.
            }
            System.out.println("Date must follow dd/MM/yyyy and be in the future.");
        }
    }

    public boolean readYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("Y")) return true;
            if (input.equalsIgnoreCase("N")) return false;
            System.out.println("Please enter Y or N.");
        }
    }
}
