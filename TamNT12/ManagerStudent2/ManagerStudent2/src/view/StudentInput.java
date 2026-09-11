/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import java.util.HashMap;
import model.Course;
import model.Enrollment;
import model.Student;

/**
 *
 * @author Admin
 */
public class StudentInput {

    public int showMenu() {
        System.out.println("WELCOME TO STUDENT MANAGEMENT\n"
                + "1.	Create\n"
                + "2.	Find and Sort\n"
                + "3.	Update/Delete\n"
                + "4.	Report\n"
                + "5.	Exit");
        return Validator.getInt("Enter your choice: ", "Just 1-5!!!","Invalid!!" , 1, 5);
    }// trả về menu chính

    public String getInputId() {
        return Validator.getString("Enter ID: ", "Invalid!!", "[Ss]\\d+").toUpperCase();
    }// lấy trong createStudent và updateOrDelete

    public String getInputName() {
        return Validator.getString("Enter Name: ", "Invalid", "[A-Za-z\\s]+");
    }

    public String getInputSemester() {
        return Validator.getString("Enter Semester: ", "Invalid", "(Spring|Summer|Fall)\\d{4}");
    }// createStudent , updateOrDelete

    public Course getInputCourse() {
        int choice = Validator.getInt("1. Java\n"
                + "2. .Net\n"
                + "3. C/C++\n"
                + "Enter your choice: ", "Just 1-3", "Invalid", 1, 3);
        return Course.getCourse(choice);// create và updateOrDelete
    }

    public boolean askToCountinue() {
        String choice = Validator.getString("Do you want to countinue(Y or N): ", "Just yes or no", "[YNyn]");
        return choice.equalsIgnoreCase("Y");
    }// gọi đến để hiển thị có tiếp tục nhập không hay out, createStudent

    public String enterSearchName() {
        System.out.println("Enter name student to search: ");
        return Validator.SCANNER.nextLine();
    }// find and sort

    public String askUpdateOrDelete() {
        return Validator.getString("Do you want Update or Delete", "Just yes or no", "[UDud]");
    }// update or delete

    public int getEnrollmentChoice(Student student) {
        System.out.println("Enrollment of " + student.getName() + ":");
        System.out.format("|%5s|%15s|%10s|\n", ".No", "Semester", "Course");
        ArrayList<Enrollment> list = student.getEnrollments();
        for (int i = 0; i < list.size(); i++) {
            Enrollment e = list.get(i);
            System.out.format("|%5s|%15s|%10s|\n", i + 1, e.getSemester(), e.getCourse().getLanguage());
        }
        return Validator.getInt("Enter record your choice: ", "Invalid", "Invalid", 1, list.size());
    }// trả về trong updateordelete để biết thao tác với enrollment nào

    public void displayStudent(ArrayList<Student> list) {
        if (list.isEmpty()) {
            System.out.println("List is empty");
            return;
        }
        System.out.format("|%5s|%20s|%10s|%15s|\n", ".No", "Student", "Semester", "Course");
        int count = 1;
        for (Student student : list) {
            for (Enrollment e : student.getEnrollments()) {
                System.out.format("|%5s|%20s|%10s|%15s|\n", count++, student.getName(), e.getSemester(), e.getCourse().getLanguage());
            }
        }
    }// find and sort

    public void displayReport(HashMap<String, Integer> report) {
        if (report.isEmpty()) {
            System.out.println("List is empty");
            return;
        }
        System.out.format("|%5s|%20s|%10s|%15s|\n", ".No", "Student", "Course", "Total");
        int count = 1;
        for (String key : report.keySet()) {
            String part[] = key.split("#");
            System.out.format("|%5s|%20s|%10s|%15s|\n", count++, part[1], part[2], report.get(key));
        }
    }// in ra tổng số report

    public void showMessage(String message) {
        System.out.println(message);
    }
}
