package controller;

import bo.ManagerSalaryHistory;
import bo.ManagerWorker;

import entity.SalaryHistory;
import entity.SalaryStatus;
import entity.Worker;

import utils.Validator;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Lớp Controller dùng để xử lý các chức năng của chương trình.
 *
 * @author
 */
public class Controller {

    // Đối tượng quản lý lịch sử lương.
    private ManagerSalaryHistory histories;

    // Đối tượng quản lý công nhân.
    private ManagerWorker workers;

    /**
     * Khởi tạo các đối tượng quản lý.
     */
    public Controller() {
        histories = new ManagerSalaryHistory();
        workers = new ManagerWorker();
    }

    /**
     * In thông tin  dạng bảng - UP/DOWN SALARY
     */
    private void printTable(Worker worker, String status, String title) {
        // Tạo định dạng ngày theo dd/MM/yyyy.
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("\n" + title);
        System.out.printf(
                "%7s%10s%10s%10s%10s%15s\n", "Code", "Name", "Age", "Salary", "Status", "Date");
        System.out.printf(
                "%7s%10s%10d%10.0f%10s%15s\n",
                worker.getId(),
                worker.getName(),
                worker.getAge(),
                worker.getSalary(),
                status,
                dateFormat.format(new Date()));
    }

    /**
     * Thêm công nhân mới.
     *
     * @return công nhân vừa được thêm
     * @throws Exception nếu thêm thất bại
     */
    public Worker addWorker() throws Exception {
        // Tạo đối tượng công nhân mới.
        Worker worker = new Worker();
        worker.setId(Validator.getString("Enter Code: ", "Invalid!", "[Ww]\\s\\d+").toUpperCase());
        worker.setName(Validator.getString("Enter Name: ", "Invalid!", "[A-Za-z\\s]+"));
        worker.setAge(Validator.getInt("Enter age: ", "age >= 18 and <=50 !", "Invalid!", 18, 50));
        worker.setSalary(
                Validator.getDouble(
                        "Enter Salary: ",
                        "salary must be > 0",
                        "Invalid!",
                        Double.MIN_VALUE,
                        Double.MAX_VALUE));
        worker.setWorkLocation(
                Validator.getString("Enter work location: ", "Invalid!", "[A-Za-z0-9\\s]+"));

        // Thêm công nhân vào danh sách.
        if (workers.add(worker)) {
            return worker;
        }
        throw new Exception("Can not add worker!");
    }

    /**
     * Tăng lương cho công nhân.
     *
     * @return lịch sử tăng lương vừa được tạo
     * @throws Exception nếu tăng lương thất bại
     */
    public SalaryHistory upSalary() throws Exception {
        String code = Validator.getString("Enter Code: ", "Invalid!", "[Ww]\\s\\d+").toUpperCase();

        Worker currentWorker = workers.getWorker(code);
        if (currentWorker == null) {
            throw new Exception("Cannot find code!");
        }

        // In BEFORE
        printTable(currentWorker, "", "Current Salary:");

        double amount =
                Validator.getDouble(
                        "Enter Salary: ",
                        "salary must be > 0",
                        "Invalid!",
                        Double.MIN_VALUE,
                        Double.MAX_VALUE);

        // Thực hiện tăng lương.
        Worker worker = workers.changeSalary(SalaryStatus.UP, code, amount);

        // Tạo lịch sử tăng lương.
        SalaryHistory history =
                new SalaryHistory(worker, worker.getSalary(), SalaryStatus.UP, new Date());

        // Lưu lịch sử tăng lương.
        if (histories.addSalaryHistory(history)) {

            // In after
            printTable(currentWorker, "UP", "Up salary success:");
            return history;
        }
        throw new Exception("Can not up salary!");
    }

    /**
     * Giảm lương cho công nhân.
     *
     * @return lịch sử giảm lương vừa được tạo
     * @throws Exception nếu giảm lương thất bại
     */
    public SalaryHistory downSalary() throws Exception {
        String code = Validator.getString("Enter Code: ", "Invalid!", "[Ww]\\s\\d+").toUpperCase();

        Worker currentWorker = workers.getWorker(code);
        if (currentWorker == null) {
            throw new Exception("Cannot find code!");
        }

        // In BEFORE
        printTable(currentWorker, "", "Current Salary:");

        // Nhập số tiền giảm lương.
        double amount =
                Validator.getDouble(
                        "Enter Salary: ",
                        "salary must be > 0",
                        "Invalid!",
                        Double.MIN_VALUE,
                        Double.MAX_VALUE);

        // Thực hiện giảm lương.
        Worker worker = workers.changeSalary(SalaryStatus.DOWN, code, amount);

        // Tạo lịch sử giảm lương.
        SalaryHistory history =
                new SalaryHistory(worker, worker.getSalary(), SalaryStatus.DOWN, new Date());

        // Lưu lịch sử giảm lương.
        if (histories.addSalaryHistory(history)) {

            printTable(currentWorker, "DOWN", "Down salary success:");
            return history;
        }
        throw new Exception("Can not down salary!");
    }

    /**
     * Hiển thị lịch sử thay đổi lương.
     *
     * @throws Exception nếu không có dữ liệu lịch sử
     */
    public void showHistory() throws Exception {
        // Lấy dữ liệu lịch sử lương.
        String result = histories.toString();

        // Kiểm tra lịch sử lương có dữ liệu hay không.
        if (result == null) {
            throw new Exception("History Salary is empty!!");
        } else {
            // Hiển thị lịch sử lương ra màn hình.
            System.out.println(result);
        }
    }
}
