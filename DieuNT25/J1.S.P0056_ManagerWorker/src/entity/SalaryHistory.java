/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author win
 */
public class SalaryHistory implements Comparable<SalaryHistory>, Serializable {

    private String workerID;
    private double salaryUpdate;
    private SalaryStatus status;
    private Date date;

    public SalaryHistory() {
    }

    public SalaryHistory(String workerID, double salaryUpdate, SalaryStatus status, Date date) {
        this.workerID = workerID;
        this.salaryUpdate = salaryUpdate;
        this.status = status;
        this.date = date;
    }

    public String getWorkerID() {
        return workerID;
    }

    public void setWorkerID(String workerID) {
        this.workerID = workerID;
    }

    public double getSalaryUpdate() {
        return salaryUpdate;
    }

    public void setSalaryUpdate(double salaryUpdate) {
        // Kiểm tra lương có lớn hơn 0 không
        if (salaryUpdate <= 0) {
            throw new IllegalArgumentException("Invalid Salary. Salary must be a positive number.");
        }
        this.salaryUpdate = salaryUpdate;
    }

    public SalaryStatus getStatus() {
        return status;
    }

    public void setStatus(SalaryStatus status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return "SalaryHistory{" + workerID + ", " + salaryUpdate + ", " + status + ", " + dateFormat.format(date) + '}';
    }

    @Override
    public int compareTo(SalaryHistory o) {
        return workerID.compareTo(o.workerID);
    }

}
