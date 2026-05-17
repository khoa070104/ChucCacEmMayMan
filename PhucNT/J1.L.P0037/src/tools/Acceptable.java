package tools;

public interface Acceptable {

    String EMPLOYEE_ID_VALID = "^E\\d{3}$";
    String NON_EMPTY_VALID = "^.+$";
    String POSITIVE_REAL_VALID = "^\\d+(\\.\\d+)?$";
    String NON_NEGATIVE_REAL_VALID = "^\\d+(\\.\\d+)?$";
    String INTEGER_VALID = "^\\d+$";
    String WORKING_DAYS_VALID = "^(?:[0-9]|1[0-9]|2[0-6])$";
    String STATUS_VALID = "^(?i)(active|inactive)$";
    String YES_NO_VALID = "^[YyNn]$";

    static boolean isValid(String data, String pattern) {
        return data != null && data.matches(pattern);
    }

    static boolean isPositiveReal(String data) {
        if (!isValid(data, POSITIVE_REAL_VALID)) {
            return false;
        }
        return Double.parseDouble(data) > 0;
    }

    static boolean isNonNegativeReal(String data) {
        if (!isValid(data, NON_NEGATIVE_REAL_VALID)) {
            return false;
        }
        return Double.parseDouble(data) >= 0;
    }

    static boolean isValidRole(String role) {
        if (role == null || role.trim().isEmpty()) {
            return false;
        }
        String[] roles = {"Developer", "Tester", "Manager", "HR"};
        for (String r : roles) {
            if (r.equalsIgnoreCase(role.trim())) {
                return true;
            }
        }
        return false;
    }

    static String normalizeRole(String role) {
        String[] roles = {"Developer", "Tester", "Manager", "HR"};
        for (String r : roles) {
            if (r.equalsIgnoreCase(role.trim())) {
                return r;
            }
        }
        return role.trim();
    }

    static String normalizeStatus(String status) {
        return status.equalsIgnoreCase("active") ? "active" : "inactive";
    }
}
