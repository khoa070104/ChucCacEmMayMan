package model;

/**
 * Line item in a customer order.
 */
public class OrderItem {

    private final String productName;
    private final int quantity;
    private final double price;
    private final double amount;

    public OrderItem(String productName, int quantity, double price) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.amount = quantity * price;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getAmount() {
        return amount;
    }
}
