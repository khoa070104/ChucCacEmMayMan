package model;

/**
 * Thực thể sản phẩm trái cây.
 *
 * @author LinhNDM
 */
public class Fruit {

    private String fruitId;
    private String fruitName;
    private double price;
    private int quantity;
    private String origin;

    /**
     * Khởi tạo thông tin trái cây.
     *
     * @param fruitId mã trái cây
     * @param fruitName tên trái cây
     * @param price giá bán
     * @param quantity số lượng tồn kho
     * @param origin xuất xứ
     */
    public Fruit(String fruitId, String fruitName, double price,
            int quantity, String origin) {
        this.fruitId = fruitId;
        this.fruitName = fruitName;
        this.price = price;
        this.quantity = quantity;
        this.origin = origin;
    }

    /**
     * Lấy mã trái cây.
     *
     * @return mã trái cây
     */
    public String getFruitId() {
        return fruitId;
    }

    /**
     * Gán mã trái cây.
     *
     * @param fruitId mã trái cây mới
     */
    public void setFruitId(String fruitId) {
        this.fruitId = fruitId;
    }

    /**
     * Lấy tên trái cây.
     *
     * @return tên trái cây
     */
    public String getFruitName() {
        return fruitName;
    }

    /**
     * Gán tên trái cây.
     *
     * @param fruitName tên trái cây mới
     */
    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }

    /**
     * Lấy giá bán.
     *
     * @return giá bán
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gán giá bán.
     *
     * @param price giá bán mới
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Lấy số lượng tồn kho.
     *
     * @return số lượng tồn kho
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Gán số lượng tồn kho.
     *
     * @param quantity số lượng mới
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Lấy xuất xứ.
     *
     * @return xuất xứ
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Gán xuất xứ.
     *
     * @param origin xuất xứ mới
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
