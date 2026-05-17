package business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import model.Employee;
import tools.Acceptable;
import tools.Inputter;

public class Employees extends ArrayList<Employee> {

    private static final String TABLE_HEADER = "--------------------------------------------------------------------------------------\n"
            + "ID    | Name            | Role      | Salary  | Days | Bonus  | Status\n"
            + "--------------------------------------------------------------------------------------";
    private static final String TABLE_FOOTER = "--------------------------------------------------------------------------------------";

    private final String pathFile;
    private boolean saved;

    public Employees(String pathFile) {
        this.pathFile = pathFile;
        this.saved = true;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public Employee searchById(String id) {
        for (Employee e : this) {
            if (e.getId().equalsIgnoreCase(id)) {
                return e;
            }
        }
        return null;
    }

    public boolean isDuplicateId(String id) {
        return searchById(id) != null;
    }

    public static Employee parseLine(String line) {
        if (line == null || line.trim().isEmpty()) {
            return null;
        }
        String[] parts = line.split(",");
        if (parts.length != 7) {
            return null;
        }
        String id = parts[0].trim();
        String name = parts[1].trim();
        String role = parts[2].trim();
        String baseSalaryStr = parts[3].trim();
        String workingDaysStr = parts[4].trim();
        String bonusStr = parts[5].trim();
        String status = parts[6].trim();

        if (!Acceptable.isValid(id, Acceptable.EMPLOYEE_ID_VALID)
                || !Acceptable.isValid(name, Acceptable.NON_EMPTY_VALID)
                || !Acceptable.isValidRole(role)
                || !Acceptable.isPositiveReal(baseSalaryStr)
                || !Acceptable.isValid(workingDaysStr, Acceptable.WORKING_DAYS_VALID)
                || !Acceptable.isNonNegativeReal(bonusStr)
                || !Acceptable.isValid(status, Acceptable.STATUS_VALID)) {
            return null;
        }

        return new Employee(
                id.toUpperCase(),
                name,
                Acceptable.normalizeRole(role),
                Double.parseDouble(baseSalaryStr),
                Integer.parseInt(workingDaysStr),
                Double.parseDouble(bonusStr),
                Acceptable.normalizeStatus(status));
    }

    public static boolean isValidEmployee(Employee employee, Employees existing,
            boolean checkIdDuplicate, String excludeIdForDuplicate) {
        if (employee == null) {
            return false;
        }
        if (!Acceptable.isValid(employee.getId(), Acceptable.EMPLOYEE_ID_VALID)
                || !Acceptable.isValid(employee.getName(), Acceptable.NON_EMPTY_VALID)
                || !Acceptable.isValidRole(employee.getRole())
                || employee.getBaseSalary() <= 0
                || employee.getWorkingDays() < 0
                || employee.getWorkingDays() > Employee.MAX_WORKING_DAYS
                || employee.getBonus() < 0
                || !Acceptable.isValid(employee.getStatus(), Acceptable.STATUS_VALID)) {
            return false;
        }
        if (checkIdDuplicate) {
            Employee found = existing.searchById(employee.getId());
            if (found != null && (excludeIdForDuplicate == null
                    || !found.getId().equalsIgnoreCase(excludeIdForDuplicate))) {
                return false;
            }
        }
        return true;
    }

    public boolean loadFromFile() {
        File file = new File(pathFile);
        if (!file.exists()) {
            System.out.println("File not found: " + pathFile);
            return false;
        }

        List<Employee> loaded = new ArrayList<>();
        Employees temp = new Employees(pathFile);
        int lineNo = 0;
        int skipped = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                lineNo++;
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                Employee employee = parseLine(line);
                temp.clear();
                temp.addAll(loaded);
                if (employee == null || !isValidEmployee(employee, temp, true, null)) {
                    System.out.println("Skipped invalid line " + lineNo + ": " + line);
                    skipped++;
                    continue;
                }
                loaded.add(employee);
            }
        } catch (Exception e) {
            System.out.println("Error loading employee data: " + e.getMessage());
            return false;
        }

