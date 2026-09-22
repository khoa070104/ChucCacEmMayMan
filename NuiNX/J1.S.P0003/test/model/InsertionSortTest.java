package model;

import java.util.Arrays;
import java.util.Random;

/**
 * Bộ kiểm thử độc lập cho thuật toán Insertion Sort.
 */
public class InsertionSortTest {

    public static void main(String[] args) {
        testKnownCases();
        testRandomCases();
        testInvalidInput();
        System.out.println("InsertionSortTest: PASSED");
    }

    private static void testKnownCases() {
        assertSorted(new int[] {5});
        assertSorted(new int[] {1, 2, 3, 4, 5});
        assertSorted(new int[] {5, 4, 3, 2, 1});
        assertSorted(new int[] {3, 1, 3, 2, 1, 3});
        assertSorted(new int[] {0, -1, 5, -10, Integer.MAX_VALUE, Integer.MIN_VALUE});
    }

    private static void testRandomCases() {
        Random random = new Random(3);
        for (int length = 1; length <= 100; length++) {
            for (int round = 0; round < 20; round++) {
                int[] values = new int[length];
                for (int i = 0; i < values.length; i++) {
                    values[i] = random.nextInt(201) - 100;
                }
                assertSorted(values);
            }
        }
    }

    private static void testInvalidInput() {
        assertIllegalArgument(() -> new InsertionSort(0));
        assertIllegalArgument(() -> new InsertionSort(-1));
        assertIllegalArgument(() -> new InsertionSort((int[]) null));
        assertIllegalArgument(() -> new InsertionSort(new int[0]));
    }

    private static void assertSorted(int[] input) {
        int[] expected = Arrays.copyOf(input, input.length);
        Arrays.sort(expected);
        InsertionSort sorter = new InsertionSort(input);
        sorter.insertionSort();
        if (!Arrays.equals(expected, sorter.getArray())) {
            throw new AssertionError("Unexpected result for " + Arrays.toString(input));
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
