/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import view.TaskInput;
import controller.ManagerTask;

import entity.Task;
import java.util.Date;
import view.Validator;

/**
 *
 * @author Admin
 */
public class Main {

    private static void showTask(ManagerTask managerTask) throws Exception {
        String str = managerTask.toString();
        if (str == null) {
            throw new Exception("List is Empty");
        }
        System.out.println(str);
    }

    public static void main(String[] args) {
        ManagerTask managerTask = new ManagerTask();
        TaskInput taskInput;
        while (true) {
            int choice = Validator.getInt("========= Task program =========\n"
                    + "1.	Add Task\n"
                    + "2.	Delete task\n"
                    + "3.	Display Task\n"
                    + "4.	exit\n"
                    + "Your choice: ", "Just 1-4", "Invalid", 1, 4);
            switch (choice) {
                case 1:
                    System.out.print("------------Add Task---------------\n");
                    try {
                        taskInput = new TaskInput();
                        Task task = taskInput.input();
                        int indexTask = managerTask.addTask(task.getTaskTypeID(), task.getRequirementName(), task.getDate(), task.getPlanFrom(), task.getPlanTo(),
                                task.getAssign(), task.getReview());
                        System.out.println("Add sucessful: " + indexTask);
                        showTask(managerTask);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("------------Del Task---------------\n");
                    try {
                        int ID = Validator.getInt("ID: ", "Invalid", "Invalid", 1, Integer.MAX_VALUE);
                        managerTask.deleteTask(ID);
                        System.out.println("Delete sucessful. ");

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("------------Show Task---------------\n");
                    try {
                        showTask(managerTask);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        showTask(managerTask);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    System.exit(0);
            }
        }
    }

}
