package main;

import model.QuickSort;

import utils.Validator;

/**
 * Main Class dùng để chạy chương trình Quick Sort.
 *
 * @version 18/09/2026
 */
public class Main {

    public static void main(String[] args) {
        Validator validator = new Validator();

        int size =
                validator.getInt(
                        "Enter number of array:",
                        "Error: Number must be greater than 0.",
                        "Error: Invalid integer input! Please enter a number.",
                        1,
                        Integer.MAX_VALUE);

        QuickSort quickSortObj = new QuickSort(size);
        quickSortObj.generateRandomArray();

        System.out.print("Unsorted array: ");
        quickSortObj.display();

        quickSortObj.quickSort();

        System.out.print("Sorted array: ");
        quickSortObj.display();
    }
}
