package bo;

import entity.Base;
import entity.BaseNumber;

import utils.Validator;

/**
 * Lớp BaseInputter dùng để nhập hệ cơ số và giá trị số từ bàn phím.
 *
 * @author yourName
 */
public class BaseInputter {

    // Lưu hệ cơ số người dùng chọn.

    private Base base;

    // Lưu giá trị số người dùng nhập.
    private String number;

    /**
     * Nhập hệ cơ số và giá trị số hợp lệ từ người dùng.
     *
     * @return đối tượng BaseNumber chứa hệ cơ số và giá trị số
     * @throws Exception nếu dữ liệu không hợp lệ
     */
    public BaseNumber input() throws Exception {
        // Yêu cầu người dùng chọn hệ cơ số đầu vào.
        int choice = Validator.getInt(
                "Enter input base (1-BIN, 2-DEC, 3-HEX, 4-Exit): ",
                "Just 1->4", "Invalid!", 1, 4);

        if (choice == 4) {
            return null;
        }

        // Kiểm tra lựa chọn và nhập số phù hợp với từng hệ cơ số.
        switch (choice) {
            case 1:
                base = Base.BIN;
                number = Validator.getString("Enter a number: ", "Not a valid number", "[01]+");
                break;
            case 2:
                base = Base.DEC;
                number = Validator.getString("Enter a number: ", "Not a valid number", "[0-9]+");
                break;
            case 3:
                base = Base.HEX;
                number =
                        Validator.getString(
                                        "Enter a number: ", "Not a valid number", "[0-9A-Fa-f]+")
                                .toUpperCase();
                break;
        }
        // Trả về đối tượng số đã nhập.
        return new BaseNumber(base, number);
    }
}
