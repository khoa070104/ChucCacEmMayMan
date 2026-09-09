package service;

import entity.Locate;
import java.util.ResourceBundle;
import utils.Helper;
import utils.IConstant;

/**
 * Cung cấp các chức năng kiểm tra dữ liệu đăng nhập Ebank theo đề bài.
 */
public class Ebank {
    private ResourceBundle resourceBundle;

    /**
     * Khởi tạo Ebank với giao diện tiếng Anh.
     */
    public Ebank() {
        setLocate(Locate.ENGLISH);
    }

    /**
     * Chuyển ngôn ngữ giao diện.
     *
     * @param locate ngôn ngữ người dùng lựa chọn
     */
    public final void setLocate(Locate locate) {
        resourceBundle = ResourceBundle.getBundle(
                "resources/Language", locate.getLocale());
    }

    /**
     * Kiểm tra số tài khoản có đúng 10 chữ số hay không.
     *
     * @param accountNumber số tài khoản cần kiểm tra
     * @return chuỗi rỗng nếu hợp lệ, ngược lại là thông báo lỗi
     */
    public String checkAccountNumber(String accountNumber) {
        return accountNumber != null && accountNumber.matches(IConstant.ACCOUNT_NUMBER)
                ? "" : resourceBundle.getString("accountInvalid");
    }

    /**
     * Kiểm tra mật khẩu có từ 8 đến 31 ký tự, gồm cả chữ và số.
     *
     * @param password mật khẩu cần kiểm tra
     * @return chuỗi rỗng nếu hợp lệ, ngược lại là thông báo lỗi
     */
    public String checkPassword(String password) {
        return password != null && password.matches(IConstant.PASSWORD)
                ? "" : resourceBundle.getString("passwordInvalid");
    }

    /**
     * Tạo mã captcha ngẫu nhiên.
     *
     * @return captcha gồm chữ và số
     */
    public String generateCaptcha() {
        return Helper.generateCaptcha(IConstant.CAPTCHA_LENGTH);
    }

    /**
     * Kiểm tra captcha nhập vào có nằm trong captcha đã tạo hay không.
     *
     * @param captchaInput chuỗi người dùng nhập
     * @param captchaGenerate captcha do chương trình tạo
     * @return chuỗi rỗng nếu hợp lệ, ngược lại là thông báo lỗi
     */
    public String checkCaptcha(String captchaInput, String captchaGenerate) {
        boolean valid = captchaInput != null && !captchaInput.isEmpty()
                && captchaGenerate != null && captchaGenerate.contains(captchaInput);
        return valid ? "" : resourceBundle.getString("captchaInvalid");
    }
}
