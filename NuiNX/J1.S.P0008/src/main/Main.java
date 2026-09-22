package main;

import model.TextCounter;
import utils.Validator;

/**
 * Main Class dùng để chạy chương trình đếm từ và ký tự.
 *
 * @version 22/09/2026
 */
public class Main {

    public static void main(String[] args) {
        Validator validator = new Validator();

        // Bước 1: Nhập nội dung cần thống kê từ bàn phím.
        String content = validator.getString("Enter your content:");

        // Bước 2: Khởi tạo đối tượng và đếm số lần xuất hiện của từng từ.
        TextCounter textCounter = new TextCounter(content);
        System.out.println(textCounter.countWords());

        // Bước 3: Đếm và hiển thị số lần xuất hiện của từng ký tự.
        System.out.println(textCounter.countCharacters());
    }
}
