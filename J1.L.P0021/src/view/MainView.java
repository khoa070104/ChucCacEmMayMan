package view;

import controller.StudentController;
import controller.Inputter;
import common.Messages;

public class MainView {
    private final StudentController studentController;

    public MainView() {
        this.studentController = new StudentController();
    }

    public void displayMenu() {
        System.out.println("\nWELCOME TO STUDENT MANAGEMENT");
        System.out.println();
        System.out.println("1. Create");
        System.out.println("2. Find and Sort");
        System.out.println("3. Update/Delete");
        System.out.println("4. Report");
        System.out.println("5. Exit");
        System.out.println();
        System.out.println("(Please choose 1 to Create, 2 to Find and Sort, 3 to Update/Delete, "
                + "4 to Report and 5 to Exit program).");
    }

    public void run() {
        while (true) {
            displayMenu();
            int choice = getMenuChoice();

            switch (choice) {
                case 1:
                    studentController.createStudent();
                    break;
                case 2:
                    studentController.findAndSort();
                    break;
                case 3:
                    studentController.updateOrDelete();
                    break;
                case 4:
                    studentController.report();
                    break;
                case 5:
                    System.out.println(Messages.MSG_PROGRAM_EXIT);
                    return;
                default:
                    System.out.println(Messages.ERR_INVALID_INPUT);
            }
        }
    }

    private int getMenuChoice() {
        while (true) {
            try {
                String input = Inputter.inputRequired("Your choice: ");
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= 5) {
                    return choice;
                }
                System.out.println("Please enter a number from 1 to 5!");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }
}
