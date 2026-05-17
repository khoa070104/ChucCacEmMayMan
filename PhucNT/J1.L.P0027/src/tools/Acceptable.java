package tools;

public interface Acceptable {

    String STUDENT_ID = "^[CcDdHhSsQq][Ee]\\d{6}$";
    String NAME_VALID = "^.{2,20}$";
    String DOUBLE_VALID = "^\\d+(\\.\\d+)?$";
    String INTEGER_VALID = "^\\d+$";
    String PHONE_VALID = "^(03[2-9]|05[25689]|07[06-9]|08[1-5]|09\\d)\\d{7}$";
    String VIETTEL_VALID = "^(032|033|034|035|036|037|038|039|086|096|097|098)\\d{7}$";
    String VNPT_VALID = "^(081|082|083|084|085|088|091|094)\\d{7}$";
    String EMAIL_VALID = "^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,}$";
    String MOUNTAIN_CODE_VALID = "^MT(0[1-9]|1[0-3])$";
    String CAMPUS_CODE_VALID = "^[CcDdHhSsQq][Ee]$";
    String YES_NO_VALID = "^[YyNn]$";

    static boolean isValid(String data, String pattern) {
        return data != null && data.matches(pattern);
    }

    static boolean isViettelOrVnpt(String phone) {
        return isValid(phone, VIETTEL_VALID) || isValid(phone, VNPT_VALID);
    }
}
