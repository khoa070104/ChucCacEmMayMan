/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Worker;

/**
 *
 * @author Admin
 */
public class WorkerInput {

    private Worker worker;

    public WorkerInput() {
        worker = new Worker();
    }

    public Worker input() {
        worker.setId(Validator.getString("Enter ID: ", "Invalid", "[A-Za-z0-9]+"));
        worker.setName(Validator.getString("Enter Name: ", "Invalid!!", "[A-Za-z\\s]+"));
        worker.setAge(Validator.getInt("Enter Age: ", "Invalid!!", "Age must be 18-50!!", 18, 50));
        worker.setSalary(Validator.getInt("Enter Salary: ", "Invalid!!", "Salary must be greater than 0", 1, Integer.MAX_VALUE));
        worker.setWorkLocation(Validator.getString("Enter location: ", "Invalid!!", "[A-Za-z\\s]+"));
        return worker;
    }

}
