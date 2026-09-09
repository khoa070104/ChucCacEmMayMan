package main;

import model.BubbleSort;

import utils.Validator;

/**
 * Main Class dùng để chạy chương trình Bubble Sort.
 *
 * @version 17/05/2026
 */
public class Main {

    public static void main(String[] args) {
        Validator validator = new Validator();

        // Bước 1: Nhập số lượng phần tử của mảng
        int size =
                validator.getInt(
                        "Enter number of array:",
                        "Error: Number must be greater than 0.",
                        "Error: Invalid integer input! Please enter a number.",
                        1,
                        Integer.MAX_VALUE);

        // Bước 2: Khởi tạo mảng và sinh số ngẫu nhiên
        BubbleSort bubbleSortObj = new BubbleSort(size);
        bubbleSortObj.generateRandomArray();

        // Bước 3: Hiển thị mảng trước khi sắp xếp
        System.out.print("Unsorted array: ");
        bubbleSortObj.display();

        // Bước 4: Sắp xếp mảng bằng thuật toán Bubble Sort
        bubbleSortObj.bubbleSort();

        // Bước 5: Hiển thị mảng sau khi sắp xếp
        System.out.print("Sorted array: ");
        bubbleSortObj.display();
    }
}
