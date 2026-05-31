package model;

/**
 * Lớp cha chứa thông tin chung của ứng viên.
 *
 * @author LinhNDM
 */
public abstract class Candidate {

    protected String candidateId;
    protected String firstName;
    protected String lastName;
    protected int birthDate;
    protected String address;
    protected String phone;
    protected String email;
    protected int candidateType;

    /**
     * Khởi tạo ứng viên rỗng.
     */
    public Candidate() {
    }

    /**
     * Khởi tạo ứng viên với đầy đủ thông tin chung.
     *
     * @param candidateId mã ứng viên
     * @param firstName tên
     * @param lastName họ
     * @param birthDate năm sinh
     * @param address địa chỉ
     * @param phone số điện thoại
     * @param email email
     * @param candidateType loại ứng viên
     */
    public Candidate(String candidateId, String firstName, String lastName,
            int birthDate, String address, String phone, String email,
            int candidateType) {
        this.candidateId = candidateId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.candidateType = candidateType;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(int birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCandidateType() {
        return candidateType;
    }

    public void setCandidateType(int candidateType) {
        this.candidateType = candidateType;
    }

    /**
     * Trả về họ và tên đầy đủ của ứng viên.
     *
     * @return họ tên đầy đủ
     */
    public String getFullName() {
        StringBuilder fullNameBuilder;
        fullNameBuilder = new StringBuilder();
        fullNameBuilder.append(lastName);
        fullNameBuilder.append(" ");
        fullNameBuilder.append(firstName);
        return fullNameBuilder.toString();
    }

    /**
     * Trả về chuỗi kết quả tìm kiếm của ứng viên.
     *
     * @return chuỗi hiển thị thông tin tìm kiếm
     */
    public String toSearchResult() {
        return String.format("%s | %d | %s | %s | %s | %d",
                getFullName(), birthDate, address, phone, email, candidateType);
    }

    /**
     * Trả về chuỗi mô tả ứng viên.
     *
     * @return chuỗi hiển thị thông tin ứng viên
     */
    @Override
    public String toString() {
        return toSearchResult();
    }
}
