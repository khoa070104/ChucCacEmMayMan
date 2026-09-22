package model;

import java.util.Arrays;
import java.util.Random;

/**
 * Lớp InsertionSort cung cấp các phương thức khởi tạo, sinh số ngẫu nhiên,
 * hiển thị và sắp xếp mảng bằng thuật toán Insertion Sort.
 *
 * @version 18/09/2026
 */
public class InsertionSort {

    private int[] array;

    /**
     * Khởi tạo mảng với kích thước được chỉ định.
     *
     * @param size kích thước của mảng
     */
    public InsertionSort(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Array size must be greater than 0.");
        }
        array = new int[size];
    }

    /**
     * Khởi tạo đối tượng từ một mảng có sẵn.
     *
     * @param values các giá trị cần sắp xếp
     */
    public InsertionSort(int[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty.");
        }
        array = Arrays.copyOf(values, values.length);
    }

    /**
     * Tạo số ngẫu nhiên cho mảng trong khoảng từ 0 đến size - 1.
     */
    public void generateRandomArray() {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(array.length);
        }
    }

    /**
     * Hiển thị mảng ra màn hình.
     */
    public void display() {
        System.out.println(Arrays.toString(array));
    }

    /**
     * Sắp xếp mảng tăng dần bằng thuật toán Insertion Sort.
     */
    public void insertionSort() {
        for (int i = 1; i < array.length; i++) {
            int currentValue = array[i];
            int position = i - 1;

            // Dịch các phần tử lớn hơn sang phải để tạo vị trí chèn.
            while (position >= 0 && array[position] > currentValue) {
                array[position + 1] = array[position];
                position--;
            }
            array[position + 1] = currentValue;
        }
    }

    /**
     * Trả về bản sao của mảng để bảo vệ dữ liệu bên trong đối tượng.
     *
     * @return bản sao của mảng hiện tại
     */
    public int[] getArray() {
        return Arrays.copyOf(array, array.length);
    }
}
