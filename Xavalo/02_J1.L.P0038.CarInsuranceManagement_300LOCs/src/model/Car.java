package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Car implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String licensePlate;
    private String owner;
    private String brand;
    private long value;
    private LocalDate registrationDate;
    private String registrationPlace;
    private int vehicleType;

    public Car(String licensePlate, String owner, String brand, long value,
               LocalDate registrationDate, String registrationPlace, int vehicleType) {
        this.licensePlate = licensePlate;
        this.owner = owner;
        this.brand = brand;
        this.value = value;
        this.registrationDate = registrationDate;
        this.registrationPlace = registrationPlace;
        this.vehicleType = vehicleType;
    }

    public String getLicensePlate() { return licensePlate; }
    public String getOwner() { return owner; }
    public String getBrand() { return brand; }
    public long getValue() { return value; }
    public LocalDate getRegistrationDate() { return registrationDate; }
    public String getRegistrationPlace() { return registrationPlace; }
    public int getVehicleType() { return vehicleType; }
    public void setOwner(String value) { owner = value; }
    public void setBrand(String value) { brand = value; }
    public void setValue(long value) { this.value = value; }
    public void setRegistrationDate(LocalDate value) { registrationDate = value; }
    public void setRegistrationPlace(String value) { registrationPlace = value; }
    public void setVehicleType(int value) { vehicleType = value; }
}
