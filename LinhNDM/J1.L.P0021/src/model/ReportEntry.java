package model;

/**
 * Dữ liệu một dòng trong báo cáo thống kê sinh viên theo khóa học.
 *
 * @author LinhNDM
 */
public class ReportEntry {

    private String name;
    private String course;
    private int count;

    /**
     * Khởi tạo một dòng báo cáo.
     *
     * @param name tên sinh viên
     * @param course tên khóa học
     * @param count số lần đăng ký
     */
    public ReportEntry(String name, String course, int count) {
        this.name = name;
        this.course = course;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
