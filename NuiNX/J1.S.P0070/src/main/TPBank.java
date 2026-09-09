package main;

import service.LoginService;

import utils.Helper;
import utils.Validate;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Lớp TPBank quản lý đa ngôn ngữ và khởi chạy chuong trinh.
 */
public class TPBank {

    /**
     * Khởi động chương trình: hiển thị menu, chọn ngôn ngữ và bắt đầu đăng
     * nhập.
     */
    public static void start() {
        // Hiển thị menu cho người dùng chọn
        Helper.menu();

        int choice = Validate.getInt("Please enter 1-3: ", "Just 1->3", "Invalid!", 1, 3);

        // Thiết lập ngôn ngữ theo lựa chọn
        if (choice == 1) {
            Locale.setDefault(Locale.forLanguageTag("vi-VN"));
        } else if (choice == 2) {
            Locale.setDefault(Locale.forLanguageTag("en-US"));
        } else {
            System.exit(0);
        }

        // Tải file ngôn ngữ và bắt đầu đăng nhập
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resources/Language");
        new LoginService().login(resourceBundle);
    }
}
