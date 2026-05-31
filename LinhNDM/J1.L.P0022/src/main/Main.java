package main;

import controller.CandidateController;

/**
 * Điểm khởi chạy chương trình quản lý ứng viên.
 *
 * @author LinhNDM
 */
public class Main {

    /**
     * Khởi chạy ứng dụng.
     *
     * @param args tham số dòng lệnh
     */
    public static void main(String[] args) {
        CandidateController controller;
        controller = new CandidateController();
        controller.run();
    }
}
