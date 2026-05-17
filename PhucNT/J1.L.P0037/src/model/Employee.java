package model;

public class Employee extends Entity {

    public static final int MAX_WORKING_DAYS = 26;

    private String name;
    private String role;
    private double baseSalary;
    private int workingDays;
    private double bonus;
    private String status;

    public Employee() {
    }

    public Employee(String id, String name, String role, double baseSalary,
            int workingDays, double bonus, String status) {
        super(id);
        this.name = name;
        this.role = role;
        this.baseSalary = baseSalary;
        this.workingDays = workingDays;
        this.bonus = bonus;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public int getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(int workingDays) {
        this.workingDays = workingDays;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isActive() {
        return "active".equalsIgnoreCase(status);
    }

    public double calculateMonthlySalary() {
        return (baseSalary / MAX_WORKING_DAYS) * workingDays + bonus;
    }

    @Override
    public String toFileLine() {
        return String.format("%s, %s, %s, %.0f, %d, %.0f, %s",
                id, name, role, baseSalary, workingDays, bonus, status);
    }

    @Override
    public String toString() {
        return String.format("%-6s| %-16s| %-10s| %8.0f| %5d| %7.0f| %-8s",
                id, name, role, baseSalary, workingDays, bonus, status);
    }
}
