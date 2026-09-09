package entity;

import java.io.Serializable;

/**
 * Lớp Doctor dùng để lưu thông tin của một bác sĩ.
 *
 */
public class Doctor implements Serializable {

    private static final long serialVersionUID = 1L;

    // Ma bac si.
    private String code;

    // Ten bac si.
    private String name;

    // Chuyen mon cua bac si.
    private String specialization;

    // So ca truc.
    private int availability;

    /**
     * Khởi tạo đối tượng bác sĩ.
     *
     * @param code Mã bác sĩ.
     * @param name Tên bác sĩ.
     * @param specialization Chuyên mon.
     * @param availability Số ca trực.
     */
    public Doctor(String code, String name, String specialization, int availability) {
        this.code = code;
        this.name = name;
        this.specialization = specialization;
        this.availability = availability;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    /**
     * Trả về thông tin bác sĩ dưới dạng chuỗi.
     *
     * @return thông tin bác sĩ
     */
    @Override
    public String toString() {
        return "Doctor{" + code + ", " + name + ", " + specialization + ", " + availability + '}';
    }
}
