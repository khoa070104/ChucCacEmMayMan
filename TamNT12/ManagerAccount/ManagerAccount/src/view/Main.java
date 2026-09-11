/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;

/**
 *
 * @author Admin
 */
public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller();
        AccountInput view = new AccountInput();

        while (true) {
            int choice = view.showMenu();
            switch (choice) {
                case 1:
                    try {
                        controller.addAccount();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    controller.login();
                    break;
                case 3:
                    System.out.println("Good Bye");
                    System.exit(0);
            }
        }
    }
}