package view;

import common.Messages;
import java.util.Scanner;

/**
 * Xử lý nhập liệu từ bàn phím và validate dữ liệu nhập.
 *
 * @author LinhNDM
 */
public class Inputter {

    private static final Scanner SCANNER = new Scanner(System.in);

    private Inputter() {
    }

    /**
     * Nhập chuỗi theo nhãn hiển thị.
     *
     * @param label nhãn gợi ý nhập
     * @return giá trị người dùng nhập
     */
    public static String input(String label) {
        String inputValue;
        System.out.print(label);
        inputValue = SCANNER.nextLine();
        return inputValue;
    }

    /**
     * Nhập bắt buộc, không cho phép để trống.
     *
     * @param label nhãn gợi ý nhập
     * @return giá trị đã trim và không rỗng
     */
    public static String inputRequired(String label) {
        String inputValue;
        do {
            System.out.print(label);
            inputValue = SCANNER.nextLine();
            if (inputValue.trim().isEmpty()) {
                System.out.println(Messages.ERR_INPUT_EMPTY);
            }
        } while (inputValue.trim().isEmpty());
        return inputValue.trim();
    }

    /**
     * Nhập bắt buộc và khớp biểu thức regex.
     *
     * @param label nhãn gợi ý nhập
     * @param regex biểu thức validate
     * @return giá trị hợp lệ
     */
    public static String inputRequired(String label, String regex) {
        String inputValue;
        do {
            System.out.print(label);
            inputValue = SCANNER.nextLine();
            if (inputValue.trim().isEmpty()) {
                System.out.println(Messages.ERR_INPUT_EMPTY);
                continue;
            }
            if (!inputValue.matches(regex)) {
                System.out.println(Messages.ERR_INVALID_INPUT);
            }
        } while (inputValue.trim().isEmpty() || !inputValue.matches(regex));
        return inputValue.trim();
    }
}
