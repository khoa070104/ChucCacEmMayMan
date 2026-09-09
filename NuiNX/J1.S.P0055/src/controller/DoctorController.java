package controller;

import bo.DoctorManager;

import entity.Doctor;

import utils.Validator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.Scanner;

/**
 * Lớp DoctorController dùng để xử lý các chức năng quản lý bác sĩ.
 *
 */
public class DoctorController {

    // Đối tượng quản lý danh sách bác sĩ.
    private DoctorManager doctorManager;

    // Đường dẫn file lưu dữ liệu.
    private static final String FILE_PATH = "doctor.dat";

    private Scanner sc = new Scanner(System.in);

    /**
     * Khởi tạo đối tượng DoctorController.
     */
    public DoctorController() {
        doctorManager = new DoctorManager();
    }

    /**
     * Thêm bác sĩ mới vào hệ thống.
     */
    public void addDoctor() {
        // Nhập thông tin bác sĩ.
        String code =
                Validator.getString("Enter Code: ", "Must be DOC x ( x is digit)", "DOC\\s\\d+");
        String name = Validator.getString("Enter Name: ", "Cannot be empty", "^(?!\\s*$).+");
        String specialization =
                Validator.getString("Enter Specialization: ", "Cannot be empty", "^(?!\\s*$).+");
        int availability =
                Validator.getInt(
                        "Enter Availability: ",
                        "Availability must be non-negative!",
                        "Invalid input, please enter a number!",
                        0,
                        Integer.MAX_VALUE);

        try {
            // Tạo đối tượng bác sĩ mới.
            Doctor doctor = new Doctor(code, name, specialization, availability);

            // Thêm bác sĩ vào danh sách.
            if (doctorManager.addDoctor(doctor)) {
                System.out.println("Doctor added successfully:");

                displayAllDoctors(); // NEW 23/6

            } else {
                System.out.println("Add failed!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Cập nhật thông tin bác sĩ.
     */
    public void updateDoctor() {
        // Nhập mã bác sĩ cần cập nhật.
        String code =
                Validator.getString("Enter Code: ", "Must be DOC x ( x is digit)", "DOC\\s\\d+");

        try {
            // Kiểm tra mã bác sĩ có tồn tại hay không.
            if (!doctorManager.getDoctorMap().containsKey(code)) {
                throw new Exception("Doctor code doesn’t exist.");
            }

            // UPADATE NEW 18H31 23/6
            // -	If user chooses 2: then request enter the code.
            // If it does not exist Code, the notification "Doctor code does not exist".
            // Otherwiseuser can edit of the remaining information. If Information
            // is blank then not change old information.
            // Lấy thông tin bác sĩ hiện tại
            Doctor oldDoctor = doctorManager.getDoctorMap().get(code);

            System.out.println("Before update:");

            System.out.printf(
                    "%-10s %-20s %-20s %-10s\n", "Code", "Name", "Specialization", "Availability");

            System.out.printf(
                    "%-10s %-20s %-20s %-10d\n",
                    oldDoctor.getCode(),
                    oldDoctor.getName(),
                    oldDoctor.getSpecialization(),
                    oldDoctor.getAvailability());

            // Nhập thông tin mới
            String name = Validator.getOptionalString("Enter Name: ");

            String specialization = Validator.getOptionalString("Enter Specialization: ");

            Integer availabilityInput =
                    Validator.getOptionalInt(
                            "Enter Availability: ",
                            "Availability must be non-negative!",
                            "Invalid input, please enter a number!",
                            0,
                            Integer.MAX_VALUE);

            // Nếu để trống thì giữ tên cũ
            if (name.isEmpty()) {
                name = oldDoctor.getName();
            }

            // Nếu để trống thì giữ chuyên môn cũ
            if (specialization.isEmpty()) {
                specialization = oldDoctor.getSpecialization();
            }

            // Nếu để trống thì giữ availability cũ
            int availability;

            if (availabilityInput == null) {
                availability = oldDoctor.getAvailability();
            } else {
                availability = availabilityInput;
            }
            // END UPDATE 23/6 If user chooses 2.

            // Tạo đối tượng bác sĩ với thông tin mới.
            Doctor doctor = new Doctor(code, name, specialization, availability);

            // Cập nhật thông tin bác sĩ.
            if (doctorManager.updateDoctor(doctor)) {
                System.out.println("Doctor updated successfully!");
                System.out.println("After update:");

                // NEW 23/6
                Doctor updatedDoctor = doctorManager.getDoctorMap().get(code);
                System.out.printf(
                        "%-10s %-20s %-20s %-10s\n",
                        "Code", "Name", "Specialization", "Availability");

                System.out.printf(
                        "%-10s %-20s %-20s %-10d\n",
                        updatedDoctor.getCode(),
                        updatedDoctor.getName(),
                        updatedDoctor.getSpecialization(),
                        updatedDoctor.getAvailability());
                // END NEW 23/6

            } else {
                System.out.println("Update failed!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Xóa bác sĩ khỏi hệ thống.
     */
    public void deleteDoctor() {
        // Nhập mã bác sĩ cần xóa.
        String code =
                Validator.getString("Enter code: ", "Must be DOC x ( x is digit)", "DOC\\s\\d+");

        try {
            Doctor doctor = doctorManager.getDoctorMap().get(code);

            // NEW 23/6
            if (doctor == null) {
                throw new Exception("Doctor code does not exist.");
            }

            System.out.println("Doctor information:");
            System.out.printf(
                    "%-10s %-20s %-20s %-10s\n", "Code", "Name", "Specialization", "Availability");
            System.out.printf(
                    "%-10s %-20s %-20s %-10d\n",
                    doctor.getCode(),
                    doctor.getName(),
                    doctor.getSpecialization(),
                    doctor.getAvailability());
            // END NEW 23/6

            // Thực hiện xóa bác sĩ.
            if (doctorManager.deleteDoctor(code)) {
                //                System.out.println(code.toString());
                System.out.println("Doctor deleted successfully!");
            } else {
                System.out.println("Delete failed.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Tìm kiếm bác sĩ theo từ khóa.
     */
    public void searchDoctor() {
        // Nhập từ khóa tìm kiếm.
        String input = Validator.getString("Enter name: ", "Cannot be empty", "^(?!\\s*$).+");

        // Lấy kết quả tìm kiếm.
        Map<String, Doctor> result;
        try {
            result = doctorManager.searchDoctor(input);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return;
        }

        // Hiển thị kết quả tìm kiếm.
        if (result == null) {
            System.out.println("No matching doctors found.");
        } else {
            DoctorManager manager = new DoctorManager();
            System.out.println("-------- Result --------"); // NEW 18H31 23/6
            manager.setDoctorMap(result);
            System.out.println(manager.toString());
        }
    }

    /**
     * Hiển thị toàn bộ danh sách bác sĩ.
     */
    public void displayAllDoctors() {
        // Lấy danh sách bác sĩ dưới dạng chuỗi.
        String str = doctorManager.toString();

        // Hiển thị danh sách bác sĩ.
        if (str == null) {
            System.out.println("This list is empty!");
        } else {
            System.out.println(str);
        }
    }

    /**
     * Đọc dữ liệu bác sĩ từ file.
     */
    @SuppressWarnings("unchecked")
    public void loadFile() {
        // Tạo đối tượng file dữ liệu.
        File file = new File(FILE_PATH);
        Map<String, Doctor> result;

        // Kiểm tra file có tồn tại hay không.
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                // Đọc dữ liệu từ file.
                result = (Map<String, Doctor>) ois.readObject();

                // Cập nhật dữ liệu vào hệ thống.
                doctorManager.setDoctorMap(result);
                System.out.println("Load file doctor.dat successful!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Lưu dữ liệu bác sĩ vào file.
     */
    public void saveFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {

            // Ghi danh sách bác sĩ xuống file.
            oos.writeObject(doctorManager.getDoctorMap());
            System.out.println("Save file doctor.dat successful!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
