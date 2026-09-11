/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import view.StudentInput;

/**
 *
 * @author Admin
 */
public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller();
        StudentInput input = new StudentInput();

        // Tạo dữ liệu giả lập ban đầu
        controller.generateStudent();

        // Vòng lặp menu ứng dụng
        while (true) {
            int choice = input.showMenu();
            switch (choice) {
                case 1:
                    controller.createStudent();
                    break;
                case 2:
                    controller.findAndSort();
                    break;
                case 3:
                    controller.updateOrDelete();
                    break;
                case 4:
                    try {
                        controller.report();
                    } catch (Exception e) {
                        input.showMessage(e.getMessage());
                    }

                    break;
                case 5:
                    input.showMessage("Goodbye!");
                    System.exit(0);
            }
        }
    }
}
