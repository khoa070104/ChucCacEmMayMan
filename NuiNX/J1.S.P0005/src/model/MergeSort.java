package model;

import java.util.Arrays;
import java.util.Random;

/**
 * Lớp MergeSort cung cấp các phương thức khởi tạo, sinh số ngẫu nhiên,
 * hiển thị và sắp xếp mảng bằng thuật toán Merge Sort.
 *
 * @version 18/09/2026
 */
public class MergeSort {

    private int[] array;
    private int[] temporaryArray;

    public MergeSort(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Array size must be greater than 0.");
        }
        array = new int[size];
        temporaryArray = new int[size];
    }

    public MergeSort(int[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty.");
        }
        array = Arrays.copyOf(values, values.length);
        temporaryArray = new int[values.length];
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
     * Sắp xếp toàn bộ mảng tăng dần bằng thuật toán Merge Sort.
     */
    public void mergeSort() {
        mergeSort(0, array.length - 1);
    }

    private void mergeSort(int left, int right) {
        if (left >= right) {
            return;
        }

        int middle = left + (right - left) / 2;
        mergeSort(left, middle);
        mergeSort(middle + 1, right);
        merge(left, middle, right);
    }

    /**
     * Trộn hai đoạn đã tăng dần thành một đoạn tăng dần.
     */
    private void merge(int left, int middle, int right) {
        for (int i = left; i <= right; i++) {
            temporaryArray[i] = array[i];
        }

        int leftIndex = left;
        int rightIndex = middle + 1;
        int currentIndex = left;

        while (leftIndex <= middle && rightIndex <= right) {
            if (temporaryArray[leftIndex] <= temporaryArray[rightIndex]) {
                array[currentIndex++] = temporaryArray[leftIndex++];
            } else {
                array[currentIndex++] = temporaryArray[rightIndex++];
            }
        }

        while (leftIndex <= middle) {
            array[currentIndex++] = temporaryArray[leftIndex++];
        }
        while (rightIndex <= right) {
            array[currentIndex++] = temporaryArray[rightIndex++];
        }
    }

    public int[] getArray() {
        return Arrays.copyOf(array, array.length);
    }
}
