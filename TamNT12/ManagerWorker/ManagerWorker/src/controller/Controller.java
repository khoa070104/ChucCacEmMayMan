/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Collections;
import java.util.Date;
import model.SalaryHistory;
import model.SalaryStatus;
import model.Worker;

/**
 *
 * @author Admin
 */
public class Controller {

    private ArrayList<Worker> list;

    public Controller() {
        list = new ArrayList<>();
    }
// add

    public boolean addWorker(Worker worker) throws Exception {
        for (Worker w : list) {
            if (w.getId().equalsIgnoreCase(worker.getId())) {
                throw new Exception("The worker already exist!!");
            }
        }
        System.out.println("Add successfull!!");
        return list.add(worker);
    }

    // dung cho option 2 va 3, tim worker de tim ra worker tang hay giam va luu vao lich su
    public Worker findWorker(String code) throws Exception {
        for (Worker worker : list) {
            if (worker.getId().equalsIgnoreCase(code)) {
                return worker;
            }
        }
        throw new Exception("Worker cannot found!!");
    }

    public boolean changeSalary(SalaryStatus status, String code, int amount, Date date) throws Exception {
        Worker worker = findWorker(code);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date today = dateFormat.parse(dateFormat.format(new Date()));
        //newDate ở dạng giờ phút giây và ngày tháng
        //dateFormat.format(new Date()) chuyển sang dạng ngày tháng năm được quy rõ trong simpleDateFormat ở dạng string là giờ phút giây bị bỏ
        // dateFormat.parse chuyên từ string sang date nhưng vì đã bỏ giờ phút giây nên khi chuyển sang date giờ phút giây = 0
        if (date.before(today)) {
            throw new Exception("Date must be>= today");
        }
// dùng để dùng với lần thay đổi  lương đầu tiên khi getHistory rỗng, giả dụ hôm nay là 3.7 nhập 25.6 thiếu dòng này thì vẫn có thể change lương
        if (!worker.getHistory().isEmpty()) {
            SalaryHistory lastHistory = worker.getHistory().get(worker.getHistory().size() - 1);
            if (date.before(lastHistory.getDate())) {
                throw new Exception("Date must be>= last salary change!!");
            }
        }
        if (status == status.UP) {
            worker.setSalary(worker.getSalary() + amount);
        } else {
            if (worker.getSalary() <= amount) {
                throw new Exception("The salary must be > 0");
            }
            worker.setSalary(worker.getSalary() - amount);
        }
        worker.getHistory().add(new SalaryHistory(worker.getSalary(), status, date));
        // changeSalary cũ dùng newDate để lưu ngày thay đổi lương dựa trên hệ thôn
        // changeSalary mới dùng date để lưu ngày thay đổi lưogn dựa vào date ng dùng nhập
        System.out.println("Change Salary Sucessful!!");
        return true;
    }

    // sort phục vụ cho option 4
    public ArrayList<Worker> sortList() throws Exception {
        if (list.isEmpty()) {
            throw new Exception("List is empty!!");
        }
        ArrayList<Worker> listSort = new ArrayList<>(list);
        Collections.sort(listSort);
        return listSort;
    }

    public String displayByDateRange(Date dateFrom, Date dateTo) throws Exception {
        if (list.isEmpty()) {
            throw new Exception("List is empty");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String str = String.format("%-10s%-15s%-10s%-15s%-10s%-15s\n",
                "Code", "Name", "Age", "Salary", "Status", "Date");
        boolean hasResult = false;
        for (Worker worker : sortList()) {
            for (SalaryHistory h : worker.getHistory()) {
                Date d = h.getDate();
                if (dateFrom == null && dateTo == null) {
                    str += worker.formatRow(h);
                    hasResult = true;
                } else if (dateFrom != null && dateTo == null) {
                    if (!d.before(dateFrom)) {
                        str += worker.formatRow(h);
                        hasResult = true;
                    }
                } else if (dateFrom == null && dateTo != null) {
                    if (!d.after(dateTo)) {
                        str += worker.formatRow(h);
                        hasResult = true;
                    }
                } else if (dateFrom != null && dateTo != null) {
                    if (!d.before(dateFrom) && !d.after(dateTo)) {
                        str += worker.formatRow(h);
                        hasResult = true;
                    }
                }
            }
        }
        if (!hasResult) {
            throw new Exception("Not worker in this time!!");
        }
        return str;
    }
}
