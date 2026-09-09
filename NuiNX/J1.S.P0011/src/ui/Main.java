package ui;

import bo.BaseInputter;

import entity.Base;
import entity.BaseNumber;

import utils.Validator;

/**
 *
 */
public class Main {

    /**
     * Lớp Main dùng để chạy chương trình chuyển đổi hệ cơ số.
     *
     * @author yourName
     */
    public static void main(String[] args) {
        // Lưu số đầu vào của người dùng.
        BaseNumber number = null;

        // Đối tượng dùng để nhập dữ liệu.
        BaseInputter inputter = new BaseInputter();

        while (true) {
            try {
                // Nhập số và hệ cơ số đầu vào.
                number = inputter.input();

                if (number == null) {
                    System.out.println("Exit");
                    break;
                }

                // Yêu cầu chọn hệ cơ số đầu ra.
                int choice = Validator.getInt(
                        "Enter output base (1-BIN, 2-DEC, 3-HEX): ",
                        "Just 1->3", "Invalid!", 1, 3);
                BaseNumber result = null;
                // Thực hiện chuyển đổi theo hệ cơ số được chọn.
                switch (choice) {
                    case 1:
                        result = number.getOutputByBase(Base.BIN);
                        break;
                    case 2:
                        result = number.getOutputByBase(Base.DEC);
                        break;
                    case 3:
                        result = number.getOutputByBase(Base.HEX);
                        break;
                }
                // Hiển thị kết quả chuyển đổi.
                System.out.println("Number after convert: " + result.getNumber());
                System.out.println("---------------------------------");
            } catch (Exception e) {
                // Hiển thị lỗi nếu có ngoại lệ xảy ra.
                System.out.println(e.getMessage());
            }
        }
    }
}
