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

    public static String inputOptional(String label) {
        System.out.print(label);
        String input = sc.nextLine();
        return input.trim();
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
                if (value < Constants.MIN_AVAILABILITY) {
                    System.out.println(Messages.ERR_AVAILABILITY_INVALID);
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    public static int inputIntOptional(String label) {
        while (true) {
            try {
                System.out.print(label);
                String input = sc.nextLine();
                if (input.trim().isEmpty()) {
                    return -1; // Indicate no input
                }
                int value = Integer.parseInt(input.trim());
                if (value < Constants.MIN_AVAILABILITY) {
                    System.out.println(Messages.ERR_AVAILABILITY_INVALID);
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number or leave empty.");
            }
        }
    }
}
