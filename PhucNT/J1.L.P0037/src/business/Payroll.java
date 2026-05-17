package business;

import java.util.ArrayList;
import java.util.List;
import model.Employee;
import model.PayrollInfo;

public class Payroll {

    private final List<PayrollInfo> payrollList;

    public Payroll(List<Employee> employees) {
        payrollList = new ArrayList<>();
        calculate(employees);
    }

    private void calculate(List<Employee> employees) {
        for (Employee e : employees) {
            if (e.isActive()) {
                double total = e.calculateMonthlySalary();
                payrollList.add(new PayrollInfo(e, total));
            }
        }
    }

    public List<PayrollInfo> getPayrollList() {
        return payrollList;
    }

    public double getGrandTotal() {
        double total = 0;
        for (PayrollInfo info : payrollList) {
            total += info.getTotalSalary();
        }
        return total;
    }

    public void show() {
        if (payrollList.isEmpty()) {
            System.out.println("No active employee for payroll calculation.");
            return;
        }
        System.out.println("Monthly Payroll (Active Employees Only):");
        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.println("ID    | Name            | Role      | Salary  | Days | Bonus  | Total Salary");
        System.out.println("-----------------------------------------------------------------------------------------------");
        for (PayrollInfo info : payrollList) {
            System.out.println(info);
        }
        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.printf("Grand Total: %,.0f%n", getGrandTotal());
    }
}
