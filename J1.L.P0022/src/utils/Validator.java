package utils;

import java.util.Calendar;

/**
 * Validation utilities for candidate data.
 */
public class Validator {

    private static final int MIN_BIRTH_YEAR = 1900;
    private static final String[] GRADUATION_RANKS = {"Excellence", "Good", "Fair", "Poor"};

    private Validator() {
    }

    public static boolean isValidBirthYear(String input) {
        if (input == null || !input.matches("\\d{4}")) {
            return false;
        }
        int year = Integer.parseInt(input);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return year >= MIN_BIRTH_YEAR && year <= currentYear;
    }

    public static boolean isValidPhone(String phone) {
        return phone != null && phone.matches("\\d{10,}");
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.[\\w.-]+$");
    }

    public static boolean isValidExpInYear(String input) {
        if (input == null || !input.matches("\\d+")) {
            return false;
        }
        int years = Integer.parseInt(input);
        return years >= 0 && years <= 100;
    }

    public static boolean isValidGraduationRank(String rank) {
        if (rank == null || rank.trim().isEmpty()) {
            return false;
        }
        for (String valid : GRADUATION_RANKS) {
            if (valid.equalsIgnoreCase(rank.trim())) {
                return true;
            }
        }
        return false;
    }

    public static String normalizeGraduationRank(String rank) {
        for (String valid : GRADUATION_RANKS) {
            if (valid.equalsIgnoreCase(rank.trim())) {
                return valid;
            }
        }
        return rank.trim();
    }

    public static boolean isYesNo(String input) {
        return input != null && input.trim().matches("[YyNn]");
    }

    public static boolean isContinue(String input) {
        return input != null && input.trim().equalsIgnoreCase("Y");
    }

    public static boolean isValidCandidateType(String input) {
        return input != null && input.matches("[012]");
    }
}
