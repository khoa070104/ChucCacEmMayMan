package utils;

import java.util.Scanner;

/**
 * Lớp Validator dùng để đọc dữ liệu nhập vào từ bàn phím.
 *
 * @version 22/09/2026
 */
public class Validator {

    private final Scanner scanner;

    public Validator() {
        scanner = new Scanner(System.in);
    }

    /**
     * Trả về chuỗi được nhập từ bàn phím.
     *
     * @param messageInfo thông báo hướng dẫn người dùng nhập dữ liệu
     * @return chuỗi người dùng đã nhập
     */
    public String getString(String messageInfo) {
        System.out.println(messageInfo);
        return scanner.nextLine();
    }
}
