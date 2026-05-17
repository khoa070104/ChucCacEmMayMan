package dispatcher;

import business.Employees;
import business.Payroll;
import tools.Inputter;

public class Main {

    private static final String EMPLOYEE_FILE = "data/employees.txt";

    private final Inputter inputter;
    private final Employees employees;

    public Main() {
        inputter = new Inputter();
        employees = new Employees(EMPLOYEE_FILE);
    }

    private void showMenu() {
        System.out.println();
        System.out.println("========== EMPLOYEE PAYROLL MANAGEMENT SYSTEM ==========");
        System.out.println("1. Load employee data from file");
        System.out.println("2. Add a new employee");
        System.out.println("3. Update employee information");
        System.out.println("4. Remove an employee by ID");
        System.out.println("5. Search employees by attribute");
        System.out.println("6. Calculate monthly payroll");
        System.out.println("7. Display employee list");
        System.out.println("8. Save data to file");
        System.out.println("9. Quit program");
        System.out.println("========================================================");
    }

    public void run() {
        String choice;
        do {
            showMenu();
            choice = inputter.getString("Enter your choice [1-9]: ");
            switch (choice) {
                case "1":
                    employees.loadFromFile();
                    break;
                case "2":
                    employees.addEmployee(inputter);
                    break;
                case "3":
                    employees.updateEmployee(inputter);
                    break;
                case "4":
                    employees.removeEmployee(inputter);
                    break;
                case "5":
                    employees.searchEmployees(inputter);
                    break;
                case "6": {
                    Payroll payroll = new Payroll(employees);
                    payroll.show();
                    break;
                }
                case "7":
                    employees.showAll();
                    break;
                case "8":
                    employees.saveToFile();
                    break;
                case "9":
                    if (!handleExit()) {
                        choice = "0";
                    }
                    break;
                default:
                    System.out.println("This function is not available.");
                    break;
            }
        } while (!choice.equals("9"));
    }

    private boolean handleExit() {
        if (!employees.isSaved()) {
            String confirm = inputter.inputYesNo(
                    "Do you want to save the changes before exiting? (Y/N): ");
            if (confirm.equals("Y")) {
                employees.saveToFile();
                System.out.println("Goodbye!");
                return true;
            }
            String forceExit = inputter.inputYesNo(
                    "You have unsaved changes. Are you sure you want to exit without saving? (Y/N): ");
            if (forceExit.equals("Y")) {
                System.out.println("Goodbye!");
            }
            return forceExit.equals("Y");
        }
        System.out.println("Goodbye!");
        return true;
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
