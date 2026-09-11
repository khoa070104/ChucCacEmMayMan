/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import controller.ManagerTask;
import entity.Task;
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
        ManagerTask control = new ManagerTask();
        while (true) {
            System.out.println("========= Task program =========");
            System.out.println("1. Add task");
            System.out.println("2. Delete tasks");
            System.out.println("3. Display  task");
            System.out.println("4. Quit");
            int option = Validator.getInt("Your choice: ",
                    "Just enter 1->4", "Invalid!", 1, 4);
            //handing option
            switch (option) {
                case 1:
                    System.out.println("------------Add Task---------------");
                    int newTaskID;
                    try {
                        Task task = new Task();
                        task.input();
                        newTaskID = control.add(task);
                        System.out.println("Add success ID: " + newTaskID);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("------------Delete Task---------------");
                    try {
                        int ID = Validator.getInt("Task ID: ", "Invalid!", "Invalid!",
                                Integer.MIN_VALUE, Integer.MAX_VALUE);
                        control.delete(ID);
                        System.out.println("Delete sucess!!");
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("---------------------------------------"
                            + "-- Task ---------------------------------------");
                    try {
                        control.show();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 4:
                    System.exit(0);
            }
        }
    }
}
