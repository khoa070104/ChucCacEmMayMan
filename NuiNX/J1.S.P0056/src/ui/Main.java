package ui;

import controller.Controller;

import entity.SalaryHistory;
import entity.Worker;

import utils.Validator;

/**
 * Lớp Main dùng để khởi động và điều khiển menu chương trình quản lý công nhân.
 *
 * @author
 */
public class Main {

    public static void main(String[] args) {
        // Tạo đối tượng điều khiển các chức năng của chương trình.
        Controller control = new Controller();

        do {
            // Hiển thị menu và yêu cầu người dùng chọn chức năng.
            int choice =
                    Validator.getInt(
                            "======== Worker Management =========\n"
                                    + "1.Add Worker\n"
                                    + "2.Up salary\n"
                                    + "3.Down salary\n"
                                    + "4.Display Information salary\n"
                                    + "5.Exit\n"
                                    + "Enter your choice: ",
                            "Just 1 -> 5",
                            "Invalid!",
                            1,
                            5);

            // Thực hiện chức năng tương ứng với lựa chọn của người dùng.
            switch (choice) {
                case 1:
                    try {
                        System.out.println("--------- Add Worker ----------");
                        // Thêm công nhân mới.
                        Worker worker = control.addWorker();

                        // Hiển thị thông tin công nhân vừa thêm.
                        System.out.println("Add success: \n" + worker.toString());
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.println("------- Up/Down Salary --------");
                        // Tăng lương cho công nhân.
                        SalaryHistory history = control.upSalary();

                        // Hiển thị thông tin sau khi tăng lương.
                        //                        System.out.println("Up salary success: \n" +
                        // history.toString());
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 3:
                    try {
                        System.out.println("------- Up/Down Salary --------");
                        // Giảm lương cho công nhân.
                        SalaryHistory history = control.downSalary();

                        // Hiển thị thông tin sau khi giảm lương.
                        //                        System.out.println("Down salary success: \n" +
                        // history.toString());
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 4:
                    try {
                        // Hiển thị lịch sử thay đổi lương.
                        System.out.println("-------- Display Information Salary --------");
                        control.showHistory();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 5:
                    // Kết thúc chương trình.
                    System.exit(0);
            }
            // Lặp lại menu cho đến khi người dùng chọn thoát.
        } while (true);
    }
}
