package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import model.Fruit;
import model.OrderItem;
import repository.FruitRepository;
import repository.OrderRepository;

/**
 * Xử lý nghiệp vụ quản lý cửa hàng trái cây.
 *
 * @author LinhNDM
 */
public class FruitService {

    private final FruitRepository fruitRepository;
    private final OrderRepository orderRepository;
    private final ArrayList<OrderItem> cartList = new ArrayList<>();

    /**
     * Khởi tạo service với các repository dữ liệu.
     *
     * @param fruitRepository repository trái cây
     * @param orderRepository repository đơn hàng
     */
    public FruitService(FruitRepository fruitRepository,
            OrderRepository orderRepository) {
        this.fruitRepository = fruitRepository;
        this.orderRepository = orderRepository;
    }

    /**
     * Lấy thông báo lỗi khi nạp file trái cây.
     *
     * @return thông báo lỗi hoặc null
     */
    public String getLoadErrorMessage() {
        return fruitRepository.getLoadErrorMessage();
    }

    /**
     * Thêm trái cây mới sau khi kiểm tra trùng mã.
     *
     * @param fruit trái cây cần thêm
     * @return true nếu thêm thành công
     * @throws IOException khi ghi file thất bại
     */
    public boolean addFruit(Fruit fruit) throws IOException {
        if (fruitRepository.existsById(fruit.getFruitId())) {
            return false;
        }
        fruitRepository.add(fruit);
        return true;
    }

    /**
     * Lấy toàn bộ trái cây.
     *
     * @return danh sách trái cây
     */
    public List<Fruit> getAllFruits() {
        return fruitRepository.findAll();
    }

    /**
     * Kiểm tra đã có trái cây trong hệ thống hay chưa.
     *
     * @return true nếu có ít nhất một trái cây
     */
    public boolean hasFruits() {
        return !fruitRepository.isEmpty();
    }

    /**
     * Lấy trái cây theo số thứ tự hiển thị trên menu.
     *
     * @param listIndex số thứ tự người dùng chọn (bắt đầu từ 1)
     * @return trái cây tương ứng hoặc null
     */
    public Fruit getFruitByListIndex(int listIndex) {
        return fruitRepository.findByIndex(listIndex - 1);
    }

    /**
     * Lấy danh sách trái cây còn tồn kho.
     *
     * @return danh sách trái cây có số lượng lớn hơn 0
     */
    public List<Fruit> getAvailableFruits() {
        List<Fruit> availableList;
        availableList = new ArrayList<>();
        for (Fruit fruit : getAllFruits()) {
            if (fruit.getQuantity() > 0) {
                availableList.add(fruit);
            }
        }
        return availableList;
    }

    /**
     * Thêm sản phẩm vào giỏ hàng và trừ tồn kho.
     *
     * @param fruit trái cây được chọn
     * @param quantity số lượng mua
     */
    public void addToCart(Fruit fruit, int quantity) {
        cartList.add(new OrderItem(fruit.getFruitName(), quantity, fruit.getPrice()));
        fruit.setQuantity(fruit.getQuantity() - quantity);
    }

    /**
     * Lấy giỏ hàng hiện tại.
     *
     * @return danh sách sản phẩm trong giỏ
     */
    public ArrayList<OrderItem> getCartList() {
        return cartList;
    }

    /**
     * Xóa toàn bộ sản phẩm trong giỏ hàng.
     */
    public void clearCart() {
        cartList.clear();
    }

    /**
     * Tính tổng tiền của một danh sách sản phẩm.
     *
     * @param itemList danh sách sản phẩm
     * @return tổng tiền
     */
    public double calculateTotal(List<OrderItem> itemList) {
        double total;
        total = 0;
        for (OrderItem item : itemList) {
            total += item.getAmount();
        }
        return total;
    }

    /**
     * Lưu đơn hàng theo tên khách hàng.
     *
     * @param customerName tên khách hàng
     */
    public void saveOrder(String customerName) {
        ArrayList<OrderItem> orderItemList;
        orderItemList = new ArrayList<>();
        for (OrderItem item : cartList) {
            orderItemList.add(new OrderItem(item.getProductName(),
                    item.getQuantity(), item.getPrice()));
        }
        orderRepository.saveOrder(customerName, orderItemList);
        clearCart();
    }

    /**
     * Kiểm tra đã có đơn hàng hay chưa.
     *
     * @return true nếu có ít nhất một đơn
     */
    public boolean hasOrders() {
        return !orderRepository.isEmpty();
    }

    /**
     * Lấy toàn bộ đơn hàng.
     *
     * @return map đơn hàng theo tên khách
     */
    public Hashtable<String, ArrayList<OrderItem>> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    /**
     * Lấy danh sách tên khách hàng.
     *
     * @return enumeration tên khách
     */
    public Enumeration<String> getCustomerNames() {
        return orderRepository.customerNames();
    }

    /**
     * Lấy đơn hàng theo tên khách hàng.
     *
     * @param customerName tên khách hàng
     * @return danh sách sản phẩm đã đặt
     */
    public ArrayList<OrderItem> getOrderByCustomer(String customerName) {
        return orderRepository.getOrderByCustomer(customerName);
    }

    /**
     * Kiểm tra giỏ hàng có rỗng hay không.
     *
     * @return true nếu giỏ hàng rỗng
     */
    public boolean isCartEmpty() {
        return cartList.isEmpty();
    }
}
