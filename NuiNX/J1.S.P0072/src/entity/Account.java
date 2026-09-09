package entity;

import java.util.Date;

/**
 * Lớp Account dùng để định nghĩa cấu trúc dữ liệu và thông tin chi tiết của một
 * tài khoản.
 */
public class Account {

    // Tên đăng nhập của tài khoản
    private String username;

    // Mật khẩu đã được mã hóa MD5 của tài khoản
    private String password;

    // Tên của chủ tài khoản
    private String name;

    // Số điện thoại (10 hoặc 11 số)
    private String phone;

    // Địa chỉ email của tài khoản
    private String email;

    // Địa chỉ của chủ tài khoản
    private String address;

    // Ngày sinh của chủ tài khoản
    private Date dob;

    /**
     * Khởi tạo đối tượng Account rỗng.
     */
    public Account() {}

    /**
     * Khởi tạo đối tượng Account với đầy đủ thông tin.
     */
    public Account(
            String username,
            String password,
            String name,
            String phone,
            String email,
            String address,
            Date dob) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.dob = dob;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getDob() {
        return dob;
    }
}
