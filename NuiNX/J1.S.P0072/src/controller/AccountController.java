package controller;

import bo.AccountDAO;

import entity.Account;

import view.AccountView;

/**
 * Lớp AccountController điều phối luồng chạy chương trình, kết nối hiển thị
 * giữa View và dữ liệu DAO.
 */
public class AccountController {

    // Đối tượng quản lý và xử lý dữ liệu tài khoản
    private AccountDAO dao = new AccountDAO();

    // Đối tượng hiển thị giao diện cho người dùng
    private AccountView view = new AccountView();

    /**
     * Chạy chương trình chính với vòng lặp hiển thị menu.
     */
    public void run() {
        while (true) {
            int choice = view.menu();
            // Lựa chọn chức năng theo menu.
            switch (choice) {
                case 1:
                    // Thêm tài khoản mới.
                    try {
                        Account acc = view.inputAccount(dao);
                        if (dao.addAccount(acc)) {
                            System.out.println("Account added successfully.");
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    // Đăng nhập.
                    try {
                        String[] loginInfo = view.inputLogin();
                        Account loggedInUser = dao.findAccount(loginInfo[0], loginInfo[1]);
                        // Nếu đăng nhập thành công, chuyển sang xử lý sau đăng nhập.
                        if (loggedInUser != null) {
                            view.processAfterLogin(dao, loggedInUser);
                        } else {
                            System.out.println("Login fail! Incorrect username or password.");
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    // Thoát chương trình.
                    System.out.println("Exiting...");
                    System.exit(0);
            }
        }
    }
}
