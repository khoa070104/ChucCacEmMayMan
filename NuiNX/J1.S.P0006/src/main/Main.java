package main;

import entity.MyArray;

import utils.Validator;

/**
 * Main Class dùng để chạy chương trình BinarySearch.
 *
 * @version 27/05/2026
 */
public class Main {

    public static void main(String[] args) {

        // Bước 1: Nhập số lượng phần tử của mảng tu ban phim.
        int number =
                Validator.getInt(
                        "Enter number of array: ",
                        "Number must be > 0",
                        "Invalid!",
                        1,
                        Integer.MAX_VALUE);

        try {
            // Bước 2: Tạo đối tượng MyArray với kích thước mảng đã nhập.
            MyArray myArray = new MyArray(number);

            // Bước 3: Sinh ngẫu nhiên các giá trị cho các phần tử trong mảng.
            myArray.generateRandomArray();

            // Bước 4: Nhập giá trị cần tìm kiếm trong mảng từ bàn phím.
            int key =
                    Validator.getInt(
                            "Enter search value: ",
                            "Error range!",
                            "Invalid!",
                            Integer.MIN_VALUE,
                            Integer.MAX_VALUE);

            // Bước 5: Sap xep mang theo thu tu tang dan va Hiển thị mảng đã được sinh ngẫu nhiên ra
            // màn hình.
            System.out.print("Sorted array: ");
            myArray.sort();
            myArray.display();

            // Bước 6: Tìm kiếm nhị phân giá trị cần tìm trong mảng đã sắp xếp và hiển thị kết quả
            // tìm kiếm.
            int index = myArray.binarySearch(key);

            if (index == -1) {

                // Nếu index trả về -1 nghĩa là mảng không chứa giá trị cần tìm.
                System.out.println("Value was not found.");
            } else {
                // Nếu tìm thấy, in ra vị trí (index) đầu tiên chứa giá trị đó.
                System.out.println("Found " + key + " at index: " + index);
            }

        } catch (Exception e) {
            // Bắt và in ra các lỗi ngoại lệ không mong muốn xảy ra trong quá trình chạy.
            System.out.println(e.getMessage());
        }
    }
}
