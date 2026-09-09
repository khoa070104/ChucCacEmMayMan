package model;

import java.util.Random;

/**
 * Lớp BubbleSort cung cấp các phương thức khởi tạo, sinh số ngẫu nhiên,
 * hiển thị và sắp xếp mảng bằng thuật toán Bubble Sort.
 *
 * @version 17/05/2026
 */
public class BubbleSort {

    private int[] array;

    /**
     * Khởi tạo mảng với kích thước được chỉ định.
     *
     * @param size kích thước của mảng
     */
    public BubbleSort(int size) {
        array = new int[size];
    }

    /**
     * Tạo số ngẫu nhiên cho mảng.
     */
    public void generateRandomArray() {
        Random rd = new Random();

        // Duyệt qua từng vị trí trong mảng và gán giá trị ngẫu nhiên
        for (int i = 0; i < array.length; i++) {
            array[i] = rd.nextInt(array.length);
        }
    }

    /**
     * Hiển thị mảng ra màn hình.
     */
    public void display() {
        System.out.print("[");

        // Duyệt qua từng phần tử để in ra màn hình
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);

            // Nếu chưa phải phần tử cuối thì in thêm dấu phẩy.
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    /**
     * Sắp xếp mảng theo thứ tự tăng dần bằng thuật toán Bubble Sort.
     */
    public void bubbleSort() {
        int temp;

        // Duyệt qua từng lượt sắp xếp
        for (int i = 0; i < array.length - 1; i++) {
            boolean swapped = false;

            // Đẩy các phần tử lớn nhất về cuối mảng chưa được sắp xếp.
            for (int j = 0; j < array.length - 1 - i; j++) {

                // Nếu phần tử trước LỚN HƠN phần tử sau thì hoán đổi vị trí.
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }

            // Mảng đã tăng dần nên không cần thực hiện các lượt còn lại.
            if (!swapped) {
                break;
            }
        }
    }
}
