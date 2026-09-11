/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Student implements Comparable<Student> {

    private String id;
    private String name;
    private ArrayList<Enrollment> enrollments;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.enrollments = new ArrayList<>();
    }
// parameter chính là dữ liệu bắt buộc phải có khi khởi tạo một đối tượng,tức là nếu ko có id name thì sinh viên ko tồn tại còn enrollment thì không cần thiết mà chỉ được khởi tạo
//trong thân constructor thôi để sẵn sàng chứa dữ liệu nhập vào sau này

    public Student() {
        this.enrollments = new ArrayList<>();
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

    public ArrayList<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(ArrayList<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public void addEnrollment(Enrollment e) {
        this.enrollments.add(e);
    }// thêm enrollments

    private String extractLastName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            return "";
        }
        String part[] = fullName.trim().split("\\s+");
        return part[part.length - 1];
    }

    @Override
    public int compareTo(Student o) {
        String name1 = this.getName().trim();
        String name2 = o.getName().trim();

        String lastName1 = extractLastName(name1);
        String lastName2 = extractLastName(name2);

        int compareName = lastName1.compareToIgnoreCase(lastName2);

        if (compareName == 0) {
            return name1.compareToIgnoreCase(name2);
        }
        return compareName;

    }
}
