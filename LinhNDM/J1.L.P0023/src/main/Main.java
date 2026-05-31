package main;

import controller.FruitController;

/**
 * Điểm khởi chạy chương trình Fruit Shop System (J1.L.P0023).
 *
 * @author LinhNDM
 */
public class Main {

    /**
     * Khởi chạy ứng dụng quản lý cửa hàng trái cây.
     *
     * @param args tham số dòng lệnh
     */
    public static void main(String[] args) {
        FruitController controller;
        controller = new FruitController();
        controller.run();
    }
}
