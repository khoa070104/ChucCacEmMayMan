package view;

import common.Constants;
import common.Messages;
import java.util.Scanner;
import model.Validator;

/**
 * Xử lý nhập liệu từ bàn phím và validate dữ liệu nhập.
 *
 * @author LinhNDM
 */
public final class Inputter {

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

    /**
     * Nhập lựa chọn Y hoặc N.
     *
     * @param label nhãn gợi ý nhập
     * @return Y hoặc N
     */
    public static String inputYesNo(String label) {
        String inputValue;
        do {
            inputValue = inputRequired(label);
            if (!Validator.isYesNo(inputValue)) {
                System.out.println(Messages.ERR_INVALID_YES_NO);
            }
        } while (!Validator.isYesNo(inputValue));
        return inputValue.trim();
    }

    /**
     * Nhập giá bán dương.
     *
     * @param label nhãn gợi ý nhập
     * @return giá hợp lệ
     */
    public static double inputPositivePrice(String label) {
        String inputValue;
        do {
            inputValue = inputRequired(label);
            if (!Validator.isPositiveDouble(inputValue)) {
                System.out.println(Messages.ERR_INVALID_PRICE);
            }
        } while (!Validator.isPositiveDouble(inputValue));
        return Double.parseDouble(inputValue);
    }

    /**
     * Nhập số lượng nguyên dương.
     *
     * @param label nhãn gợi ý nhập
     * @return số lượng hợp lệ
     */
    public static int inputPositiveQuantity(String label) {
        String inputValue;
        do {
            inputValue = inputRequired(label);
            if (!Validator.isPositiveInteger(inputValue)) {
                System.out.println(Messages.ERR_INVALID_QUANTITY);
            }
        } while (!Validator.isPositiveInteger(inputValue));
        return Integer.parseInt(inputValue);
    }

    /**
     * Nhập số lượng nguyên dương không vượt quá tồn kho.
     *
     * @param label nhãn gợi ý nhập
     * @param maxQuantity số lượng tồn kho tối đa
     * @return số lượng hợp lệ
     */
    public static int inputPositiveQuantity(String label, int maxQuantity) {
        String inputValue;
        int quantity;
        do {
            inputValue = inputRequired(label);
            if (!Validator.isPositiveInteger(inputValue)) {
                System.out.println(Messages.ERR_INVALID_QUANTITY);
                continue;
            }
            quantity = Integer.parseInt(inputValue);
            if (quantity > maxQuantity) {
                System.out.println(Messages.ERR_NOT_ENOUGH_STOCK + maxQuantity);
                inputValue = null;
            }
        } while (inputValue == null || !Validator.isPositiveInteger(inputValue)
                || Integer.parseInt(inputValue) > maxQuantity);
        return Integer.parseInt(inputValue);
    }
}
