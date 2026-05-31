package model;

/**
 * Dòng chi tiết trong đơn hàng của khách.
 *
 * @author LinhNDM
 */
public class OrderItem {

    private final String productName;
    private final int quantity;
    private final double price;
    private final double amount;

    /**
     * Khởi tạo dòng chi tiết đơn hàng.
     *
     * @param productName tên sản phẩm
     * @param quantity số lượng mua
     * @param price đơn giá
     */
    public OrderItem(String productName, int quantity, double price) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.amount = quantity * price;
    }

    /**
     * Lấy tên sản phẩm.
     *
     * @return tên sản phẩm
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Lấy số lượng mua.
     *
     * @return số lượng
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Lấy đơn giá.
     *
     * @return đơn giá
     */
    public double getPrice() {
        return price;
    }

    /**
     * Lấy thành tiền.
     *
     * @return thành tiền
     */
    public double getAmount() {
        return amount;
    }
}
