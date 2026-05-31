package model;

import common.Constants;

/**
 * Ứng viên mới tốt nghiệp.
 *
 * @author LinhNDM
 */
public class FresherCandidate extends Candidate {

    private int graduationDate;
    private String graduationRank;
    private String education;

    /**
     * Khởi tạo ứng viên fresher rỗng.
     */
    public FresherCandidate() {
        this.candidateType = Constants.TYPE_FRESHER;
    }

    /**
     * Khởi tạo ứng viên fresher với đầy đủ thông tin.
     *
     * @param candidateId mã ứng viên
     * @param firstName tên
     * @param lastName họ
     * @param birthDate năm sinh
     * @param address địa chỉ
     * @param phone số điện thoại
     * @param email email
     * @param graduationDate năm tốt nghiệp
     * @param graduationRank xếp loại tốt nghiệp
     * @param education trường đại học
     */
    public FresherCandidate(String candidateId, String firstName, String lastName,
            int birthDate, String address, String phone, String email,
            int graduationDate, String graduationRank, String education) {
        super(candidateId, firstName, lastName, birthDate, address, phone,
                email, Constants.TYPE_FRESHER);
        this.graduationDate = graduationDate;
        this.graduationRank = graduationRank;
        this.education = education;
    }

    public int getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(int graduationDate) {
        this.graduationDate = graduationDate;
    }

    public String getGraduationRank() {
        return graduationRank;
    }

    public void setGraduationRank(String graduationRank) {
        this.graduationRank = graduationRank;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    /**
     * Trả về chuỗi mô tả ứng viên fresher.
     *
     * @return chuỗi hiển thị thông tin ứng viên
     */
    @Override
    public String toString() {
        return super.toString()
                + String.format(" | Grad: %d | Rank: %s | Education: %s",
                        graduationDate, graduationRank, education);
    }
}
