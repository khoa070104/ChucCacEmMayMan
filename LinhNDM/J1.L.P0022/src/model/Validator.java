package model;

import common.Constants;
import java.util.Calendar;

/**
 * Kiểm tra tính hợp lệ của dữ liệu ứng viên.
 *
 * @author LinhNDM
 */
public final class Validator {

    private Validator() {
    }

    /**
     * Kiểm tra năm sinh hợp lệ.
     *
     * @param input chuỗi năm sinh
     * @return true nếu hợp lệ
     */
    public static boolean isValidBirthYear(String input) {
        int year;
        int currentYear;
        if (input == null || !input.matches(Constants.REGEX_FOUR_DIGIT_YEAR)) {
            return false;
        }
        year = Integer.parseInt(input);
        currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return year >= Constants.MIN_BIRTH_YEAR && year <= currentYear;
    }

    /**
     * Kiểm tra số điện thoại hợp lệ.
     *
     * @param phone số điện thoại
     * @return true nếu hợp lệ
     */
    public static boolean isValidPhone(String phone) {
        return phone != null && phone.matches(Constants.REGEX_PHONE);
    }

    /**
     * Kiểm tra email hợp lệ.
     *
     * @param email địa chỉ email
     * @return true nếu hợp lệ
     */
    public static boolean isValidEmail(String email) {
        return email != null && email.matches(Constants.REGEX_EMAIL);
    }

    /**
     * Kiểm tra số năm kinh nghiệm hợp lệ.
     *
     * @param input chuỗi số năm kinh nghiệm
     * @return true nếu hợp lệ
     */
    public static boolean isValidExpInYear(String input) {
        int years;
        if (input == null || !input.matches(Constants.REGEX_POSITIVE_INTEGER)) {
            return false;
        }
        years = Integer.parseInt(input);
        return years >= Constants.MIN_EXP_IN_YEAR
                && years <= Constants.MAX_EXP_IN_YEAR;
    }

    /**
     * Kiểm tra xếp loại tốt nghiệp hợp lệ.
     *
     * @param rank xếp loại tốt nghiệp
     * @return true nếu hợp lệ
     */
    public static boolean isValidGraduationRank(String rank) {
        String validRank;
        if (rank == null || rank.trim().isEmpty()) {
            return false;
        }
        for (int i = 0; i < Constants.GRADUATION_RANKS.length; i++) {
            validRank = Constants.GRADUATION_RANKS[i];
            if (validRank.equalsIgnoreCase(rank.trim())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Chuẩn hóa xếp loại tốt nghiệp theo định dạng chuẩn.
     *
     * @param rank xếp loại nhập vào
     * @return xếp loại đã chuẩn hóa
     */
    public static String normalizeGraduationRank(String rank) {
        String validRank;
        for (int i = 0; i < Constants.GRADUATION_RANKS.length; i++) {
            validRank = Constants.GRADUATION_RANKS[i];
            if (validRank.equalsIgnoreCase(rank.trim())) {
                return validRank;
            }
        }
        return rank.trim();
    }

    /**
     * Kiểm tra lựa chọn Y/N hợp lệ.
     *
     * @param input lựa chọn nhập vào
     * @return true nếu hợp lệ
     */
    public static boolean isYesNo(String input) {
        return input != null && input.trim().matches(Constants.YES_NO_VALIDATE);
    }

    /**
     * Kiểm tra người dùng chọn tiếp tục.
     *
     * @param input lựa chọn nhập vào
     * @return true nếu chọn Y
     */
    public static boolean isContinue(String input) {
        return input != null && input.trim().equalsIgnoreCase("Y");
    }

    /**
     * Kiểm tra loại ứng viên hợp lệ.
     *
     * @param input loại ứng viên nhập vào
     * @return true nếu hợp lệ
     */
    public static boolean isValidCandidateType(String input) {
        return input != null && input.matches(Constants.REGEX_CANDIDATE_TYPE);
    }
}
