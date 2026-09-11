/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Admin
 */
public class Enrollment {
// tách ra nếu gộp chung hết vào Student gồm 4 thuộc tính thì mỗi học sinh chỉ có học một môn vào một kì, nếu muốn thêm môn và kì khác cùng là một
// sinh viên phải tạo thêm object mới, khi muốn update thì chỉ việc update mỗi semester và course của enrollment thôi
    private String semester;
    private Course course;

    public Enrollment(String semester, Course course) {
        this.semester = semester;
        this.course = course;
    }
// có danh sách 10 thành viên trong controller rồi nên dùng có tham số
    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
//không tham số là một constructor rỗng và được truyền vào thông qua setter
//
}
