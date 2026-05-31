package model;

/**
 * Kiểm tra tính hợp lệ của dữ liệu nhập.
 *
 * @author LinhNDM
 */
public final class Validator {

    private Validator() {
    }

    /**
     * Kiểm tra chuỗi nhập có phải Y hoặc N hay không.
     *
     * @param input giá trị cần kiểm tra
     * @return true nếu hợp lệ
     */
    public static boolean isYesNo(String input) {
        return input != null && input.trim().matches("[YyNn]");
    }

    /**
     * Kiểm tra người dùng chọn tiếp tục (Y).
     *
     * @param input giá trị cần kiểm tra
     * @return true nếu chọn Y
     */
    public static boolean isContinue(String input) {
        return input != null && input.trim().equalsIgnoreCase("Y");
    }

    /**
     * Kiểm tra chuỗi có phải số thực dương hay không.
     *
     * @param input giá trị cần kiểm tra
     * @return true nếu hợp lệ
     */
    public static boolean isPositiveDouble(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }
        try {
            return Double.parseDouble(input.trim()) > 0;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    /**
     * Kiểm tra chuỗi có phải số nguyên dương hay không.
     *
     * @param input giá trị cần kiểm tra
     * @return true nếu hợp lệ
     */
    public static boolean isPositiveInteger(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }
        try {
            return Integer.parseInt(input.trim()) > 0;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    /**
     * Kiểm tra lựa chọn menu có nằm trong phạm vi cho phép hay không.
     *
     * @param input giá trị cần kiểm tra
     * @param min giá trị nhỏ nhất
     * @param max giá trị lớn nhất
     * @return true nếu hợp lệ
     */
    public static boolean isValidMenuChoice(String input, int min, int max) {
        int choice;
        if (input == null || !input.matches("\\d+")) {
            return false;
        }
        choice = Integer.parseInt(input);
        return choice >= min && choice <= max;
    }
}