        clear();
        addAll(loaded);
        saved = true;
        System.out.printf("Loaded %d employee(s) from file. Skipped %d invalid line(s).%n",
                loaded.size(), skipped);
        return true;
    }

    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathFile))) {
            for (Employee e : this) {
                bw.write(e.toFileLine());
                bw.newLine();
            }
            saved = true;
            System.out.println("Employee data has been successfully saved to `" + pathFile + "`.");
        } catch (Exception e) {
            System.out.println("Error saving employee data: " + e.getMessage());
        }
    }

    public void addEmployee(Inputter inputter) {
        String id;
        do {
            id = inputter.inputAndLoop("Employee ID [Exxx]: ", Acceptable.EMPLOYEE_ID_VALID);
            if (isDuplicateId(id)) {
                System.out.println("Employee ID already exists. Please enter another ID.");
            }
        } while (isDuplicateId(id));

        String name = inputter.inputAndLoop("Employee name: ", Acceptable.NON_EMPTY_VALID);
        String role = inputter.inputRole();
        double baseSalary = Double.parseDouble(inputter.inputPositiveReal("Base salary: "));
        int workingDays = Integer.parseInt(inputter.inputWorkingDays("Working days [0-26]: "));
        double bonus = Double.parseDouble(inputter.inputNonNegativeReal("Bonus: "));
        String status = Acceptable.normalizeStatus(
                inputter.inputAndLoop("Status [active/inactive]: ", Acceptable.STATUS_VALID));

        Employee employee = new Employee(id.toUpperCase(), name, role, baseSalary, workingDays, bonus, status);
        add(employee);
        saved = false;
        System.out.println("Employee added successfully!");
    }

    public void updateEmployee(Inputter inputter) {
        String id = inputter.inputAndLoop("Enter Employee ID to update: ", Acceptable.EMPLOYEE_ID_VALID);
        Employee employee = searchById(id);
        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }

        System.out.println("Current information:");
        showEmployeeDetail(employee);

        String role = inputter.inputOptionalRole();
        String baseSalaryStr = inputter.inputOptionalPositiveReal("New base salary (blank to keep): ");
        String workingDaysStr = inputter.inputOptionalWorkingDays("New working days (blank to keep): ");
        String bonusStr = inputter.inputOptionalNonNegativeReal("New bonus (blank to keep): ");
        String status = inputter.inputOptionalStatus();

        if (!role.isEmpty()) {
            employee.setRole(role);
        }
        if (!baseSalaryStr.isEmpty()) {
            employee.setBaseSalary(Double.parseDouble(baseSalaryStr));
        }
        if (!workingDaysStr.isEmpty()) {
            employee.setWorkingDays(Integer.parseInt(workingDaysStr));
        }
        if (!bonusStr.isEmpty()) {
            employee.setBonus(Double.parseDouble(bonusStr));
        }
        if (!status.isEmpty()) {
            employee.setStatus(status);
        }

        saved = false;
        System.out.println("Employee information updated successfully.");
    }

    public void removeEmployee(Inputter inputter) {
        String id = inputter.inputAndLoop("Enter Employee ID to remove: ", Acceptable.EMPLOYEE_ID_VALID);
        Employee employee = searchById(id);
        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }

        showEmployeeDetail(employee);
        String confirm = inputter.inputYesNo("Are you sure you want to remove this employee? (Y/N): ");
        if (confirm.equals("Y")) {
            remove(employee);
            saved = false;
            System.out.println("Employee removed successfully.");
        }
    }

    public void searchEmployees(Inputter inputter) {
        System.out.println("Search by: 1-ID | 2-Name | 3-Role | 4-Status");
        String option = inputter.getString("Enter search option [1-4]: ");
        List<Employee> result = new ArrayList<>();

        switch (option) {
            case "1": {
                String id = inputter.inputAndLoop("Enter Employee ID: ", Acceptable.EMPLOYEE_ID_VALID);
                Employee employee = searchById(id);
                if (employee != null) {
                    result.add(employee);
                }
                break;
            }
            case "2": {
                String keyword = inputter.getString("Enter name or partial name: ");
                if (keyword.isEmpty()) {
                    System.out.println("Name cannot be empty.");
                    return;
                }
                String lower = keyword.toLowerCase();
                for (Employee e : this) {
                    if (e.getName().toLowerCase().contains(lower)) {
                        result.add(e);
                    }
                }
                break;
            }
            case "3": {
                String roleInput = inputter.getString("Enter role: ");
                if (!Acceptable.isValidRole(roleInput)) {
                    System.out.println("Invalid role.");
                    return;
                }
                String role = Acceptable.normalizeRole(roleInput);
                for (Employee e : this) {
                    if (e.getRole().equalsIgnoreCase(role)) {
                        result.add(e);
                    }
                }
                break;
            }
            case "4": {
                String statusInput = inputter.getString("Enter status [active/inactive]: ");
                if (!Acceptable.isValid(statusInput, Acceptable.STATUS_VALID)) {
                    System.out.println("Invalid status.");
                    return;
                }
                String status = Acceptable.normalizeStatus(statusInput);
                for (Employee e : this) {
                    if (e.getStatus().equalsIgnoreCase(status)) {
                        result.add(e);
                    }
                }
                break;
            }
            default:
                System.out.println("Invalid search option.");
                return;
        }

        if (result.isEmpty()) {
            System.out.println("No employee matches the search criteria.");
        } else {
            showAll(result, "Search Results:", "No employee matches the search criteria.");
        }
    }

    public void showAll() {
        showAll(this, "Employee List:", "No employee data available.");
    }

    public void showAll(List<Employee> list, String title, String emptyMessage) {
        if (list == null || list.isEmpty()) {
            System.out.println(emptyMessage);
            return;
        }
        System.out.println(title);
        System.out.println(TABLE_HEADER);
        for (Employee e : list) {
            System.out.println(e);
        }
        System.out.println(TABLE_FOOTER);
    }

    private void showEmployeeDetail(Employee employee) {
        System.out.println("-----------------------------------------------------");
        System.out.printf("ID          : %s%n", employee.getId());
        System.out.printf("Name        : %s%n", employee.getName());
        System.out.printf("Role        : %s%n", employee.getRole());
        System.out.printf("Base Salary : %,.0f%n", employee.getBaseSalary());
        System.out.printf("Working Days: %d%n", employee.getWorkingDays());
        System.out.printf("Bonus       : %,.0f%n", employee.getBonus());
        System.out.printf("Status      : %s%n", employee.getStatus());
        System.out.println("-----------------------------------------------------");
    }
}
