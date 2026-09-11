/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import utils.Validator;

/**
 *
 * @author win
 */
public class Worker implements Serializable {

    private String id;
    private String name;
    private int age;
    private double salary;
    private String workLocation;

    public Worker() {
    }

    public Worker(String id, String name, int age, double salary, String workLocation) {
        setId(id);
        setName(name);
        setAge(age);
        setSalary(salary);
        setWorkLocation(workLocation);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        // Kiểm tra id có rỗng hoặc không đúng định dạng không
        if (id == null || !id.matches("[Ww]\\d+")) {
            throw new IllegalArgumentException("Invalid ID. ID must start with 'W' or 'w' followed by numbers.");
        }
        this.id = id.toUpperCase();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        // Kiểm tra tên có rỗng hoặc không đúng định dạng không
        if (name == null || !name.matches("[A-Za-z\\s]+")) {
            throw new IllegalArgumentException("Invalid Name. Name must contain only letters and spaces.");
        }
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        // Kiểm tra tuổi có nằm trong khoảng hợp lệ không
        if (age < 18 || age > 50) {
            throw new IllegalArgumentException("Invalid Age. Age must be between 18 and 50.");
        }
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        // Kiểm tra lương có lớn hơn 0 không
        if (salary <= 0) {
            throw new IllegalArgumentException("Invalid Salary. Salary must be a positive number.");
        }
        this.salary = salary;
    }

    public String getWorkLocation() {
        return workLocation;
    }

    public void setWorkLocation(String workLocation) {
        // Kiểm tra địa điểm làm việc có rỗng hoặc không đúng định dạng không
        if (workLocation == null || !workLocation.matches("[A-Za-z0-9\\s]+")) {
            throw new IllegalArgumentException("Invalid Work Location. Work location must contain only letters, numbers, and spaces.");
        }
        this.workLocation = workLocation;
    }

    @Override
    public String toString() {
        return String.format("%7s%10s%10d%10.0f%15s\n", getId(), getName(),
                getAge(), getSalary(), getWorkLocation());
    }

    public void input() {
        // Các hàm setter giờ đây tự xử lý việc kiểm tra dữ liệu,
        // nên logic trong phương thức input() có thể đơn giản hơn
        this.setId(Validator.getString("Enter id: ", "Invalid!", "[Ww]\\d+").toUpperCase());
        this.setName(Validator.getString("Enter Name: ", "Invalid!", "[A-Za-z\\s]+"));
        this.setAge(Validator.getInt("Enter age: ", "age >= 18 and <=50 !", "Invalid!", 18, 50));
        this.setSalary(Validator.getDouble("Enter Salary: ", "salary must be > 0", "Invalid!", 0, Double.MAX_VALUE));
        this.setWorkLocation(Validator.getString("Enter work location: ", "Invalid!", "[A-Za-z0-9\\s]+"));
    }
    
}