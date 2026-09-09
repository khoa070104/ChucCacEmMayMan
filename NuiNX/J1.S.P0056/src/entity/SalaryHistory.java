package entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Lớp SalaryHistory dùng để lưu lịch sử thay đổi lương của công nhân.
 *
 * @author
 */
public class SalaryHistory implements Comparable<SalaryHistory> {

    // Thông tin công nhân
    private Worker worker;

    // Lương sau khi cập nhật
    private double salaryUpdate;

    // Trạng thái thay đổi lương.
    private SalaryStatus status;

    // Ngày thay đổi lương.
    private Date date;

    /**
     * Khởi tạo lịch sử lương rỗng.
     */
    public SalaryHistory() {}

    public SalaryHistory(Worker worker, double salaryUpdate, SalaryStatus status, Date date) {
        this.worker = worker;
        this.salaryUpdate = salaryUpdate;
        this.status = status;
        this.date = date;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public double getSalaryUpdate() {
        return salaryUpdate;
    }

    public void setSalaryUpdate(double salaryUpdate) {
        this.salaryUpdate = salaryUpdate;
    }

    public SalaryStatus getStatus() {
        return status;
    }

    public void setStatus(SalaryStatus status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Chuyển lịch sử lương thành chuỗi để hiển thị.
     *
     * @return chuỗi thông tin lịch sử lương
     */
    @Override
    public String toString() {
        // Tạo định dạng ngày theo dd/MM/yyyy.
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Trả về thông tin lịch sử lương dưới dạng bảng.
        return String.format(
                "%7s%10s%10d%10.0f%10s%15s\n",
                worker.getId(),
                worker.getName(),
                worker.getAge(),
                salaryUpdate,
                getStatus(),
                dateFormat.format(date));
    }

    @Override
    public int compareTo(SalaryHistory o) {
        // So sánh theo mã công nhân để phục vụ sắp xếp.
        return worker.getId().compareTo(o.getWorker().getId());
    }
}
