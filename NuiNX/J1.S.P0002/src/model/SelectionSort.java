/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Random;

/**
 *
 */
public class SelectionSort {

    private int[] array;

    /**
     * Khởi tạo mảng với kích thước được chỉ định.
     *
     * @param size kích thước của mảng
     */
    public SelectionSort(int size) {
        array = new int[size];
    }

    /**
     * Tạo số ngẫu nhiên cho mảng.
     */
    public void generateRandomArray() {
        Random rd = new Random();
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
     * Sắp xếp mảng tăng dần bằng thuật toán Selection Sort.
     * Mỗi lượt tìm phần tử nhỏ nhất của đoạn chưa sắp xếp rồi đưa phần tử đó
     * về đầu đoạn.
     */
    public void selectionSort() {

        int minIndex;
        int temp;

        // Duyệt từng vị trí trong mảng
        for (int i = 0; i < array.length - 1; i++) {

            // Giả sử phần tử đầu tiên của đoạn chưa sắp xếp là nhỏ nhất
            minIndex = i;

            // Tìm phần tử nhỏ nhất trong phần mảng chưa sắp xếp
            for (int j = i + 1; j < array.length; j++) {

                // Nếu tìm thấy phần tử nhỏ hơn phần tử nhỏ nhất hiện tại
                if (array[j] < array[minIndex]) {

                    // Cập nhật vị trí của phần tử nhỏ nhất
                    minIndex = j;
                }
            }

            // Nếu phần tử nhỏ nhất không nằm đúng vị trí
            if (minIndex != i) {

                // Hoán đổi phần tử nhỏ nhất với phần tử tại vị trí hiện tại
                temp = array[minIndex];
                array[minIndex] = array[i];
                array[i] = temp;
            }
        }
    }
}
