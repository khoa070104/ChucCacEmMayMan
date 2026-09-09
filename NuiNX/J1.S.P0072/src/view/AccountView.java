package view;

import bo.AccountDAO;

import entity.Account;

import utils.Validator;

import java.util.Date;

/**
 * Lớp AccountView xử lý hiển thị giao diện và nhận dữ liệu đầu vào từ người
 * dùng.
 */
public class AccountView {

    /**
     * Hiển thị menu chính và nhận lựa chọn của người dùng.
     *
     * @return số từ 1 đến 3
     */
    public int menu() {
        System.out.println("============ Login Program =========");
        System.out.println("1. Add User");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        // Nhập số nguyên từ 1 đến 3
        return Validator.getInt(
                "Please choose one option: ", "Just enter 1->3", "Invalid number!", 1, 3);
    }

    /**
     * Nhập thông tin tài khoản mới từ bàn phím.
     *
     * @param dao đối tượng quản lý dữ liệu để check trùng tên
     * @return đối tượng Account mới
     */
    public Account inputAccount(AccountDAO dao) {
        System.out.println("---------- Add User --------");
        String username;
        // Vòng lặp bắt nhập tên tài khoản không trống và không trùng
        while (true) {
            username = Validator.getString("Account: ", "Username cannot be empty!", ".*[^ ].*");
            // Nếu tên tài khoản đã tồn tại
            if (dao.isExist(username)) {
                System.out.println("Account already exists. Please enter another one.");
            } else {
                break;
            }
        }
        // Nhập các thông tin cá nhân cơ bản
        String password =
                Validator.getString("Password: ", "Password cannot be empty!", ".*[^ ].*");
        String name = Validator.getString("Name: ", "Name cannot be empty!", ".*[^ ].*");
        String phone =
                Validator.getString(
                        "Phone: ", "Phone number must be 10 or 11 digits!", "^[0-9]{10,11}$");
        String email =
                Validator.getString(
                        "Email: ", "Invalid email format!", "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
        String address = Validator.getString("Address: ", "Address cannot be empty!", ".*[^ ].*");
        Date dob =
                Validator.getDate("DOB (dd/MM/yyyy): ", "Format must be dd/MM/yyyy!", "dd/MM/yyyy");

        // Tạo và trả về đối tượng Account hoàn chỉnh
        return new Account(username, password, name, phone, email, address, dob);
    }

    /**
     * Nhập thông tin đăng nhập từ bàn phím.
     *
     * @return mảng gồm [tài khoản, mật khẩu]
     */
    public String[] inputLogin() {
        System.out.println("------------- Login ----------------");
        String username = Validator.getString("Account: ", "Account cannot be empty!", ".*[^ ].*");
        String password =
                Validator.getString("Password: ", "Password cannot be empty!", ".*[^ ].*");

        // Trả về mảng thông tin đăng nhập
        return new String[] {username, password};
    }

    /**
     * Xử lý các tác vụ sau khi đăng nhập thành công.
     *
     * @param dao đối tượng xử lý đổi mật khẩu
     * @param acc tài khoản đang đăng nhập
     */
    public void processAfterLogin(AccountDAO dao, Account acc) {
        System.out.println("------------ Welcome -----------");
        System.out.println("Hello " + acc.getUsername());
        String choice =
                Validator.getString(
                        "Hi " + acc.getName() + ", " + "do you want change password now? Y/N: ",
                        "Invalid input",
                        "^[YyNn]$");

        // Kiểm tra nếu người dùng muốn đổi mật khẩu.
        if (choice.equalsIgnoreCase("Y")) {
            while (true) {
                try {
                    // Nhập mật khẩu cũ và mật khẩu mới 2 lần
                    String oldPass =
                            Validator.getString("Old password: ", "Cannot be empty", ".*[^ ].*");
                    String newPass =
                            Validator.getString("New password: ", "Cannot be empty", ".*[^ ].*");
                    String rePass =
                            Validator.getString("Renew password: ", "Cannot be empty", ".*[^ ].*");

                    // Nếu mật khẩu mới nhập lại không trùng khớp
                    if (!newPass.equals(rePass)) {
                        System.out.println("Renew password does not match new password!");
                        // Quay lại vòng lặp bắt nhập lại từ đầu
                        continue;
                    }

                    // Gọi DAO để đổi mật khẩu.
                    if (dao.changePassword(acc, oldPass, newPass)) {
                        System.out.println("Password changed successfully!");
                        break;
                    }
                } catch (Exception e) {
                    // Xử lý lỗi trong quá trình đổi mật khẩu.
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
