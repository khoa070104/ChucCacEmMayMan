package main;

import model.MergeSort;

import utils.Validator;

/**
 * Main Class dùng để chạy chương trình Merge Sort.
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

        MergeSort mergeSortObj = new MergeSort(size);
        mergeSortObj.generateRandomArray();

        System.out.print("Unsorted array: ");
        mergeSortObj.display();

        mergeSortObj.mergeSort();

        System.out.print("Sorted array: ");
        mergeSortObj.display();
    }
}
