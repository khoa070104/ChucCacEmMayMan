package model;

import constants.CourseType;

// Moi doi tuong Student la mot ban ghi day du theo dung yeu cau cua de bai
public class Student {

    // Ma sinh vien
    private String id;

    // Ten sinh vien
    private String name;

    // Hoc ky
    private String semester;

    // Mon hoc
    private CourseType course;

    // Constructor khoi tao sinh vien
    public Student(String id, String name, String semester, CourseType course) {

        // Gan id
        this.id = id;

        // Gan ten
        this.name = name;

        this.semester = semester;
        this.course = course;
    }

    // Getter lay id
    public String getId() {
        return id;
    }

    // Getter lay ten
    public String getName() {
        return name;
    }
   
    public String getSemester() {
        return semester;
    }

    public CourseType getCourse() {
        return course;
    }

    // Cap nhat thong tin cua ban ghi hien tai
    public void updateInfo(String newName, String newSemester, CourseType newCourse) {
        this.name = newName;
        this.semester = newSemester;
        this.course = newCourse;
    }
}
