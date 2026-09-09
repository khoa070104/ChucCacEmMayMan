package Utilities;

import java.time.LocalDate;

public final class DataValidation {
    private DataValidation() { }

    public static boolean isStudentId(String value) {
        return value != null && value.trim().matches("(?i)STU\\d{4}");
    }

    public static boolean isFullName(String value) {
        return value != null && value.trim().split("\\s+").length >= 2;
    }

    public static boolean isNotBlank(String value) {
        return value != null && !value.trim().isEmpty();
    }

    public static boolean isGpa(double value) { return value >= 0.0 && value <= 4.0; }
    public static boolean isPositive(int value) { return value >= 1; }
    public static boolean isFutureDate(LocalDate value) {
        return value != null && value.isAfter(LocalDate.now());
    }
}
