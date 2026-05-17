package model;

import java.io.Serializable;

public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final double DEFAULT_TUITION = 6_000_000;
    public static final double DISCOUNT_RATE = 0.35;

    private String id;
    private String name;
    private String phone;
    private String email;
    private String mountainCode;
    private double tuitionFee;

    public Student() {
    }

    public Student(String id, String name, String phone, String email, String mountainCode, double tuitionFee) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.mountainCode = mountainCode;
        this.tuitionFee = tuitionFee;
    }

    public static double calculateTuition(String phone) {
        if (tools.Acceptable.isViettelOrVnpt(phone)) {
            return DEFAULT_TUITION * (1 - DISCOUNT_RATE);
        }
        return DEFAULT_TUITION;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMountainCode() {
        return mountainCode;
    }

    public void setMountainCode(String mountainCode) {
        this.mountainCode = mountainCode;
    }

    public double getTuitionFee() {
        return tuitionFee;
    }

    public void setTuitionFee(double tuitionFee) {
        this.tuitionFee = tuitionFee;
    }

    @Override
    public String toString() {
        return String.format("%-12s| %-14s| %-13s| %-10s| %,.0f",
                id, name, phone, mountainCode, tuitionFee);
    }
}
