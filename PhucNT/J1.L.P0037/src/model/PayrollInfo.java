package model;

public class PayrollInfo {

    private final Employee employee;
    private final double totalSalary;

    public PayrollInfo(Employee employee, double totalSalary) {
        this.employee = employee;
        this.totalSalary = totalSalary;
    }

    public Employee getEmployee() {
        return employee;
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    @Override
    public String toString() {
        return String.format("%-6s| %-16s| %-10s| %8.0f| %5d| %7.0f| %12,.0f",
                employee.getId(),
                employee.getName(),
                employee.getRole(),
                employee.getBaseSalary(),
                employee.getWorkingDays(),
                employee.getBonus(),
                totalSalary);
    }
}
