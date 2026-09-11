/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import model.Course;
import model.Enrollment;
import model.Student;
import view.Validator;
import view.StudentInput;

/**
 *
 * @author Admin
 */
public class Controller {

    private StudentInput input = new StudentInput();
    private final ArrayList<Student> list = new ArrayList<>();

    private Student getStudentById(String id) {
        for (Student student : list) {
            if (student.getId().equalsIgnoreCase(id)) {
                return student;
            }
        }
        return null;
    }

    private ArrayList<Student> getStudentByName(String name) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student student : list) {
            if (student.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(student);
            }
        }
        return result;
    }

    private boolean isEnrollmentDuplicated(String id, String semester, Course course) {
        Student s = getStudentById(id);
        if (s != null) {// nếu ko rỗng thì là id name đã có tiến hành kiểm tra enrollment
            for (Enrollment e : s.getEnrollments()) {
                if (e.getSemester().equalsIgnoreCase(semester) && e.getCourse() == course) {
                    return true;
                }
            }
        }
        return false;
    }

    private void create(String id, String name, String semester, Course course) throws Exception {
        Student s = getStudentById(id);
        if (s != null) {
            if (isEnrollmentDuplicated(id, semester, course)) {
                throw new Exception("The record already exsited!!");// toàn bộ id, name, semester, course đều trùng
            }
            Enrollment e = new Enrollment(semester, course);
            s.addEnrollment(e);
            return;
        }
        Student newStudent = new Student(id, name);
        newStudent.addEnrollment(new Enrollment(semester, course));
        list.add(newStudent);
    }
//isEnrollmentDuplicated method này để kiểm tra xem trong student đã có enrollment này chưa phục vụ cho create, nếu khi nhập id vào getStudentById 
//lưu kết quả vào s mà trả về không null tức là id đã tồn tại, id tồn tại chắc chắn name tồn tại thì chỉ tiến hành kiểm tra enrollment 
//của student đó thôi, nếu enrollment có sự trùng lặp trả về true thông qua method isEnrollmentDuplicated thì tức là object student này đã có trong list nên ném ra exception là This enrollment already exists for this student

//còn nếu isEnrollmentDuplicated mà trả về false tức là enrollment không trùng thì add enrollment mới nhập vào existingStudent, nếu existingStudent == null
//tức là không có id nên không có name không có một cái gì cả thì nhập và add vào tất từ id name semester course
    public void createStudent() {
        boolean cont;
        do {
          String id = input.getInputId();
            Student student = getStudentById(id);
            String name;
            if (student == null) {
                name = input.getInputName();
            } else {
                name = student.getName();
                input.showMessage("Name of student (auto-filled): " + name);
            }
            String semester = input.getInputSemester();
            Course course = input.getInputCourse();
            try {
                create(id, name, semester, course);
                input.showMessage("Add sucessfull!!");
            } catch (Exception e) {
                input.showMessage("Add failed: " + e.getMessage());
            }
            cont = input.askToCountinue();
        } while (cont);
    }

    public void findAndSort() {
        String name = input.enterSearchName();
        ArrayList<Student> result = getStudentByName(name);
        if (result.isEmpty()) {
            input.showMessage("Can not found!!");
            return;
        }
        Collections.sort(result);
        input.displayStudent(result);
    }

    public void updateOrDelete() {
        String id = input.getInputId();
        Student student = getStudentById(id);
        if (student == null || student.getEnrollments().isEmpty()) {
            input.showMessage("The student can not exsited or enrollments is empty!!");
            return;
        }
        String action = input.askUpdateOrDelete();
        int choice = input.getEnrollmentChoice(student);
        if (action.equalsIgnoreCase("U")) {
            String semester = input.getInputSemester();
            Course course = input.getInputCourse();
            if (isEnrollmentDuplicated(id, semester, course)) {
                input.showMessage("The student is dupplicate!! Update failed!!");
                return;
            }
            input.showMessage("Name: " + student.getName());
            String choiceUpdateName = Validator.getString("Do you want to update name: ", "Just yes or no", "[YNyn]");
            if (choiceUpdateName.equalsIgnoreCase("Y")) {
                String newName = input.getInputName();
                student.setName(newName);
            }
            Enrollment e = student.getEnrollments().get(choice - 1);
            e.setSemester(semester);
            e.setCourse(course);
            input.showMessage("Update successful");
        } else if (action.equalsIgnoreCase("D")) {
            student.getEnrollments().remove(choice - 1);
            input.showMessage("Delete successfull");
            if (student.getEnrollments().isEmpty()) {
                list.remove(student);
                input.showMessage("Student " + student.getName() + " has been removed from list");
            }
        }
    }

    private HashMap<String, Integer> generateReport() {
        HashMap<String, Integer> report = new HashMap<>();
        for (Student student : list) {
            for (Enrollment e : student.getEnrollments()) {
                String key = student.getId() + "#" + student.getName() + "#" + e.getSemester();
                report.put(key, report.getOrDefault(key, 0) + 1);
            }
        }
        return report;
    }

    public void report() throws Exception {
        if (list.isEmpty()) {
            throw new Exception("List is empty");
        }
        input.displayReport(generateReport());
    }

    public void generateStudent() {
        try {
            create("S1", "Tran Quoc Tuan ", "Fall2022", Course.JAVA);
            create("S1", "Tran Quoc Tuan", "Summer2022", Course.DOT_NET);
            create("S1", "Tran Quoc Tuan", "Fall2022", Course.DOT_NET);
            create("S2", "Nguyen Duy Huy", "Fall2021", Course.JAVA);
            create("S2", "Nguyen Anh Huy", "Spring2022", Course.JAVA);
            create("S3", "Le Thi Hoa", "Fall2022", Course.DOT_NET);
            create("S4", "Le Thi Quynh", "Fall2022", Course.JAVA);
            create("S5", "Le Trong Nhat", "Fall2022", Course.DOT_NET);
            create("S5", "Le Trong Nhat", "Summer2022", Course.JAVA);
            create("S6", "Le Trong Nhat", "Fall2022", Course.JAVA);
        } catch (Exception e) {
        }
    }
}
