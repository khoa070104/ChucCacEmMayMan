package model;

import java.util.Arrays;

/**
 * Bộ kiểm thử độc lập cho chức năng tạo dãy Fibonacci.
 */
public class FibonacciTest {

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();

        assertSequence(fibonacci.generateSequence(1), new long[] {0});
        assertSequence(fibonacci.generateSequence(2), new long[] {0, 1});
        assertSequence(
                fibonacci.generateSequence(10),
                new long[] {0, 1, 1, 2, 3, 5, 8, 13, 21, 34});

        long[] sequence = fibonacci.generateSequence(45);
        if (sequence[44] != 701408733L) {
            throw new AssertionError("Unexpected 45th Fibonacci number.");
        }

        assertIllegalArgument(() -> fibonacci.generateSequence(0));
        assertIllegalArgument(() -> fibonacci.generateSequence(-1));
        System.out.println("FibonacciTest: PASSED");
    }

    private static void assertSequence(long[] actual, long[] expected) {
        if (!Arrays.equals(actual, expected)) {
            throw new AssertionError("Unexpected sequence: " + Arrays.toString(actual));
        }
    }

    private static void assertIllegalArgument(Runnable action) {
        try {
            action.run();
            throw new AssertionError("Expected IllegalArgumentException");
        } catch (IllegalArgumentException expected) {
            // Kết quả mong đợi.
        }
    }
}
