package entity;

import java.util.Arrays;
import java.util.Random;

/**
 * Lớp MyArray dùng để tạo mảng số nguyên, sinh giá trị ngẫu nhiên,sap xep mang
 * theo thu tu tang dan, hiển thị mảng và tìm kiếm nhị phân một giá trị trong
 * mảng.
 *
 * @version 27/05/2026
 */
public class MyArray {

    // Thuộc tính dùng để lưu trữ các phần tử số nguyên của mảng.
    private int[] array;

    /**
     * Khởi tạo mảng với kích thước được chỉ định.
     *
     * @param size kích thước của mảng
     */
    public MyArray(int size) {
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
     * new @version 27/05/2026.
     *
     * Hiển thị các phần tử của mảng ra màn hình.
     */
    public void display() {
        System.out.println(Arrays.toString(array));
    }

    /**
     * Sắp xếp mảng theo thứ tự tăng dần.
     */
    public void sort() {
        int temp;

        // Duyệt qua từng lượt sắp xếp
        for (int i = 0; i < array.length - 1; i++) {

            // Đẩy các phần tử lớn nhất về cuối mảng chưa được sắp xếp.
            for (int j = 0; j < array.length - 1 - i; j++) {

                // Nếu phần tử trước LỚN HƠN phần tử sau thì hoán đổi vị trí.
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Tìm kiếm nhị phân giá trị cần tìm trong mảng đã được sắp xếp tăng dần.
     *
     * @param key giá trị cần tìm trong mảng
     * @return vị trí nếu tìm thấy, ngược lại trả về -1
     */
    public int binarySearch(int key) {

        // Khai báo vị trí đầu và vị trí cuối của phạm vi tìm kiếm trong mảng.
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {

            // Tính vị trí chính giữa của phạm vi tìm kiếm hiện tại.
            int mid = (left + right) / 2;

            // Nếu giá trị ở giữa nhỏ hơn giá trị cần tìm thì bỏ qua nửa bên trái.
            if (array[mid] < key) {
                left = mid + 1;

                // Nếu giá trị ở giữa lớn hơn giá trị cần tìm thì bỏ qua nửa bên phải.
            } else if (array[mid] > key) {
                right = mid - 1;
            } else {

                // Nếu giá trị ở giữa bằng giá trị cần tìm thì trả về vị trí đó.
                return mid;
            }
        }
        // Trả về -1 khi đã tìm hết phạm vi nhưng không có giá trị cần tìm.
        return -1;
    }
}
