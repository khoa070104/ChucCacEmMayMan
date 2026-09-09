package utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Lớp tiện ích cung cấp các phương thức kiểm tra và chuẩn hóa đầu vào người
 * dùng.
 */
public class Validator {

    /**
     * Đối tượng Scanner dùng để đọc đầu vào từ bàn phím.
     */
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Constructor mặc định bị khóa để tránh khởi tạo lớp tiện ích.
     */
    private Validator() {}

    /**
     * Lấy một số nguyên trong khoảng cho phép từ người dùng.
     */
    public static int getInt(
            String messageInfo,
            String messageErrorOutOfRange,
            String messageErrorInvalidNumber,
            int min,
            int max) {
        do {
            try {
                System.out.print(messageInfo);
                int number = Integer.parseInt(SCANNER.nextLine().trim());
                // Kiểm tra phạm vi.
                if (number >= min && number <= max) {
                    return number;
                }
                System.out.println(messageErrorOutOfRange);
            } catch (NumberFormatException e) {
                // Xử lý lỗi nếu không phải là số.
                System.out.println(messageErrorInvalidNumber);
            }
        } while (true);
    }

    /**
     * Lấy một số thực trong khoảng cho phép từ người dùng.
     */
    public static double getDouble(
            String messageInfo,
            String messageErrorOutOfRange,
            String messageErrorInvalidNumber,
            double min,
            double max) {
        do {
            try {
                System.out.print(messageInfo);
                double number = Double.parseDouble(SCANNER.nextLine().trim());
                // Kiểm tra phạm vi.
                if (number >= min && number <= max) {
                    return number;
                }
                System.out.println(messageErrorOutOfRange);
            } catch (NumberFormatException e) {
                // Xử lý lỗi nếu không phải là số.
                System.out.println(messageErrorInvalidNumber);
            }
        } while (true);
    }

    /**
     * Lấy một chuỗi hợp lệ theo định dạng Regex.
     */
    public static String getString(String messageInfo, String messageError, final String REGEX) {
        do {
            System.out.print(messageInfo);
            String str = SCANNER.nextLine().trim();
            // Kiểm tra định dạng.
            if (str.matches(REGEX)) {
                return str;
            }
            System.out.println(messageError);
        } while (true);
    }

    /**
     * Lấy một ngày tháng hợp lệ theo định dạng Regex.
     */
    public static Date getDate(
            String messageInfo, String messageErrorInvalidDate, final String REGEX) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(REGEX);
        dateFormat.setLenient(false);
        while (true) {
            System.out.print(messageInfo);
            try {
                Date date = dateFormat.parse(SCANNER.nextLine());
                return date;
            } catch (ParseException e) {
                System.err.println(messageErrorInvalidDate);
            }
        }
    }

    /**
     * Mã hóa chuỗi đầu vào sang định dạng MD5.
     */
    public static String getMD5(String input) {
        try {
            // Tạo đối tượng MessageDigest với thuật toán MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Băm chuỗi input thành mảng byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Chuyển mảng byte sang số nguyên lớn
            BigInteger no = new BigInteger(1, messageDigest);

            // Chuyển sang hệ hex (cơ số 16)
            String hashtext = no.toString(16);

            // Thêm số 0 vào đầu nếu thiếu (đảm bảo đủ 32 ký tự)
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
