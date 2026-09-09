/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Scanner;

/**
 *
 */
public class Validator {

    private Scanner scanner;

    /**
     * Khởi tạo đối tượng Validator cùng với Scanner đọc input từ bàn phím.
     */
    public Validator() {
        scanner = new Scanner(System.in);
    }

    /**
     * Trả về giá trị nguyên hợp lệ được nhập từ bàn phím, lặp lại cho đến khi nhập đúng.
     *
     * @param messageInfo: Thông báo hướng dẫn người dùng nhập dữ liệu.
     * @param messageErrorOutOfRange: Thông báo lỗi khi giá trị nằm ngoài khoảng cho phép.
     * @param messageErrorInvalidNumber: Thông báo lỗi khi chuỗi nhập không phải số nguyên.
     * @param min: Giới hạn tối thiểu.
     * @param max: Giới hạn tối đa.
     * @return: Giá trị nguyên hợp lệ được nhập từ bàn phím.
     */
    public int getInt(
            String messageInfo,
            String messageErrorOutOfRange,
            String messageErrorInvalidNumber,
            int min,
            int max) {

        // Lặp vô hạn cho đến khi người dùng nhập đúng giá trị hợp lệ.
        do {
            try {
                System.out.println(messageInfo);
                int number = Integer.parseInt(scanner.nextLine());

                // Nếu giá trị nằm trong khoảng [min, max] thì trả về kết quả.
                if (number >= min && number <= max) {
                    return number;
                }

                // Nếu giá trị nằm ngoài khoảng cho phép thì thông báo lỗi.
                System.out.println(messageErrorOutOfRange);
            } catch (NumberFormatException e) {

                // Nếu nhập sai định dạng số nguyên thì báo lỗi.
                System.out.println(messageErrorInvalidNumber);
            }
        } while (true);
    }
}
