/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class SalaryHistory {//lưu 1 lần điều chỉnh lương của worker

    private int salaryHistory;
    private SalaryStatus status;
    private Date date;

    public SalaryHistory() {
    }

    public SalaryHistory(int salaryHistory, SalaryStatus status, Date date) {
        this.salaryHistory = salaryHistory;
        this.status = status;
        this.date = date;
    }

    public int getSalaryHistory() {
        return salaryHistory;
    }

    public void setSalaryHistory(int salaryHistory) {
        this.salaryHistory = salaryHistory;
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

}
