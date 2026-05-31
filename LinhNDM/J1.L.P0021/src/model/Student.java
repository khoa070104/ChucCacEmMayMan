package model;

/**
 * Lưu trữ thông tin một sinh viên.
 *
 * @author LinhNDM
 */
public class Student {

    private String id;
    private String name;
    private String semester;
    private String course;

    /**
     * Khởi tạo sinh viên với đầy đủ thông tin.
     *
     * @param id mã sinh viên
     * @param name tên sinh viên
     * @param semester học kỳ
     * @param course khóa học
     */
    public Student(String id, String name, String semester, String course) {
        this.id = id;
        this.name = name;
        this.semester = semester;
        this.course = course;
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

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    /**
     * Trả về chuỗi mô tả sinh viên theo định dạng bảng.
     *
     * @return chuỗi hiển thị thông tin sinh viên
     */
    @Override
    public String toString() {
        return String.format("%-10s | %-20s | %-10s | %-15s",
                id, name, semester, course);
    }
}
