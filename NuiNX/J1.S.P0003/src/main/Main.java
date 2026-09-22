package main;

import model.InsertionSort;

import utils.Validator;

/**
 * Main Class dùng để chạy chương trình Insertion Sort.
 *
 * @version 18/09/2026
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
        InsertionSort insertionSortObj = new InsertionSort(size);
        insertionSortObj.generateRandomArray();

        // Bước 3: Hiển thị mảng trước khi sắp xếp
        System.out.print("Unsorted array: ");
        insertionSortObj.display();

        // Bước 4: Sắp xếp mảng bằng thuật toán Insertion Sort
        insertionSortObj.insertionSort();

        // Bước 5: Hiển thị mảng sau khi sắp xếp
        System.out.print("Sorted array: ");
        insertionSortObj.display();
    }
}
