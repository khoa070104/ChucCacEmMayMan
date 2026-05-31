package main;

import controller.StudentController;

/**
 * Điểm khởi đầu của chương trình quản lý sinh viên.
 *
 * @author LinhNDM
 */
public class Main {

    /**
     * Khởi chạy ứng dụng quản lý sinh viên.
     *
     * @param args tham số dòng lệnh
     */
    public static void main(String[] args) {
        StudentController studentController;
        studentController = new StudentController();
        studentController.run();
    }
}
