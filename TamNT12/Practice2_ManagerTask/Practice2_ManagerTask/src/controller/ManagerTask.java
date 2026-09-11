/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Task;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class ManagerTask {

    private ArrayList<Task> list;
    private int lastId;

    public ManagerTask() {
        list = new ArrayList<>();

    }

    public ArrayList<Task> getList() {
        return list;
    }

    public void setList(ArrayList<Task> list) {
        this.list = list;
    }

    // kiểm tra sự trùng lặp 
// kiểm tra xem có ai trùng thời gian không
 

    private boolean isDupplicate(int TaskType, String requirementName, Date date, double planFrom, double planTo, String assign, String review) {
        for (Task task : list) {
            if (task.getTaskTypeID() == TaskType
                    && task.getRequirementName().equalsIgnoreCase(requirementName)
                    && task.getDate().compareTo(date) == 0
                    && task.getPlanFrom() == planFrom
                    && task.getPlanTo() == planTo
                    && task.getAssign().equalsIgnoreCase(assign)
                    && task.getReview().equalsIgnoreCase(review)) {
                return true;
            }
        }
        return false;
    }
// kiểm tra xem người này đã làm việc trên 8 tiếng chưa


// kiểm tra xem ai là người làm nhiều nhất trong một ngày
    public int addTask(int TaskType, String requirementName, Date date, double planFrom, double planTo, String assign, String review) throws Exception {
        if (isDupplicate(TaskType, requirementName, date, planFrom, planTo, assign, review)) {
            throw new Exception("The task is exsited!!");
        }

        Task newTask = new Task(++lastId, TaskType, requirementName, date, planFrom, planTo, assign, review);
        list.add(newTask);
        return newTask.getiD();
    }

    private int getIndexById(int ID) throws Exception {
        if (list.isEmpty()) {
            throw new Exception("List is Empty");
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getiD() == ID) {
                return i;
            }
        }
        return -1;
    }

    public Task deleteTask(int ID) throws Exception {
        int index = getIndexById(ID);
        if (index == -1) {
            throw new Exception("Can not found to delete!!");
        }
        return list.remove(index);
    }

 

    @Override
    public String toString() {
        if (list.isEmpty()) {
            return null;
        }
        String str = String.format("%-5s%-15s%-15s%-15s%-15s%-15s%-15s%n",
                "ID", "Name", "Task Type", "Date", "Time", "Assignee", "Reviewer");
        for (Task task : list) {
            str += task.toString();
        }
        return str;
    }
}
// không thay đổi được nếu không sẽ in ra NullPointerException xảy ra khi gọi method hoặc truy cập thuộc tính của object đang là null
