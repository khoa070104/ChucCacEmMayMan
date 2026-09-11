/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import java.util.Locale;
import utils.Validate;

/**
 *
 * @author win
 */
public class Main {

    public static void main(String[] args) {
        TPBank bank = new TPBank();
        menu();
        int choice = Validate.getInt("Please enter 1-3: ", "Just 1->3", "Invalid!", 1, 3);

        switch (choice) {
            case 1:
                bank.login(new Locale("vi", "VN"));
                break;
            case 2:
                bank.login(new Locale("en", "EN"));
                break;
            case 3:
                System.exit(0);
                break;
        }
    }

    public static void menu() {
        System.out.println("\n-------Login Program--------");
        System.out.println("1. Vietnamese");
        System.out.println("2. English");
        System.out.println("3. Exit");
    }
}
