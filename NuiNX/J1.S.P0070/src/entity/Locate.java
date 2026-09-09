package entity;

import java.util.Locale;

/**
 * Các ngôn ngữ được hỗ trợ trong chương trình Ebank.
 */
public enum Locate {
    VIETNAMESE(Locale.forLanguageTag("vi-VN")),
    ENGLISH(Locale.forLanguageTag("en-US"));

    private final Locale locale;

    /**
     * Khởi tạo một lựa chọn ngôn ngữ.
     *
     * @param locale đối tượng Locale tương ứng
     */
    Locate(Locale locale) {
        this.locale = locale;
    }

    /**
     * Lấy Locale dùng để đọc file ResourceBundle.
     *
     * @return Locale tương ứng với ngôn ngữ
     */
    public Locale getLocale() {
        return locale;
    }
}
