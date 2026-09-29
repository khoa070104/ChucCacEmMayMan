package model;

/**
 * Lớp Wallet biểu diễn ví tiền của người dùng, chứa thông tin về số tiền hiện có
 * và cung cấp các phương thức tính toán tổng giá trị hóa đơn cũng như kiểm tra khả năng thanh toán.
 *
 * @version 29/09/2026
 */
public class Wallet {

    private int amount;

    /**
     * Khởi tạo ví tiền với số dư mặc định là 0.
     */
    public Wallet() {
        this(0);
    }

    /**
     * Khởi tạo ví tiền với số dư được chỉ định.
     *
     * @param amount số tiền có trong ví (phải >= 0)
     * @throws IllegalArgumentException nếu số tiền âm
     */
    public Wallet(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Wallet amount must be non-negative.");
        }
        this.amount = amount;
    }

    /**
     * Lấy số tiền hiện có trong ví.
     *
     * @return số tiền trong ví
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Cập nhật số tiền trong ví.
     *
     * @param amount số tiền mới (phải >= 0)
     * @throws IllegalArgumentException nếu số tiền âm
     */
    public void setAmount(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Wallet amount must be non-negative.");
        }
        this.amount = amount;
    }

    /**
     * Tính tổng số tiền của danh sách các hóa đơn.
     *
     * @param bills mảng chứa các giá trị hóa đơn
     * @return tổng số tiền của các hóa đơn
     * @throws IllegalArgumentException nếu mảng bills là null hoặc chứa giá trị âm
     */
    public int calcTotal(int[] bills) {
        if (bills == null) {
            throw new IllegalArgumentException("Bills array must not be null.");
        }

        int total = 0;
        // Duyệt qua từng hóa đơn và cộng dồn vào tổng tiền
        for (int bill : bills) {
            if (bill < 0) {
                throw new IllegalArgumentException("Bill value must not be negative.");
            }
            total += bill;
        }
        return total;
    }

    /**
     * Kiểm tra xem số tiền trong ví có đủ để thanh toán tổng tiền hóa đơn hay không.
     *
     * @param total tổng số tiền cần thanh toán
     * @return true nếu số tiền trong ví đủ chi trả (amount >= total), ngược lại false
     * @throws IllegalArgumentException nếu total âm
     */
    public boolean payMoney(int total) {
        if (total < 0) {
            throw new IllegalArgumentException("Total bill amount must not be negative.");
        }
        // So sánh số dư trong ví với tổng số tiền cần thanh toán
        return this.amount >= total;
    }

    /**
     * Phương thức tĩnh hỗ trợ kiểm tra chi trả với số dư ví truyền vào trực tiếp.
     *
     * @param total tổng số tiền cần thanh toán
     * @param walletAmount số tiền trong ví
     * @return true nếu số tiền trong ví đủ chi trả, ngược lại false
     * @throws IllegalArgumentException nếu tổng tiền hoặc số dư âm
     */
    public static boolean payMoney(int total, int walletAmount) {
        if (total < 0 || walletAmount < 0) {
            throw new IllegalArgumentException("Amount must not be negative.");
        }
        return walletAmount >= total;
    }
}
