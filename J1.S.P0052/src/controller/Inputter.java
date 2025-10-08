package controller;

import java.util.Scanner;

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
        } while (input.isEmpty());
        return input;
    }
    
    public static String inputRequired(String label, String regex) {
        String input;
        do {
            System.out.print(label);
            input = sc.nextLine();
        } while (!input.matches(regex));
        return input;
    }

    public static String inputOptional(String label) {
        String input;
        while (true) {
            System.out.print(label);
            input = sc.nextLine();
            if (input == null || input.isEmpty()) return "";
            else return input;
        }
    }
    
    public static String inputOptional(String label, String regex) {
        String input;
        while (true) {
            System.out.print(label);
            input = sc.nextLine();
            if (input == null || input.isEmpty()) return "";
            if (input.matches(regex)) return input;
        }
    }

    public static float inputFloat(String label) {
        float value;
        while (true) {
            try {
                System.out.print(label);
                value = Float.parseFloat(sc.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    public static int inputInt(String label) {
        int value;
        while (true) {
            try {
                System.out.print(label);
                String input = sc.nextLine();
                if (input.isEmpty()) {
                    System.out.println("Input cannot be empty! Please try again.");
                    continue;
                }
                value = Integer.parseInt(input);
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
            }
        }
    }
}
