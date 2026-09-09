package ui;

import controller.AccountController;

/**
 * Lớp chính dùng để khởi chạy chuong trinh AccountManager.
 */
public class Main {
    public static void main(String[] args) {
        // Tạo đối tượng điều khiển chương trình
        AccountController controller = new AccountController();

        // Gọi hàm chạy menu chính của chuong trinh.
        controller.run();
    }
}
