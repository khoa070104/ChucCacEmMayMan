package utils;

import java.util.Scanner;

/**
 * Lớp Validator dùng để kiểm tra dữ liệu nhập vào từ bàn phím.
 *
 * @version 18/09/2026
 */
public class Validator {

    private Scanner scanner;

    public Validator() {
        scanner = new Scanner(System.in);
    }

    public int getInt(
            String messageInfo,
            String messageErrorOutOfRange,
            String messageErrorInvalidNumber,
            int min,
            int max) {
        do {
            try {
                System.out.println(messageInfo);
                int number = Integer.parseInt(scanner.nextLine().trim());
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
