package model;

import common.Constants;

/**
 * Ứng viên thực tập sinh.
 *
 * @author LinhNDM
 */
public class InternCandidate extends Candidate {

    private String majors;
    private String semester;
    private String universityName;

    /**
     * Khởi tạo ứng viên intern rỗng.
     */
    public InternCandidate() {
        this.candidateType = Constants.TYPE_INTERN;
    }

    /**
     * Khởi tạo ứng viên intern với đầy đủ thông tin.
     *
     * @param candidateId mã ứng viên
     * @param firstName tên
     * @param lastName họ
     * @param birthDate năm sinh
     * @param address địa chỉ
     * @param phone số điện thoại
     * @param email email
     * @param majors chuyên ngành
     * @param semester học kỳ
     * @param universityName tên trường đại học
     */
    public InternCandidate(String candidateId, String firstName, String lastName,
            int birthDate, String address, String phone, String email,
            String majors, String semester, String universityName) {
        super(candidateId, firstName, lastName, birthDate, address, phone,
                email, Constants.TYPE_INTERN);
        this.majors = majors;
        this.semester = semester;
        this.universityName = universityName;
    }

    public String getMajors() {
        return majors;
    }

    public void setMajors(String majors) {
        this.majors = majors;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    /**
     * Trả về chuỗi mô tả ứng viên intern.
     *
     * @return chuỗi hiển thị thông tin ứng viên
     */
    @Override
    public String toString() {
        return super.toString()
                + String.format(" | Majors: %s | Semester: %s | University: %s",
                        majors, semester, universityName);
    }
}
