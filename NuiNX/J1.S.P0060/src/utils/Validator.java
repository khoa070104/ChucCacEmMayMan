package utils;

import java.util.Scanner;

/**
 * Lớp Validator dùng để kiểm tra và xử lý dữ liệu nhập vào từ bàn phím.
 *
 * @version 29/09/2026
 */
public class Validator {

    private final Scanner scanner;

    /**
     * Khởi tạo đối tượng Validator cùng với Scanner đọc input từ System.in.
     */
    public Validator() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Khởi tạo đối tượng Validator với Scanner tùy chỉnh để hỗ trợ kiểm thử.
     *
     * @param scanner đối tượng Scanner
     */
    public Validator(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Trả về giá trị nguyên hợp lệ được nhập từ bàn phím trong khoảng [min, max].
     *
     * @param messageInfo thông báo hướng dẫn người dùng nhập dữ liệu
     * @param messageErrorOutOfRange thông báo lỗi khi giá trị nằm ngoài khoảng cho phép
     * @param messageErrorInvalidNumber thông báo lỗi khi chuỗi nhập không phải số nguyên
     * @param min giới hạn tối thiểu (bao gồm cả min)
     * @param max giới hạn tối đa (bao gồm cả max)
     * @return giá trị nguyên hợp lệ được nhập từ bàn phím
     */
    public int getInt(
            String messageInfo,
            String messageErrorOutOfRange,
            String messageErrorInvalidNumber,
            int min,
            int max) {
        do {
            try {
                System.out.print(messageInfo);
                String input = scanner.nextLine().trim();
                int number = Integer.parseInt(input);
                if (number >= min && number <= max) {
                    return number;
                }
                System.out.println(messageErrorOutOfRange);
            } catch (NumberFormatException e) {
                System.out.println(messageErrorInvalidNumber);
            }
        } while (true);
    }
}
