package tools;

public interface Acceptable {

    String CLUB_ID_VALID = "^CL-\\d{4}$";
    String PLAYER_ID_VALID = "^P\\d{4}$";
    String NON_EMPTY_VALID = "^.+$";
    String INTEGER_VALID = "^\\d+$";
    String POSITIVE_REAL_VALID = "^\\d+(\\.\\d+)?$";
    String SHIRT_NUMBER_VALID = "^([1-9]|[1-9]\\d)$";

    static boolean isValid(String data, String pattern) {
        return data != null && data.matches(pattern);
    }

    static boolean isPositiveReal(String data) {
        if (!isValid(data, POSITIVE_REAL_VALID)) {
            return false;
        }
        return Double.parseDouble(data) > 0;
    }

    static boolean isValidPosition(String position) {
        if (position == null || position.trim().isEmpty()) {
            return false;
        }
        String[] positions = {"Goalkeeper", "Defender", "Midfielder", "Forward", "Winger"};
        for (String p : positions) {
            if (p.equalsIgnoreCase(position.trim())) {
                return true;
            }
        }
        return false;
    }

    static String normalizePosition(String position) {
        String[] positions = {"Goalkeeper", "Defender", "Midfielder", "Forward", "Winger"};
        for (String p : positions) {
            if (p.equalsIgnoreCase(position.trim())) {
                return p;
            }
        }
        return position.trim();
    }
}
