package model;

import common.Constants;

/**
 * Ứng viên có kinh nghiệm làm việc.
 *
 * @author LinhNDM
 */
public class ExperienceCandidate extends Candidate {

    private int expInYear;
    private String proSkill;

    /**
     * Khởi tạo ứng viên kinh nghiệm rỗng.
     */
    public ExperienceCandidate() {
        this.candidateType = Constants.TYPE_EXPERIENCE;
    }

    /**
     * Khởi tạo ứng viên kinh nghiệm với đầy đủ thông tin.
     *
     * @param candidateId mã ứng viên
     * @param firstName tên
     * @param lastName họ
     * @param birthDate năm sinh
     * @param address địa chỉ
     * @param phone số điện thoại
     * @param email email
     * @param expInYear số năm kinh nghiệm
     * @param proSkill kỹ năng chuyên môn
     */
    public ExperienceCandidate(String candidateId, String firstName,
            String lastName, int birthDate, String address, String phone,
            String email, int expInYear, String proSkill) {
        super(candidateId, firstName, lastName, birthDate, address, phone,
                email, Constants.TYPE_EXPERIENCE);
        this.expInYear = expInYear;
        this.proSkill = proSkill;
    }

    public int getExpInYear() {
        return expInYear;
    }

    public void setExpInYear(int expInYear) {
        this.expInYear = expInYear;
    }

    public String getProSkill() {
        return proSkill;
    }

    public void setProSkill(String proSkill) {
        this.proSkill = proSkill;
    }

    /**
     * Trả về chuỗi mô tả ứng viên kinh nghiệm.
     *
     * @return chuỗi hiển thị thông tin ứng viên
     */
    @Override
    public String toString() {
        return super.toString()
                + String.format(" | Exp: %d year(s) | Skill: %s",
                        expInYear, proSkill);
    }
}
