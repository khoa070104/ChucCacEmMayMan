package utils;

import java.util.Scanner;

/**
 * Lớp Validate cung cấp các công cụ để kiểm tra và lấy dữ liệu nhập vào từ bàn phím.
 * Lớp đảm bảo người dùng nhập đúng kiểu dữ liệu và định dạng yêu cầu.
 */
public class Validate {

    /** Đối tượng Scanner dùng để đọc dữ liệu từ bàn phím. */
    private static final Scanner SCANNER = new Scanner(System.in);

    /** Constructor private ngăn chặn việc khởi tạo instance của lớp tiện ích này. */
    private Validate() {}

    /**
     * Yêu cầu người dùng nhập một số nguyên trong khoảng [min, max].
     * Lặp lại cho đến khi người dùng nhập đúng.
     */
    public static int getInt(
            String messageInfo,
            String messageErrorOutOfRange,
            String messageErrorNumber,
            int min,
            int max) {
        do {
            try {
                // Hiển thị thông báo yêu cầu người dùng nhập liệu
                System.out.print(messageInfo);

                // Đọc dữ liệu từ bàn phím và chuyển đổi thành số nguyên
                int number = Integer.parseInt(SCANNER.nextLine().trim());

                // Kiểm tra xem số vừa nhập có nằm trong phạm vi cho phép hay không
                if (number >= min && number <= max) {
                    return number;
                } else {
                    // Thông báo lỗi nếu số nằm ngoài khoảng quy định
                    System.out.println(messageErrorOutOfRange);
                }
            } catch (NumberFormatException e) {
                // Xử lý lỗi nếu người dùng nhập không phải là số (nhập chữ cái hoặc ký tự đặc biệt)
                System.out.println(messageErrorNumber);
            }
        } while (true);
    }

    /**
     * Yêu cầu người dùng nhập một chuỗi thỏa mãn định dạng cho trước (dùng biểu thức chính quy).
     * Lặp lại cho đến khi người dùng nhập đúng định dạng.
     */
    public static String getString(String messageInfo, String messageError, final String REGEX) {
        do {
            System.out.print(messageInfo);
            String str = SCANNER.nextLine().trim();

            // Kiểm tra xem chuỗi nhập vào có khớp với định dạng quy định hay không
            if (str.matches(REGEX)) {
                return str;
            }
            // Thông báo lỗi nếu chuỗi nhập vào sai định dạng
            System.out.println(messageError);
        } while (true);
    }

    /**
     * Yêu cầu người dùng nhập mã Captcha để xác thực.
     * Lặp lại cho đến khi mã nhập vào khớp với mã được hệ thống tạo ra.
     */
    public static boolean verifyCaptcha(
            String message, String messageError, String captchaGenerate) {
        String captchaInput;
        while (true) {
            System.out.printf(message);
            captchaInput = SCANNER.nextLine().trim();

            // So sánh mã người dùng nhập với mã thực tế được sinh ra
            if (captchaGenerate.equals(captchaInput)) {
                // Thoát vòng lặp khi nhập đúng mã
                break;
            }
            // Thông báo lỗi nếu nhập sai mã
            System.out.println(messageError);
        }
        return true;
    }
}
