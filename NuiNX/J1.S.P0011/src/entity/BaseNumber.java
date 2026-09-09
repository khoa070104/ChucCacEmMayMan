package entity;

import java.math.BigInteger;

/**
 * Lớp BaseNumber dùng để lưu trữ số và thực hiện chuyển đổi hệ cơ số.
 *
 * @author yourName
 */
public class BaseNumber {

    // Lưu hệ cơ số của số hiện tại.

    private Base base;

    // Lưu giá trị số dưới dạng chuỗi.
    private String number;

    /**
     * Khởi tạo đối tượng BaseNumber.
     *
     * @param base hệ cơ số
     * @param number giá trị số
     * @throws Exception nếu số không hợp lệ với hệ cơ số
     */
    public BaseNumber(Base base, String number) throws Exception {
        // Kiểm tra tính hợp lệ của số theo hệ cơ số.
        if (isValidNumber(base, number)) {
            this.base = base;
            this.number = number;
        } else {
            throw new Exception("Invalid number of base");
        }
    }

    /**
     * Kiểm tra số có hợp lệ với hệ cơ số hay không.
     *
     * @param base hệ cơ số
     * @param number giá trị số
     * @return true nếu hợp lệ, false nếu không hợp lệ
     */
    private boolean isValidNumber(Base base, String number) {
        switch (base) {
            case BIN:
                return number.matches("[01]+");
            case DEC:
                return number.matches("[0-9]+");
            case HEX:
                return number.matches("[0-9A-F]+");
            default:
                throw new AssertionError();
        }
    }

    /**
     * Lấy hệ cơ số hiện tại.
     *
     * @return hệ cơ số
     */
    public Base getBase() {
        return base;
    }

    /**
     * Cập nhật hệ cơ số.
     *
     * @param base hệ cơ số mới
     */
    public void setBase(Base base) {
        this.base = base;
    }

    /**
     * Lấy giá trị số.
     *
     * @return giá trị số
     */
    public String getNumber() {
        return number;
    }

    /**
     * Cập nhật giá trị số.
     *
     * @param number giá trị số mới
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Chuyển số hiện tại sang hệ thập phân(Decimal).
     *
     * @return số ở hệ thập phân
     * @throws Exception nếu xảy ra lỗi
     */
    private BaseNumber convertToDec() throws Exception {
        // Lưu kết quả số thập phân sau khi chuyển đổi.
        BigInteger decNum = BigInteger.ZERO;

        // Lấy giá trị cơ số hiện tại (2, 10 hoặc 16).
        BigInteger base = BigInteger.valueOf(this.base.value());

        // Bien tich luy bat dau tu base ^ 0
        BigInteger power = BigInteger.ONE;

        // Duyệt từng ký tự từ phải sang trái để tính giá trị vị trí.
        for (int i = number.length() - 1; i >= 0; i--) {

            // Lấy ký tự số tại vị trí hiện tại.
            char digit = number.charAt(i);

            // Chuyển ký tự thành giá trị số
            BigInteger digitValue = BigInteger.valueOf(Character.getNumericValue(digit));

            // Cộng giá trị chữ số * lũy thừa cơ số vào kết quả.
            decNum = decNum.add(digitValue.multiply(power));

            // Tăng lũy thừa cơ số lên base^(n+1).
            power = power.multiply(base);
        }
        // Trả về đối tượng BaseNumber ở hệ thập phân.
        return new BaseNumber(Base.DEC, decNum.toString());
    }

    /**
     * Chuyển số thập phân(Decimal) sang hệ cơ số đầu ra.
     *
     * @param outBase hệ cơ số đích
     * @return số sau khi chuyển đổi
     * @throws Exception nếu xảy ra lỗi
     */
    private BaseNumber convertDecToOut(Base outBase) throws Exception {
        // Chuyển số hiện tại sang hệ thập phân trước.
        BigInteger decNum = new BigInteger(convertToDec().number);

        // Lưu kết quả chuyển đổi dưới dạng chuỗi.
        StringBuilder reverseResult = new StringBuilder();

        // Chia liên tiếp cho cơ số đích đến khi thương bằng 0.
        while (decNum.compareTo(BigInteger.ZERO) != 0) {
            // Lấy phần dư sau phép chia.
            int remainNum = decNum.mod(BigInteger.valueOf(outBase.value())).intValue();

            // Tinh thuong va Cập nhật giá trị thương cho lần chia tiếp theo.
            decNum = decNum.divide(BigInteger.valueOf(outBase.value()));

            // Chuyển giá trị từ 10-15 sang A-F.
            switch (remainNum) {
                case 10:
                    reverseResult.insert(0, "A");
                    break;
                case 11:
                    reverseResult.insert(0, "B");
                    break;
                case 12:
                    reverseResult.insert(0, "C");
                    break;
                case 13:
                    reverseResult.insert(0, "D");
                    break;
                case 14:
                    reverseResult.insert(0, "E");
                    break;
                case 15:
                    reverseResult.insert(0, "F");
                    break;
                default:
                    // Thêm trực tiếp các số từ 0-9 vào kết quả.
                    reverseResult.insert(0, remainNum);
            }
        }
        // Gán giá trị 0 nếu kết quả rỗng.
        if (reverseResult.toString().trim().isEmpty()) {
            reverseResult.insert(0, "0");
        }
        // Tạo đối tượng BaseNumber chứa kết quả chuyển đổi.
        BaseNumber result = new BaseNumber(outBase, reverseResult.toString());
        return result;
    }

    /**
     * Lấy kết quả chuyển đổi theo hệ cơ số đầu ra.
     *
     * @param outBase hệ cơ số đầu ra
     * @return số sau khi chuyển đổi
     * @throws Exception nếu xảy ra lỗi
     */
    public BaseNumber getOutputByBase(Base outBase) throws Exception {
        // Trả về kết quả chuyển đổi theo hệ cơ số mong muốn.
        return convertDecToOut(outBase);
    }
}
