package entity;

/**
 * Enum Base dùng để quản lý các hệ cơ số, được hỗ trợ trong chương trình chuyển
 * đổi số. Bao gồm nhị phân (2), thập phân (10) và thập lục phân (16).
 * @author yourName
 */
public enum Base {
    DEC(10),
    HEX(16),
    BIN(2);

    // Lưu giá trị của hệ cơ số.
    private int base;

    /**
     * Khởi tạo giá trị cơ số cho từng hệ đếm.
     *
     * @param base giá trị cơ số
     */
    private Base(int base) {
        this.base = base;
    }

    /**
     * Trả về đối tượng Base tương ứng với giá trị cơ số.
     *
     * @param type giá trị cơ số
     * @return hệ cơ số tương ứng
     */
    public static Base getBase(int type) {
        switch (type) {
            case 10:
                return DEC;
            case 16:
                return HEX;
            case 2:
                return BIN;
            default:
                throw new AssertionError();
        }
    }

    /**
     * Lấy giá trị cơ số.
     *
     * @return giá trị hệ cơ số
     */
    public int value() {
        return base;
    }
}
