/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.util.Date;
import model.SalaryStatus;
import model.Worker;

/**
 *
 * @author Admin
 */
public class Main {

    public static void main(String[] args) {
        Controller manager = new Controller();

        while (true) {
            int choice = Validator.getInt("======== Worker Management ========= \n"
                    + "1.	Add Worker\n"
                    + "2.	Up Salary\n"
                    + "3.	Down salary\n"
                    + "4.	Display Information salary\n"
                    + "5.	Exit\n"
                    + "Enter choice: ", "Invalid!!!", "just 1--->5", 1, 5);
            switch (choice) {
                case 1:
                    try {
                        System.out.println(" --------- Add Worker ----------");

                        WorkerInput input = new WorkerInput();
                        Worker worker = input.input();
                        manager.addWorker(worker);

                    } catch (Exception e) {
                        System.out.println("Lỗi" + e.getMessage() + " nhap lại!!");
                    }
                    break;

                case 2:
                    try {
                        String code = Validator.getString("Enter code: ", "Invalid!!", "[A-Za-z0-9]+");
                        int amount = Validator.getInt("Enter salary: ", "Invalid", "Invalid", 1, Integer.MAX_VALUE);
                        Date date = Validator.getDate("Enter date you want change Salary: ", "Invalid", "dd/MM/yyyy");
                        manager.changeSalary(SalaryStatus.UP, code, amount, date);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 3:
                    try {
                        String code = Validator.getString("Enter code: ", "Invalid!!", "[A-Za-z0-9]+");
                        int amount = Validator.getInt("Enter salary: ", "Invalid", "Invalid", 1, Integer.MAX_VALUE);
                        Date date = Validator.getDate("Enter date you want change Salary: ", "Invalid", "dd/MM/yyyy");
                        manager.changeSalary(SalaryStatus.DOWN, code, amount, date);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 4:
                    try {
                        Date dateFrom = Validator.getDateOrNull("Enter Date From (dd/MM/yyyy or press Enter to skip): ", "Invalid date format!!", "dd/MM/yyyy");
                        Date dateTo = Validator.getDateOrNull("Enter Date To (dd/MM/yyyy or press Enter to skip): ", "Invalid date format!!", "dd/MM/yyyy");
                        System.out.println(manager.displayByDateRange(dateFrom, dateTo));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    System.exit(0);
                    break;
            }
        }
    }

}
