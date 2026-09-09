package Entity;

public class Student extends Person {
    private String major;
    private double gpa;

    public Student(String id, String fullName, String major, double gpa) {
        super(id, fullName);
        this.major = major;
        this.gpa = gpa;
    }

    public String getMajor() { return major; }
    public double getGpa() { return gpa; }
    public void setMajor(String major) { this.major = major; }
    public void setGpa(double gpa) { this.gpa = gpa; }

    @Override
    public String getDescription() { return major + " student"; }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %.1f", getId(), getFullName(), major, gpa);
    }
}
