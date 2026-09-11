/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import bo.Manager;
import utils.Validator;

/**
 *
 * @author win
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Manager control = new Manager();
        do {
            int choice = Validator.getInt("======== Worker Management =========\n"
                    + "1.	Add Worker\n"
                    + "2.	Up salary\n"
                    + "3.	Down salary\n"
                    + "4.	Display Information salary\n"
                    + "5.	Display list worker (Yeu cau them)\n"
                    + "Enter your choice: ",
                    "Just 1 -> 5", "Invalid!", 1, 5);
            switch (choice) {
                case 1:
                    try {
                        control.addWorker();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 2:
                    try {
                        control.upSalary();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 3:
                    try {
                        control.downSalary();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 4:
                    try {
                        control.showHistory();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 5:
                    try {
                        control.showWorker();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    System.exit(0);
            }
        } while (true);
    }

}
