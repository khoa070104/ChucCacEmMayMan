package bo;

import entity.Account;

import utils.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Lớp AccountDAO quản lý danh sách tài khoản
 * và xử lý các nghiệp vụ thêm, đăng
 * nhập, đổi mật khẩu.
 */
public class AccountDAO {

    // Danh sách dùng để lưu trữ toàn bộ các tài khoản
    private List<Account> list = new ArrayList<>();

    /**
     * Kiểm tra xem tên tài khoản đã tồn tại trong danh sách hay chưa.
     *
     * @param username Tên tài khoản cần kiểm tra trùng lặp
     * @return true nếu đã tồn tại tài khoản, ngược lại trả về false
     */
    public boolean isExist(String username) {
        // Duyệt qua từng tài khoản có trong danh sách
        for (Account a : list) {
            // Nếu tìm thấy tên trùng, trả về true.
            if (a.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Thêm tài khoản mới vào hệ thống sau khi kiểm tra trùng và mã hóa mật
     * khẩu.
     *
     * @param acc Đối tượng tài khoản mới do người dùng nhập vào
     * @return true nếu thêm tài khoản vào danh sách thành công
     * @throws Exception nếu tên đăng nhập đã có người sử dụng trong hệ thống
     */
    public boolean addAccount(Account acc) throws Exception {
        // Kiểm tra trùng tên đăng nhập.
        if (isExist(acc.getUsername())) {
            throw new Exception("Username already exists in DB!");
        }
        // Mã hóa mật khẩu trước khi lưu.
        acc.setPassword(Validator.getMD5(acc.getPassword()));
        return list.add(acc);
    }

    /**
     * Thêm tài khoản theo đúng chữ ký phương thức được yêu cầu trong đề bài.
     *
     * @param username tên đăng nhập
     * @param password mật khẩu chưa mã hóa
     * @param name tên người dùng
     * @param phone số điện thoại
     * @param email địa chỉ email
     * @param address địa chỉ liên hệ
     * @param dob ngày sinh theo định dạng dd/MM/yyyy
     * @return số thứ tự của tài khoản vừa thêm
     * @throws Exception nếu dữ liệu ngày sinh sai hoặc tài khoản bị trùng
     */
    public int addAccount(
            String username,
            String password,
            String name,
            String phone,
            String email,
            String address,
            String dob)
            throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setLenient(false);
        try {
            addAccount(
                    new Account(
                            username, password, name, phone, email, address, format.parse(dob)));
            return list.size();
        } catch (ParseException exception) {
            throw new Exception("Date of birth must have format dd/MM/yyyy.");
        }
    }

    /**
     * Kiểm tra thông tin đăng nhập của người dùng.
     *
     * @param username Tên tài khoản người dùng nhập để đăng nhập
     * @param password Mật khẩu gốc người dùng nhập để đăng nhập
     * @return Đối tượng Account nếu thông tin chính xác, ngược lại trả về null
     */
    public Account findAccount(String username, String password) {
        String hashPass = Validator.getMD5(password);
        // Duyệt danh sách tìm tài khoản khớp thông tin.
        for (Account a : list) {
            // Nếu username và mật khẩu đã hash trùng khớp, trả về đối tượng Account.
            if (a.getUsername().equals(username) && a.getPassword().equals(hashPass)) {
                return a;
            }
        }
        // Trả về null nếu sai tài khoản/mật khẩu.
        return null;
    }

    /**
     * Kiểm tra tên đăng nhập và mật khẩu theo chữ ký trong đề bài.
     *
     * @param username tên đăng nhập
     * @param password mật khẩu chưa mã hóa
     * @return true nếu thông tin đăng nhập chính xác
     */
    public Boolean login(String username, String password) {
        return findAccount(username, password) != null;
    }

    /**
     * Thay đổi mật khẩu cho tài khoản sau khi xác thực mật khẩu cũ.
     *
     * @param acc Đối tượng tài khoản hiện tại đang đăng nhập thành công
     * @param oldPass Mật khẩu cũ cần nhập vào để đối chiếu xác thực
     * @param newPass Mật khẩu mới muốn thay đổi
     * @return true nếu thực hiện cập nhật mật khẩu mới thành công
     * @throws Exception nếu mật khẩu cũ nhập vào không khớp với mật khẩu lưu
     * trong hệ thống
     */
    public boolean changePassword(Account acc, String oldPass, String newPass) throws Exception {
        // Kiểm tra mật khẩu cũ có đúng không.
        if (!acc.getPassword().equals(Validator.getMD5(oldPass))) {
            throw new Exception("Old password is incorrect!");
        }
        // Cập nhật mật khẩu mới đã mã hóa.
        acc.setPassword(Validator.getMD5(newPass));
        return true;
    }
}
