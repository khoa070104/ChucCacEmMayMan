/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import entity.SalaryHistory;
import entity.SalaryStatus;
import entity.Worker;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import utils.Validator;

/**
 *
 * @author win
 */
public class Manager {

    private List<Worker> workers;
    private List<SalaryHistory> histories;

    public Manager() {
        histories = new ArrayList<>();
        workers = new ArrayList<>();
    }

    private boolean isExist(String id) {
        for (Worker workers : workers) {
            if (workers.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    private Worker getWorker(String id) {
        for (Worker workers : workers) {
            if (workers.getId().equalsIgnoreCase(id)) {
                return workers;
            }
        }
        return null;
    }

    public void addWorker() throws Exception {
        Worker worker = new Worker();
        worker.input();
        if (isExist(worker.getId())) {
            throw new Exception("Worker with ID " + worker.getId() + " already exists.");
        }
        if(workers.add(worker)){
            System.out.println("Add success: "+worker.toString());
        }
    }

    public boolean addSalaryHistory(SalaryHistory history) {
        return histories.add(history);
    }

    public void upSalary() throws Exception {
        String code = Validator.getString("Enter id: ", "Invalid!", "[Ww]\\d+").toUpperCase();
        double amount = Validator.getDouble("Enter Salary: ", "salary must be > 0", "Invalid!",
                Double.MIN_VALUE, Double.MAX_VALUE);
        Worker worker = changeSalary(SalaryStatus.UP, code, amount);
        SalaryHistory history = new SalaryHistory(worker.getId(), worker.getSalary(), SalaryStatus.UP, new Date());
        if (addSalaryHistory(history)) {
            System.out.println("Up salary: " + code + " Success!!!");
        } else {
            throw new Exception("Can not up salary!");
        }
    }

    public void downSalary() throws Exception {
        String code = Validator.getString("Enter id: ", "Invalid!", "[Ww]\\d+").toUpperCase();
        double amount = Validator.getDouble("Enter Salary: ", "salary must be > 0", "Invalid!",
                Double.MIN_VALUE, Double.MAX_VALUE);
        Worker worker = changeSalary(SalaryStatus.DOWN, code, amount);
        SalaryHistory history = new SalaryHistory(worker.getId(), worker.getSalary(), SalaryStatus.DOWN, new Date());
        if (addSalaryHistory(history)) {
            System.out.println("Down salary: " + code + " Success!!!");
        } else {
            throw new Exception("Can not down salary!");
        }
    }

    private Worker changeSalary(SalaryStatus status, String code, double amount) throws Exception {
        if (workers.isEmpty()) {
            throw new Exception("List is empty!Can not change salary");
        }
        if (!isExist(code)) {
            throw new Exception("Can not found code!");
        }
        if (amount <= 0) {
            throw new Exception("Amount of money must be > 0 ");
        }
        Worker worker = getWorker(code);
        switch (status) {
            case UP:
                worker.setSalary(worker.getSalary() + amount);
                break;
            case DOWN:
                if (worker.getSalary() - amount < 0) {
                    throw new Exception("Can not down " + amount);
                }
                worker.setSalary(worker.getSalary() - amount);
                break;
        }
        return worker;
    }

    public void showHistory() throws Exception {
        if (histories.isEmpty()) {
            throw new Exception("History Salary is empty!!");
        }
        Collections.sort(histories);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String str = String.format("%7s%10s%10s%10s%10s%15s\n", "Code", "Name", "Age", "Salary", "Status", "Date");
        for (int i = 0; i < histories.size(); i++) {
            String workerID = histories.get(i).getWorkerID();
            str += String.format("%7s%10s%10d%10.0f%10s%15s\n", workerID, getWorker(workerID).getName(),
                    getWorker(workerID).getAge(), histories.get(i).getSalaryUpdate(),
                    histories.get(i).getStatus(), dateFormat.format(histories.get(i).getDate()));
        }
        System.out.println(str);
    }

    //Yeu cau them hien thi danh sach worker
    public void showWorker() throws Exception {
        if (workers.isEmpty()) {
            throw new Exception("List Worker is empty!!");
        }
        String str = String.format("%7s%10s%10s%10s%15s\n", "Code", "Name", "Age", "Salary", "workLocation");
        for (int i = 0; i < workers.size(); i++) {
            str += workers.get(i);
        }
        System.out.println(str);
    }

}
