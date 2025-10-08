package controller;

import java.util.Scanner;
import common.Constants;
import common.Messages;

public class Inputter {
    static Scanner sc = new Scanner(System.in);

    public static String input(String label) {
        System.out.print(label);
        String input = sc.nextLine();
        return input;
    }
    
    public static String inputRequired(String label) {
        String input;
        do {
            System.out.print(label);
            input = sc.nextLine();
            if (input.trim().isEmpty()) {
                System.out.println("Input cannot be empty!");
            }
        } while (input.trim().isEmpty());
        return input.trim();
    }
    
    public static String inputRequired(String label, String regex, String errorMessage) {
        String input;
        do {
            System.out.print(label);
            input = sc.nextLine();
            if (input.trim().isEmpty()) {
                System.out.println("Input cannot be empty!");
                continue;
            }
            if (!input.matches(regex)) {
                System.out.println(errorMessage);
            }
        } while (input.trim().isEmpty() || !input.matches(regex));
        return input.trim();
    }

    public static String inputUsername() {
        return inputRequired("Enter Username: ", Constants.USERNAME_REGEX, Messages.ERR_USERNAME_TOO_SHORT);
    }

    public static String inputPassword() {
        return inputRequired("Enter Password: ", Constants.PASSWORD_REGEX, Messages.ERR_PASSWORD_TOO_SHORT);
    }

    public static int inputInt(String label) {
        int value;
        while (true) {
            try {
                System.out.print(label);
                String input = sc.nextLine();
                if (input.trim().isEmpty()) {
                    System.out.println("Input cannot be empty!");
                    continue;
                }
                value = Integer.parseInt(input.trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }
}
