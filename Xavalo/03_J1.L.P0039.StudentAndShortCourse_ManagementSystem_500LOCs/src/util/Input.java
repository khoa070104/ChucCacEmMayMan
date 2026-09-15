package util;

import model.Course;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.function.Predicate;

public class Input {
    private final Scanner scanner = new Scanner(System.in);
    public String text(String prompt, Predicate<String> valid, String error) {
        while (true) { System.out.print(prompt); String value = scanner.nextLine().trim(); if (valid.test(value)) return value; System.out.println(error); }
    }
    public int integer(String prompt, Predicate<Integer> valid, String error) {
        while (true) { try { int n = Integer.parseInt(text(prompt, s -> !s.isEmpty(), error)); if (valid.test(n)) return n; } catch (NumberFormatException ignored) { } System.out.println(error); }
    }
    public double decimal(String prompt, Predicate<Double> valid, String error) {
        while (true) { try { double n = Double.parseDouble(text(prompt, s -> !s.isEmpty(), error)); if (valid.test(n)) return n; } catch (NumberFormatException ignored) { } System.out.println(error); }
    }
    public LocalDate futureDate(String prompt) {
        while (true) { try { LocalDate d = LocalDate.parse(text(prompt, s -> !s.isEmpty(), "Date is required."), Course.DATE_FORMAT); if (d.isAfter(LocalDate.now())) return d; } catch (RuntimeException ignored) { } System.out.println("Start date must use dd/MM/yyyy and be in the future."); }
    }
    public boolean yesNo(String prompt) { return text(prompt, s -> s.equalsIgnoreCase("Y") || s.equalsIgnoreCase("N"), "Enter Y or N.").equalsIgnoreCase("Y"); }
}
