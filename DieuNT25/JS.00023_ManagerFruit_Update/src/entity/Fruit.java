package entity;

import common.Constant;

/**
 *
 * @author LeSyThanhLong
 */
public class Fruit {

    private String fruitId;
    private String fruitName;
    private double price;
    private int quantity;
    private String origin;

    public Fruit() {
    }

    public Fruit(String fruitId, String fruitName, double price, int quantity, String origin) {
        this.setFruitId(fruitId);
        this.setFruitName(fruitName);
        this.setPrice(price);
        this.setQuantity(quantity);
        this.setOrigin(origin);
    }

    public String getFruitId() {
        return fruitId;
    }

    public void setFruitId(String fruitId) {
        if (fruitId == null || !fruitId.trim().matches(Constant.ID)) {
            throw new IllegalArgumentException("Invalid fruit ID format. It must follow pattern 'Fxx' (where x is a digit).");
        }
        this.fruitId = fruitId.trim();
    }

    public String getFruitName() {
        return fruitName;
    }

    public void setFruitName(String fruitName) {
        if (fruitName == null || !fruitName.trim().matches(Constant.NAME)) {
            throw new IllegalArgumentException("Invalid fruit name. Only alphabetic characters and spaces are allowed.");
        }
        this.fruitName = fruitName.trim();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be a positive number greater than 0.");
        }
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be a positive integer greater than 0.");
        }
        this.quantity = quantity;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        if (origin == null || !origin.trim().matches(Constant.NAME)) {
            throw new IllegalArgumentException("Invalid origin. Only alphabetic characters and spaces are allowed.");
        }
        this.origin = origin.trim();
    }

    @Override
    public String toString() {
        return "Fruit{" + "fruitId=" + fruitId + ", fruitName=" + fruitName + ", price=" + price + ", quantity=" + quantity + ", origin=" + origin + '}';
    }
}