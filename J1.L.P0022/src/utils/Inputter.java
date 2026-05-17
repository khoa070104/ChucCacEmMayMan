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

    public static String inputBirthYear() {
        return inputYear("Enter Birth Date (yyyy): ");
    }

    public static String inputGraduationYear() {
        return inputYear("Enter Graduation Date (yyyy): ");
    }

    private static String inputYear(String label) {
        String value;
        do {
            value = inputRequired(label);
            if (!Validator.isValidBirthYear(value)) {
                System.out.println("Invalid year! Must be 4 digits from 1900 to current year.");
            }
        } while (!Validator.isValidBirthYear(value));
        return value;
    }

    public static String inputPhone() {
        String value;
        do {
            value = inputRequired("Enter Phone: ");
            if (!Validator.isValidPhone(value)) {
                System.out.println("Invalid phone! Must be numeric with at least 10 digits.");
            }
        } while (!Validator.isValidPhone(value));
        return value;
    }

    public static String inputEmail() {
        String value;
        do {
            value = inputRequired("Enter Email: ");
            if (!Validator.isValidEmail(value)) {
                System.out.println("Invalid email! Format: account@domain (e.g. annguyen@fpt.edu.vn)");
            }
        } while (!Validator.isValidEmail(value));
        return value;
    }

    public static int inputExpInYear() {
        String value;
        do {
            value = inputRequired("Enter Year of Experience: ");
            if (!Validator.isValidExpInYear(value)) {
                System.out.println("Invalid year of experience! Must be a number from 0 to 100.");
            }
        } while (!Validator.isValidExpInYear(value));
        return Integer.parseInt(value);
    }

    public static String inputGraduationRank() {
        String value;
        do {
            value = inputRequired("Enter Rank of Graduation (Excellence/Good/Fair/Poor): ");
            if (!Validator.isValidGraduationRank(value)) {
                System.out.println("Invalid rank! Must be one of: Excellence, Good, Fair, Poor.");
            }
        } while (!Validator.isValidGraduationRank(value));
        return Validator.normalizeGraduationRank(value);
    }

    public static String inputYesNo() {
        String value;
        do {
            value = inputRequired("Do you want to continue (Y/N)? ");
            if (!Validator.isYesNo(value)) {
                System.out.println("Please enter Y or N.");
            }
        } while (!Validator.isYesNo(value));
        return value.trim();
    }

    public static int inputCandidateType() {
        String value;
        do {
            value = inputRequired("Input type of candidate: ");
            if (!Validator.isValidCandidateType(value)) {
                System.out.println("Invalid type! Must be 0 (Experience), 1 (Fresher), or 2 (Intern).");
            }
        } while (!Validator.isValidCandidateType(value));
        return Integer.parseInt(value);
    }
}
