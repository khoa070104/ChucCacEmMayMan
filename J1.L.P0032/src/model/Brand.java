/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author NCPC
 */
public class Brand {
    String brandId,brandName, brandSound;
    double price;

    public Brand(String brandId, String brandName, String brandSound, double price) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.brandSound = brandSound;
        this.price = price;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandSound() {
        return brandSound;
    }

    public void setBrandSound(String brandSound) {
        this.brandSound = brandSound;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Brand{" + "brandId=" + brandId + ", brandName=" + brandName + ", brandSound=" + brandSound + ", price=" + price + '}';
    }
    
    
}
