package model;

/**
 * Lớp Person đại diện cho người dùng tham gia mua sắm, sở hữu một ví tiền (Wallet)
 * để thực hiện chi trả các hóa đơn.
 *
 * @version 29/09/2026
 */
public class Person {

    private model.Wallet wallet;

    /**
     * Khởi tạo người dùng với ví tiền mặc định có số dư bằng 0.
     */
    public Person() {
        this.wallet = new model.Wallet(0);
    }

    /**
     * Khởi tạo người dùng với số dư ví tiền được chỉ định.
     *
     * @param walletAmount số tiền ban đầu trong ví
     */
    public Person(int walletAmount) {
        this.wallet = new model.Wallet(walletAmount);
    }

    /**
     * Khởi tạo người dùng với đối tượng ví tiền cụ thể.
     *
     * @param wallet đối tượng Wallet của người dùng
     */
    public Person(model.Wallet wallet) {
        if (wallet == null) {
            throw new IllegalArgumentException("Wallet must not be null.");
        }
        this.wallet = wallet;
    }

    /**
     * Lấy đối tượng ví tiền của người dùng.
     *
     * @return đối tượng Wallet
     */
    public model.Wallet getWallet() {
        return wallet;
    }

    /**
     * Cập nhật ví tiền cho người dùng.
     *
     * @param wallet đối tượng Wallet mới
     */
    public void setWallet(model.Wallet wallet) {
        if (wallet == null) {
            throw new IllegalArgumentException("Wallet must not be null.");
        }
        this.wallet = wallet;
    }

    /**
     * Tính tổng số tiền của các hóa đơn.
     *
     * @param bills mảng chứa các giá trị hóa đơn
     * @return tổng giá trị các hóa đơn
     */
    public int calcTotal(int[] bills) {
        if (wallet != null) {
            return wallet.calcTotal(bills);
        }
        if (bills == null) {
            throw new IllegalArgumentException("Bills array must not be null.");
        }
        int total = 0;
        for (int bill : bills) {
            if (bill < 0) {
                throw new IllegalArgumentException("Bill value must not be negative.");
            }
            total += bill;
        }
        return total;
    }

    /**
     * Kiểm tra khả năng chi trả hóa đơn bằng số tiền trong ví của người dùng.
     *
     * @param total tổng số tiền cần thanh toán
     * @return true nếu ví tiền đủ chi trả, ngược lại false
     */
    public boolean payMoney(int total) {
        if (wallet == null) {
            throw new IllegalStateException("Wallet has not been initialized.");
        }
        return wallet.payMoney(total);
    }

    /**
     * Lớp Wallet lồng bên trong Person để đáp ứng đặc tả thiết kế:
     * "Designing class Wallet represented the user's wallet within class Person."
     */
    public static class Wallet extends model.Wallet {

        public Wallet() {
            super();
        }

        public Wallet(int amount) {
            super(amount);
        }
    }
}
