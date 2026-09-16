package model;

import java.io.Serializable;

public class Vehicle implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String color;
    private double price;
    private String brand;
    private String type;
    private int productYear;

    public Vehicle(String id, String name, String color, double price,
            String brand, String type, int productYear) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.price = price;
        this.brand = brand;
        this.type = type;
        this.productYear = productYear;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getColor() { return color; }
    public double getPrice() { return price; }
    public String getBrand() { return brand; }
    public String getType() { return type; }
    public int getProductYear() { return productYear; }

    public void setName(String name) { this.name = name; }
    public void setColor(String color) { this.color = color; }
    public void setPrice(double price) { this.price = price; }
    public void setBrand(String brand) { this.brand = brand; }
    public void setType(String type) { this.type = type; }
    public void setProductYear(int productYear) { this.productYear = productYear; }
}
