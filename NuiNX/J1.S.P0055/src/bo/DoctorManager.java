package bo;

import entity.Doctor;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Lớp DoctorManager dùng để quản lý danh sách bác sĩ.
 *
 */
public class DoctorManager {

    // Lưu danh sách bác sĩ theo mã bác sĩ.
    private Map<String, Doctor> doctorMap;

    /**
     * Khởi tạo danh sách bác sĩ rỗng.
     */
    public DoctorManager() {
        doctorMap = new HashMap<>();
    }

    /**
     * Lấy danh sách bác sĩ.
     *
     * @return danh sách bác sĩ
     */
    public Map<String, Doctor> getDoctorMap() {
        return doctorMap;
    }

    /**
     * Cập nhật danh sách bác sĩ.
     *
     * @param doctorMap danh sách bác sĩ mới
     */
    public void setDoctorMap(Map<String, Doctor> doctorMap) {
        this.doctorMap = doctorMap;
    }

    /**
     * Thêm bác sĩ vào danh sách.
     *
     * @param doctor thông tin bác sĩ
     * @return true nếu thêm thành công
     * @throws Exception nếu mã bác sĩ bị trùng
     */
    public boolean addDoctor(Doctor doctor) throws Exception {
        if (doctor == null || doctor.getCode() == null || doctor.getCode().trim().isEmpty()) {
            throw new Exception("Data does not exist.");
        }
        // Kiểm tra mã bác sĩ đã tồn tại hay chưa.
        if (doctorMap.containsKey(doctor.getCode())) {
            throw new Exception("Doctor code [" + doctor.getCode() + "] is duplicate.");
        }
        // Thêm bác sĩ vào danh sách.
        doctorMap.put(doctor.getCode(), doctor);
        return true;
    }

    /**
     * Cập nhật thông tin bác sĩ.
     *
     * @param doctor thông tin bác sĩ mới
     * @return true nếu cập nhật thành công
     * @throws Exception nếu mã bác sĩ không tồn tại
     */
    public boolean updateDoctor(Doctor doctor) throws Exception {
        if (doctor == null || doctor.getCode() == null || doctor.getCode().trim().isEmpty()) {
            throw new Exception("Data does not exist.");
        }
        // Kiểm tra mã bác sĩ có tồn tại hay không.
        if (!doctorMap.containsKey(doctor.getCode())) {
            throw new Exception("Doctor code doesn’t exist.");
        }
        // Cập nhật thông tin bác sĩ.
        doctorMap.put(doctor.getCode(), doctor);
        return true;
    }

    /**
     * Xóa bác sĩ theo mã.
     *
     * @param code mã bác sĩ
     * @return true nếu xóa thành công
     * @throws Exception nếu mã bác sĩ không tồn tại
     */
    public boolean deleteDoctor(String code) throws Exception {
        // Kiểm tra mã bác sĩ có tồn tại hay không.
        if (!doctorMap.containsKey(code)) {
            throw new Exception("Doctor code doesn’t exist.");
        }
        // Xóa bác sĩ khỏi danh sách.
        if (doctorMap.remove(code) != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Xóa bác sĩ theo đúng chữ ký phương thức trong đề bài.
     *
     * @param doctor bác sĩ cần xóa
     * @return true nếu xóa thành công
     * @throws Exception nếu dữ liệu hoặc mã bác sĩ không tồn tại
     */
    public boolean deleteDoctor(Doctor doctor) throws Exception {
        if (doctor == null) {
            throw new Exception("Data does not exist.");
        }
        return deleteDoctor(doctor.getCode());
    }

    /**
     * Tìm kiếm bác sĩ theo từ khóa.
     *
     * @param input từ khóa tìm kiếm
     * @return danh sách bác sĩ tìm thấy
     */
    public HashMap<String, Doctor> searchDoctor(String input) throws Exception {
        if (doctorMap == null) {
            throw new Exception("Database does not exist.");
        }
        // Lưu kết quả tìm kiếm.
        HashMap<String, Doctor> result = new HashMap<>();

        // Duyệt toàn bộ danh sách bác sĩ.
        for (Doctor doctor : doctorMap.values()) {

            // Kiểm tra từ khóa có xuất hiện trong thông tin bác sĩ hay không.
            if (doctor.getCode().toLowerCase().contains(input.toLowerCase())
                    || doctor.getName().toLowerCase().contains(input.toLowerCase())
                    || doctor.getSpecialization().toLowerCase().contains(input.toLowerCase())) {
                result.put(doctor.getCode(), doctor);
            }
        }
        // Trả về danh sách tìm được, nếu không có ai thì trả về null
        return result.isEmpty() ? null : result;
    }

    /**
     * Hiển thị danh sách bác sĩ dưới dạng bảng.
     *
     * @return chuỗi chứa danh sách bác sĩ
     */
    @Override
    public String toString() {
        // Kiểm tra danh sách có rỗng hay không.
        if (doctorMap.isEmpty()) {
            return null;
        }
        // Sắp xếp danh sách theo mã bác sĩ.
        Map<String, Doctor> sortedDoctorMap = new TreeMap<>(doctorMap);

        // Tạo tiêu đề bảng.
        String result =
                String.format(
                        "%-10s %-20s %-20s %-10s\n",
                        "Code", "Name", "Specialization", "Availability");

        // Thêm từng bác sĩ vào bảng.
        for (Doctor doctor : sortedDoctorMap.values()) {
            result +=
                    String.format(
                            "%-10s %-20s %-20s %-10d\n",
                            doctor.getCode(),
                            doctor.getName(),
                            doctor.getSpecialization(),
                            doctor.getAvailability());
        }
        return result;
    }
}
