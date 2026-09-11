/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Worker implements Comparable<Worker> {

    private String id;
    private String name;
    private int age;
    private int salary;
    private String workLocation;
    private ArrayList<SalaryHistory> history = new ArrayList<>();

    public Worker() {
    }

    public Worker(String id, String name, int age, int salary, String workLocation) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.workLocation = workLocation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getWorkLocation() {
        return workLocation;
    }

    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
    }

    public ArrayList<SalaryHistory> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<SalaryHistory> history) {
        this.history = history;
    }

    @Override
    public int compareTo(Worker o) {
        return this.getId().compareToIgnoreCase(o.id);
    }

    public String formatRow(SalaryHistory h) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return String.format("%-10s%-15s%-10s%-15s%-10s%-15s\n",
                this.getId(),
                this.getName(),
                this.getAge(),
                h.getSalaryHistory(),
                h.getStatus(),
                dateFormat.format(h.getDate()));
    }

}
