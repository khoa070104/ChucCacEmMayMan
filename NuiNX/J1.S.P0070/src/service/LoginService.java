package service;

import data.Data;

import entity.Account;

import utils.Helper;
import utils.IConstant;
import utils.Validate;

import java.util.ResourceBundle;

/**
 * Lớp LoginService xử lý nghiệp vụ đăng nhập Ebank.
 */
public class LoginService {

    /**
     * Điều phối quy trình đăng nhập: nhập tài khoản, mật khẩu, captcha và xác
     * thực.
     *
     * @param resourceBundle Tài nguyên ngôn ngữ đã chọn
     */
    public void login(ResourceBundle resourceBundle) {

        // Nhập tài khoản và kiểm tra định dạng
        String account =
                Validate.getString(
                        resourceBundle.getString("account"),
                        resourceBundle.getString("accountInvalid"),
                        IConstant.ACCOUNT_NUMBER);

        // Nhập mật khẩu và kiểm tra định dạng
        String password =
                Validate.getString(
                        resourceBundle.getString("password"),
                        resourceBundle.getString("passwordInvalid"),
                        IConstant.PASSWORD);

        // Tạo và hiển thị mã Captcha ngẫu nhiên
        String captchaGenerate = Helper.generateCaptcha(IConstant.CAPTCHA_LENGTH);
        System.out.println(resourceBundle.getString("captcha") + captchaGenerate);

        // Xác thực captcha
        Validate.verifyCaptcha(
                resourceBundle.getString("inputCaptcha"),
                resourceBundle.getString("captchaInvalid"),
                captchaGenerate);

        // Kiểm tra tài khoản và mật khẩu
        if (authentication(account, password)) {
            // Thông báo đăng nhập thành công
            System.out.println(resourceBundle.getString("loginSuccess"));
        } else {
            // Thông báo đăng nhập thất bại.
            System.out.println(resourceBundle.getString("loginFailed"));
        }
    }

    /**
     * Đối chiếu tài khoản và mật khẩu với dữ liệu mẫu.
     *
     * @param account Số tài khoản nhập vào
     * @param password Mật khẩu nhập vào
     * @return true nếu đúng, false nếu sai
     */
    private boolean authentication(String account, String password) {
        // Duyệt qua từng đối tượng tài khoản mẫu để đối chiếu thông tin.
        for (Account a : Data.listAccount) {

            // So sánh trùng khớp đồng thời cả số tài khoản và mật khẩu nhập vào.
            if (account.equals(a.getAccount()) && password.equals(a.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
