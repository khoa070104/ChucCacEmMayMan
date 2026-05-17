package repository;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import model.OrderItem;

/**
 * Data access layer – stores customer orders in Hashtable.
 */
public class OrderRepository {

    private final Hashtable<String, ArrayList<OrderItem>> orders = new Hashtable<>();

    public void saveOrder(String customerName, ArrayList<OrderItem> items) {
        String key = customerName.trim();
        if (orders.containsKey(key)) {
            orders.get(key).addAll(items);
        } else {
            orders.put(key, new ArrayList<>(items));
        }
    }

    public Hashtable<String, ArrayList<OrderItem>> getAllOrders() {
        return orders;
    }

    public boolean isEmpty() {
        return orders.isEmpty();
    }

    public Enumeration<String> customerNames() {
        return orders.keys();
    }

    public ArrayList<OrderItem> getOrderByCustomer(String customerName) {
        return orders.get(customerName);
    }
}
