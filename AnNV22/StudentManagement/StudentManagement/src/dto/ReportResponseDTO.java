package dto;

// DTO dung de hien thi ket qua report
public class ReportResponseDTO {

    // Ten sinh vien
    private String studentName;

    // Ten mon hoc
    private String course;

    // Hoc ky, chi dung cho ket qua tim kiem
    private String semester;

    // So lan hoc mon do
    private int total;

    // Constructor khoi tao du lieu report
    public ReportResponseDTO(String studentName, String course, int total) {
        this.studentName = studentName;
        this.course = course;
        this.total = total;
    }

    // Constructor dung cho chuc nang find and sort
    public ReportResponseDTO(String studentName, String semester, String course) {
        this.studentName = studentName;
        this.semester = semester;
        this.course = course;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getCourse() {
        return course;
    }

    public String getSemester() {
        return semester;
    }

    public int getTotal() {
        return total;
    }

    // Ghi de toString de hien thi dung format
    @Override
    public String toString() {
        if (semester != null) {
            return studentName + " | " + semester + " | " + course;
        }
        return studentName + " | " + course + " | " + total;
    }
}
