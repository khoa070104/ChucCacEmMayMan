package entity;

import java.util.Random;

/**
 * Lớp MyArray dùng để tạo mảng số nguyên, sinh giá trị ngẫu nhiên, hiển thị
 * mảng và tìm kiếm tuyến tính một giá trị trong mảng.
 *
 * @version 27/05/2026
 */
public class MyArray {

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

    /* NEW @version 27/05/2026.
     * Tìm kiếm tuyến tính.
     *
     * @param key giá trị cần tìm
     * @return vị trí nếu tìm thấy, ngược lại trả về -1
     */
    public int linearSearch(int key) {

        // Duyệt lần lượt từng phần tử trong mảng
        for (int i = 0; i < array.length; i++) {

            // Nếu tìm thấy giá trị cần tìm thì trả về vị trí
            if (array[i] == key) {
                return i;
            }
        }
        // Không tìm thấy thì trả về -1
        return -1;
    }
}
