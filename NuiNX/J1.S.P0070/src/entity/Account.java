package entity;

/**
 * Lớp Account đại diện cho tài khoản ngân hàng.
 */
public class Account {

    // Số tài khoản
    private String account;

    // Mật khẩu của tài khoản.
    private String password;

    // khoi tao constructor , getter and setter.
    public Account(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }
}
