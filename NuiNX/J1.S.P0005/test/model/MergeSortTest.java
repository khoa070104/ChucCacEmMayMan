package model;

import java.util.Arrays;
import java.util.Random;

/**
 * Bộ kiểm thử độc lập cho thuật toán Merge Sort.
 */
public class MergeSortTest {

    public static void main(String[] args) {
        testKnownCases();
        testRandomCases();
        testInvalidInput();
        System.out.println("MergeSortTest: PASSED");
    }

    private static void testKnownCases() {
        assertSorted(new int[] {5});
        assertSorted(new int[] {1, 2, 3, 4, 5});
        assertSorted(new int[] {5, 4, 3, 2, 1});
        assertSorted(new int[] {3, 1, 3, 2, 1, 3});
        assertSorted(new int[] {0, -1, 5, -10, Integer.MAX_VALUE, Integer.MIN_VALUE});
    }

    private static void testRandomCases() {
        Random random = new Random(5);
        for (int length = 1; length <= 200; length++) {
            for (int round = 0; round < 25; round++) {
                int[] values = new int[length];
                for (int i = 0; i < values.length; i++) {
                    values[i] = random.nextInt(201) - 100;
                }
                assertSorted(values);
            }
        }
    }

    private static void testInvalidInput() {
        assertIllegalArgument(() -> new MergeSort(0));
        assertIllegalArgument(() -> new MergeSort(-1));
        assertIllegalArgument(() -> new MergeSort((int[]) null));
        assertIllegalArgument(() -> new MergeSort(new int[0]));
    }

    private static void assertSorted(int[] input) {
        int[] expected = Arrays.copyOf(input, input.length);
        Arrays.sort(expected);
        MergeSort sorter = new MergeSort(input);
        sorter.mergeSort();
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
