package repository;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import model.OrderItem;

/**
 * Lớp truy cập dữ liệu đơn hàng trong bộ nhớ.
 *
 * @author LinhNDM
 */
public class OrderRepository {

    private final Hashtable<String, ArrayList<OrderItem>> orderMap
            = new Hashtable<>();

    /**
     * Lưu đơn hàng của khách hàng.
     *
     * @param customerName tên khách hàng
     * @param itemList danh sách sản phẩm đặt mua
     */
    public void saveOrder(String customerName, ArrayList<OrderItem> itemList) {
        String key;
        key = customerName.trim();
        if (orderMap.containsKey(key)) {
            orderMap.get(key).addAll(itemList);
        } else {
            orderMap.put(key, new ArrayList<>(itemList));
        }
    }

    /**
     * Lấy toàn bộ đơn hàng.
     *
     * @return map đơn hàng theo tên khách
     */
    public Hashtable<String, ArrayList<OrderItem>> getAllOrders() {
        return orderMap;
    }

    /**
     * Kiểm tra chưa có đơn hàng nào.
     *
     * @return true nếu rỗng
     */
    public boolean isEmpty() {
        return orderMap.isEmpty();
    }

    /**
     * Lấy danh sách tên khách hàng.
     *
     * @return enumeration tên khách
     */
    public Enumeration<String> customerNames() {
        return orderMap.keys();
    }

    /**
     * Lấy đơn hàng theo tên khách hàng.
     *
     * @param customerName tên khách hàng
     * @return danh sách sản phẩm đã đặt
     */
    public ArrayList<OrderItem> getOrderByCustomer(String customerName) {
        return orderMap.get(customerName);
    }
}
