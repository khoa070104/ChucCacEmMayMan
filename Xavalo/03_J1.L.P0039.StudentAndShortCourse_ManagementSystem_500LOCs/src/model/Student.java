package model;

public class Student {
    private final String id;
    private final String fullName;
    private final String major;
    private double gpa;

    public Student(String id, String fullName, String major, double gpa) {
        this.id = id; this.fullName = fullName; this.major = major; this.gpa = gpa;
    }
    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public String getMajor() { return major; }
    public double getGpa() { return gpa; }
    public void setGpa(double value) { gpa = value; }
    public String toFileLine() { return String.format("%s, %s, %s, %.1f", id, fullName, major, gpa); }
}
