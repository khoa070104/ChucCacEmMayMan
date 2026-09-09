package bo;

import entity.SalaryStatus;
import entity.Worker;

import java.util.ArrayList;
import java.util.List;

/**
 * Lớp ManagerWorker dùng để quản lý danh sách công nhân.
 *
 * @author
 */
public class ManagerWorker {

    // Danh sách công nhân.
    private List<Worker> list;

    /**
     * Khởi tạo danh sách công nhân rỗng.
     */
    public ManagerWorker() {
        this.list = new ArrayList<>();
    }

    public List<Worker> getList() {
        return list;
    }

    public void setList(List<Worker> list) {
        this.list = list;
    }

    /**
     * Tìm công nhân theo mã.
     *
     * @param id mã công nhân
     * @return công nhân tìm được hoặc null
     */
    public Worker getWorker(String id) {
        // Duyệt danh sách để tìm công nhân theo mã.
        for (Worker workers : list) {

            // Kiểm tra mã công nhân có trùng hay không.
            if (workers.getId().equalsIgnoreCase(id)) {
                return workers;
            }
        }
        return null;
    }

    /**
     * Kiểm tra mã công nhân đã tồn tại hay chưa.
     *
     * @param id mã công nhân
     * @return true nếu đã tồn tại
     */
    private boolean isExist(String id) {
        // Duyệt danh sách để kiểm tra mã công nhân.
        for (Worker workers : list) {

            // Kiểm tra mã công nhân có tồn tại hay không.
            if (workers.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Thêm công nhân mới vào danh sách.
     *
     * @param worker công nhân cần thêm
     * @return true nếu thêm thành công
     * @throws Exception nếu mã công nhân đã tồn tại
     */
    public boolean add(Worker worker) throws Exception {
        // Kiểm tra công nhân đã tồn tại hay chưa.
        if (isExist(worker.getId())) {
            throw new Exception("Worker with ID " + worker.getId() + " already exists.");
        }
        return list.add(worker);
    }

    /**
     * Thay đổi lương của công nhân.
     *
     * @param status trạng thái tăng hoặc giảm lương
     * @param code mã công nhân
     * @param amount số tiền thay đổi
     * @return công nhân sau khi thay đổi lương
     * @throws Exception nếu dữ liệu không hợp lệ
     */
    public Worker changeSalary(SalaryStatus status, String code, double amount) throws Exception {
        // Kiểm tra danh sách công nhân có dữ liệu hay không.
        if (list.isEmpty()) {
            throw new Exception("List is empty!Can not change salary");
        }
        // Kiểm tra mã công nhân có tồn tại hay không.
        if (!isExist(code)) {
            throw new Exception("Cannot find code!");
        }
        // Kiểm tra số tiền thay đổi phải lớn hơn 0.
        if (amount <= 0) {
            throw new Exception("Amount of money must be > 0 ");
        }
        // Lấy công nhân cần thay đổi lương.
        Worker worker = getWorker(code);

        // Thực hiện tăng hoặc giảm lương.
        switch (status) {
            case UP:
                // Tăng lương cho công nhân.
                worker.setSalary(worker.getSalary() + amount);
                break;
            case DOWN:
                // Kiểm tra lương sau khi giảm có âm hay không.
                if (worker.getSalary() - amount <= 0) {
                    throw new Exception("Can not down " + amount);
                }
                // Giảm lương cho công nhân.
                worker.setSalary(worker.getSalary() - amount);
                break;
        }
        return worker;
    }
}
