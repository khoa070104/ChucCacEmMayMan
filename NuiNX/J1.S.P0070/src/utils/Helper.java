package utils;

import java.util.Random;

/**
 * Lớp Helper hỗ trợ hiển thị menu và tạo captcha.
 */
public class Helper {

    /**
     * Hiển thị menu chọn ngôn ngữ.
     */
    public static void menu() {
        System.out.println("\n-------Login Program--------");
        System.out.println("1. Vietnamese");
        System.out.println("2. English");
        System.out.println("3. Exit");
    }

    /**
     * Tạo chuỗi ký tự alphanumeric (A-Z, a-z, 0-9).
     *
     * @return Chuỗi gồm chữ hoa, chữ thường và số
     */
    private static String genAlphaNumeric() {
        // Chuỗi rỗng lưu chữ cái
        String alpha = "";

        String number = "0123456789";

        // Chuỗi kết quả sau khi gộp
        String alphaNumeric = "";

        // Biến chạy bắt đầu từ chữ cái A viết hoa
        char c = 'A';

        // Vòng lặp thêm tất cả chữ cái in hoa (A-Z) vào chuỗi alpha
        while (c <= 'Z') {
            alpha += c;
            c++;
        }

        // Kết hợp chữ in hoa, chữ in thường và dãy số để tạo chuỗi đầy đủ
        alphaNumeric = alpha + alpha.toLowerCase() + number;
        return alphaNumeric;
    }

    /**
     * Tạo chuỗi captcha ngẫu nhiên.
     *
     * @param length Độ dài captcha
     * @return Chuỗi captcha ngẫu nhiên
     */
    public static String generateCaptcha(int length) {
        // Lấy chuỗi ký tự đã chuẩn hóa
        String alphaNumeric = genAlphaNumeric();

        // Chuỗi tích lũy các ký tự Captcha ngẫu nhiên
        String captchaGen = "";

        // Vòng lặp chọn ngẫu nhiên từng vị trí ký tự để ghép thành mã Captcha
        for (int i = 0; i < length; i++) {
            captchaGen += alphaNumeric.charAt(new Random().nextInt(alphaNumeric.length()));
        }

        return captchaGen;
    }
}
