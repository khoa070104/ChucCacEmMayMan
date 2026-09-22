package model;

import java.util.Arrays;
import java.util.Random;

/**
 * Lớp QuickSort cung cấp các phương thức khởi tạo, sinh số ngẫu nhiên,
 * hiển thị và sắp xếp mảng bằng thuật toán Quick Sort.
 *
 * @version 18/09/2026
 */
public class QuickSort {

    private int[] array;

    public QuickSort(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Array size must be greater than 0.");
        }
        array = new int[size];
    }

    public QuickSort(int[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty.");
        }
        array = Arrays.copyOf(values, values.length);
    }

    public void generateRandomArray() {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(array.length);
        }
    }

    public void display() {
        System.out.println(Arrays.toString(array));
    }

    /**
     * Sắp xếp toàn bộ mảng tăng dần bằng thuật toán Quick Sort.
     */
    public void quickSort() {
        quickSort(0, array.length - 1);
    }

    /**
     * Chia mảng quanh phần tử chốt rồi sắp xếp đệ quy hai phần.
     */
    private void quickSort(int left, int right) {
        int i = left;
        int j = right;
        int pivot = array[left + (right - left) / 2];

        while (i <= j) {
            while (array[i] < pivot) {
                i++;
            }
            while (array[j] > pivot) {
                j--;
            }
            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        if (left < j) {
            quickSort(left, j);
        }
        if (i < right) {
            quickSort(i, right);
        }
    }

    public int[] getArray() {
        return Arrays.copyOf(array, array.length);
    }
}
