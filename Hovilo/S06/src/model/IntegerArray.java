package model;

import java.util.Arrays;

/** Manages an integer array of at most 100 elements. @author Ho Vi Lo @since 09/09/2026 */
public class IntegerArray {
    private static final int CAPACITY = 100;
    private final int[] values = new int[CAPACITY];
    private int size;

    /** @param value value to append @throws IllegalStateException if the array is full */
    public void add(int value) {
        if (size == CAPACITY) throw new IllegalStateException("The array is full.");
        values[size++] = value;
    }

    /** @param value searched value @return its first index or -1 when absent */
    public int search(int value) {
        for (int i = 0; i < size; i++) if (values[i] == value) return i;
        return -1;
    }

    /** @return a safe copy containing only stored values */
    public int[] getValues() {
        return Arrays.copyOf(values, size);
    }

    /** @param min inclusive minimum @param max inclusive maximum @return matching values */
    public int[] valuesInRange(int min, int max) {
        int[] temporary = new int[size];
        int count = 0;
        for (int i = 0; i < size; i++)
            if (values[i] >= min && values[i] <= max) temporary[count++] = values[i];
        return Arrays.copyOf(temporary, count);
    }

    /**
     * Sorts the stored part in ascending order using bubble sort.
     * Algorithm: repeatedly compare adjacent values and swap incorrect pairs;
     * stop early when a pass makes no swap.
     */
    public void sortAscending() {
        for (int end = size - 1; end > 0; end--) {
            boolean swapped = false;
            for (int i = 0; i < end; i++) {
                if (values[i] > values[i + 1]) {
                    int temporary = values[i];
                    values[i] = values[i + 1];
                    values[i + 1] = temporary;
                    swapped = true;
                }
            }
            if (!swapped) return;
        }
    }
}
