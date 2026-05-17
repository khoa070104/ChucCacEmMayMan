package tools;

import java.util.Scanner;

public class Inputter {

    private final Scanner ndl;

    public Inputter() {
        this.ndl = new Scanner(System.in);
    }

    public String getString(String mess) {
        System.out.print(mess);
        return ndl.nextLine().trim();
    }

    public int getInt(String mess) {
        String value;
        do {
            value = getString(mess);
        } while (!Acceptable.isValid(value, Acceptable.INTEGER_VALID));
        return Integer.parseInt(value);
    }

    public double getDouble(String mess) {
        String value;
        do {
            value = getString(mess);
        } while (!Acceptable.isValid(value, Acceptable.POSITIVE_REAL_VALID));
        return Double.parseDouble(value);
    }

    public String inputAndLoop(String mess, String pattern) {
        String data;
        do {
            data = getString(mess);
            if (!Acceptable.isValid(data, pattern)) {
                System.out.println("Invalid data. Please re-enter.");
            }
        } while (!Acceptable.isValid(data, pattern));
        return data;
    }

    public String inputPositiveReal(String mess) {
        String data;
        do {
            data = getString(mess);
            if (!Acceptable.isPositiveReal(data)) {
                System.out.println("Value must be a positive number. Please re-enter.");
            }
        } while (!Acceptable.isPositiveReal(data));
        return data;
    }

    public String inputNonNegativeReal(String mess) {
        String data;
        do {
            data = getString(mess);
            if (!Acceptable.isNonNegativeReal(data)) {
                System.out.println("Value must be >= 0. Please re-enter.");
            }
        } while (!Acceptable.isNonNegativeReal(data));
        return data;
    }

    public String inputWorkingDays(String mess) {
        String data;
        do {
            data = getString(mess);
            if (!Acceptable.isValid(data, Acceptable.WORKING_DAYS_VALID)) {
                System.out.println("Working days must be an integer from 0 to 26.");
            }
        } while (!Acceptable.isValid(data, Acceptable.WORKING_DAYS_VALID));
        return data;
    }

    public String inputRole() {
        String data;
        do {
            data = getString("Role [Developer/Tester/Manager/HR]: ");
            if (!Acceptable.isValidRole(data)) {
                System.out.println("Invalid role. Please choose Developer, Tester, Manager, or HR.");
            }
        } while (!Acceptable.isValidRole(data));
        return Acceptable.normalizeRole(data);
    }

    public String inputOptional(String mess, String pattern) {
        String data;
        while (true) {
            data = getString(mess);
            if (data.isEmpty()) {
                return "";
            }
            if (Acceptable.isValid(data, pattern)) {
                return data;
            }
            System.out.println("Invalid data. Please re-enter or leave blank to keep current value.");
        }
    }

    public String inputOptionalRole() {
        String data;
        while (true) {
            data = getString("New role (blank to keep) [Developer/Tester/Manager/HR]: ");
            if (data.isEmpty()) {
                return "";
            }
            if (Acceptable.isValidRole(data)) {
                return Acceptable.normalizeRole(data);
            }
            System.out.println("Invalid role. Please re-enter or leave blank to keep current value.");
        }
    }

    public String inputOptionalPositiveReal(String mess) {
        String data;
        while (true) {
            data = getString(mess);
            if (data.isEmpty()) {
                return "";
            }
            if (Acceptable.isPositiveReal(data)) {
                return data;
            }
            System.out.println("Value must be a positive number. Please re-enter or leave blank.");
        }
    }

    public String inputOptionalNonNegativeReal(String mess) {
        String data;
        while (true) {
            data = getString(mess);
            if (data.isEmpty()) {
                return "";
            }
            if (Acceptable.isNonNegativeReal(data)) {
                return data;
            }
            System.out.println("Value must be >= 0. Please re-enter or leave blank.");
        }
    }

    public String inputOptionalWorkingDays(String mess) {
        String data;
        while (true) {
            data = getString(mess);
            if (data.isEmpty()) {
                return "";
            }
            if (Acceptable.isValid(data, Acceptable.WORKING_DAYS_VALID)) {
                return data;
            }
            System.out.println("Working days must be from 0 to 26. Please re-enter or leave blank.");
        }
    }

    public String inputOptionalStatus() {
        String data;
        while (true) {
            data = getString("New status (blank to keep) [active/inactive]: ");
            if (data.isEmpty()) {
                return "";
            }
            if (Acceptable.isValid(data, Acceptable.STATUS_VALID)) {
                return Acceptable.normalizeStatus(data);
            }
            System.out.println("Status must be active or inactive. Please re-enter or leave blank.");
        }
    }

    public String inputYesNo(String mess) {
        String data;
        do {
            data = getString(mess);
        } while (!Acceptable.isValid(data, Acceptable.YES_NO_VALID));
        return data.toUpperCase();
    }
}
