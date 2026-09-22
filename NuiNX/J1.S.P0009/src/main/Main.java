package main;

import model.Fibonacci;

/**
 * Main Class dùng để chạy chương trình hiển thị 45 số Fibonacci.
 *
 * @version 22/09/2026
 */
public class Main {

    private static final int NUMBER_OF_TERMS = 45;

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();

        // Bước 1: Hiển thị tiêu đề của dãy Fibonacci.
        System.out.println("The 45 sequence fibonacci:");

        // Bước 2: Tạo 45 số Fibonacci bằng phương pháp đệ quy.
        long[] sequence = fibonacci.generateSequence(NUMBER_OF_TERMS);

        // Bước 3: Hiển thị lần lượt các phần tử của dãy ra màn hình.
        fibonacci.display(sequence);
    }
}
