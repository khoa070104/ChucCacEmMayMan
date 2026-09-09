package ui;

import controller.DoctorController;

import utils.Validator;

/**
 * Lớp Main dùng để chạy chương trình quản lý bác sĩ.
 *
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Tạo đối tượng điều khiển chương trình.
        DoctorController doctorController = new DoctorController();

        // Đọc dữ liệu từ file.
        //        doctorController.loadFile();

        int choice;
        do {
            // Hiển thị menu chức năng.
            System.out.println("========= Doctor Management =========");
            System.out.println("1. Add Doctor");
            System.out.println("2. Update Doctor");
            System.out.println("3. Delete Doctor");
            System.out.println("4. Search Doctor");
            System.out.println("5. Save file and Exit");

            // Nhập lựa chọn từ người dùng.
            choice =
                    Validator.getInt(
                            "Please choose one option: ",
                            "Out of range, choose again!",
                            "Invalid input, please enter a number!",
                            1,
                            5);

            // Thực hiện chức năng tương ứng.
            switch (choice) {
                case 1:
                    doctorController.addDoctor();
                    break;
                case 2:
                    doctorController.updateDoctor();
                    break;
                case 3:
                    doctorController.deleteDoctor();
                    break;
                case 4:
                    doctorController.searchDoctor();
                    break;
                case 5:
                    //                    doctorController.saveFile();
                    System.out.println("Exiting program...");
                    break;
            }
            // Lặp lại menu cho đến khi chọn thoát chương trình.
        } while (choice != 5);
    }
}
