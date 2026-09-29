package main;

import model.Person;
import model.Wallet;
import utils.Validator;

/**
 * Main Class dùng để chạy chương trình Shopping program (J1.S.P0060).
 *
 * @version 29/09/2026
 */
public class Main {

    public static void main(String[] args) {
        Validator validator = new Validator();

        System.out.println("======= Shopping program ==========");

        // Bước 1: Nhập số lượng hóa đơn
        int numberOfBills =
                validator.getInt(
                        "input number of bill: ",
                        "Error: Number of bills must be greater than 0.",
                        "Error: Invalid integer input! Please enter a number.",
                        1,
                        Integer.MAX_VALUE);

        // Bước 2: Nhập giá trị của từng hóa đơn
        int[] bills = new int[numberOfBills];
        for (int i = 0; i < numberOfBills; i++) {
            bills[i] =
                    validator.getInt(
                            "input value of bill " + (i + 1) + ": ",
                            "Error: Bill value must be greater than 0.",
                            "Error: Invalid integer input! Please enter a number.",
                            1,
                            Integer.MAX_VALUE);
        }

        // Bước 3: Nhập số tiền hiện có trong ví
        int walletAmount =
                validator.getInt(
                        "input value of wallet: ",
                        "Error: Wallet amount must be non-negative.",
                        "Error: Invalid integer input! Please enter a number.",
                        0,
                        Integer.MAX_VALUE);

        // Bước 4: Khởi tạo đối tượng Wallet và Person
        Wallet wallet = new Wallet(walletAmount);
        Person person = new Person(wallet);

        // Bước 5: Tính tổng số tiền của các hóa đơn và hiển thị ra màn hình
        int total = person.calcTotal(bills);
        System.out.println("this is total of bill: " + total);

        // Bước 6: Kiểm tra số tiền trong ví có đủ chi trả hay không và hiển thị thông báo
        if (person.payMoney(total)) {
            System.out.println("You can buy it.");
        } else {
            System.out.println("You can't buy it.");
        }
    }
}
