package main;

import model.SelectionSort;

import utils.Validator;

/**
 *
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
        SelectionSort selectionSortObj = new SelectionSort(size);
        selectionSortObj.generateRandomArray();

        // Bước 3: Hiển thị mảng trước khi sắp xếp
        System.out.print("Unsorted array: ");
        selectionSortObj.display();

        // Bước 4: Sắp xếp mảng bằng thuật toán selection Sort
        selectionSortObj.selectionSort();

        // Bước 5: Hiển thị mảng sau khi sắp xếp
        System.out.print("Sorted array: ");
        selectionSortObj.display();
    }
}
