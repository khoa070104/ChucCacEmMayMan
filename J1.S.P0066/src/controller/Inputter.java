package controller;

import java.util.Scanner;
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
                System.out.println(Messages.ERR_INPUT_EMPTY);
            }
        } while (input.trim().isEmpty());
        return input.trim();
    }

    public static String inputOptional(String label) {
        System.out.print(label);
        String input = sc.nextLine();
        return input.trim();
    }
}
