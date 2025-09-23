package model;

public class Student {
    private String studenId, name, phoneNumber,email, mountainCode;
    private double tutioneFee;

    public Student(String studenId, String name, String phoneNumber, String email, String mountainCode, double tutioneFee) {
        this.studenId = studenId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.mountainCode = mountainCode;
        this.tutioneFee = tutioneFee;
    }

    public String getStudenId() {
        return studenId;
    }

    public void setStudenId(String studenId) {
        this.studenId = studenId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public double getTutioneFee() {
        return tutioneFee;
    }

    public void setTutioneFee(double tutioneFee) {
        this.tutioneFee = tutioneFee;
    }

    @Override
    public String toString() {
        return "Student{" + "studenId=" + studenId + ", name=" + name + ", phoneNumber=" + phoneNumber + ", email=" + email + ", mountainCode=" + mountainCode + ", tutioneFee=" + tutioneFee + '}';
    }
    
    
    
    
}
