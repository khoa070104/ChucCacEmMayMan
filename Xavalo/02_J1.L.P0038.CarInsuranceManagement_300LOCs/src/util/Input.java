package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.function.Predicate;

public class Input {
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/uuuu");
    private final Scanner scanner = new Scanner(System.in);

    public String text(String prompt, Predicate<String> valid, String error) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (valid.test(value)) return value;
            System.out.println(error);
        }
    }

    public String optional(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public int integer(String prompt, Predicate<Integer> valid, String error) {
        while (true) {
            try {
                int value = Integer.parseInt(text(prompt, s -> !s.isEmpty(), error));
                if (valid.test(value)) return value;
            } catch (NumberFormatException ignored) { }
            System.out.println(error);
        }
    }

    public long positiveLong(String prompt) {
        while (true) {
            try {
                long value = Long.parseLong(text(prompt, s -> !s.isEmpty(), "Enter a number over 999."));
                if (value > 999) return value;
            } catch (NumberFormatException ignored) { }
            System.out.println("Enter a number over 999.");
        }
    }

    public LocalDate date(String prompt, Predicate<LocalDate> valid) {
        while (true) {
            try {
                LocalDate value = LocalDate.parse(optional(prompt), DATE_FORMAT);
                if (valid.test(value)) return value;
            } catch (DateTimeParseException ignored) { }
            System.out.println("Enter a valid date in MM/dd/yyyy format.");
        }
    }

    public boolean yesNo(String prompt) {
        return text(prompt, s -> s.equalsIgnoreCase("Y") || s.equalsIgnoreCase("N"),
                "Enter Y or N.").equalsIgnoreCase("Y");
    }
}
