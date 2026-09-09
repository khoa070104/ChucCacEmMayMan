package bo;

import entity.SalaryHistory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Lớp ManagerSalaryHistory dùng để quản lý danh sách lịch sử thay đổi lương.
 *
 * @author
 */
public class ManagerSalaryHistory {

    // Danh sách lưu lịch sử thay đổi lương.
    private List<SalaryHistory> list;

    /**
     * Khởi tạo danh sách lịch sử lương rỗng.
     */
    public ManagerSalaryHistory() {
        this.list = new ArrayList<>();
    }

    /**
     * Lấy danh sách lịch sử lương.
     *
     * @return danh sách lịch sử lương
     */
    public List<SalaryHistory> getList() {
        return list;
    }

    /**
     * Gán danh sách lịch sử lương.
     *
     * @param list danh sách lịch sử lương
     */
    public void setList(List<SalaryHistory> list) {
        this.list = list;
    }

    /**
     * Thêm một lịch sử thay đổi lương vào danh sách.
     *
     * @param history lịch sử lương cần thêm
     * @return true nếu thêm thành công
     */
    public boolean addSalaryHistory(SalaryHistory history) {
        return list.add(history);
    }

    /**
     * Sắp xếp danh sách lịch sử theo mã công nhân.
     */
    private void sortByID() {
        Collections.sort(list);
    }

    /**
     * Chuyển danh sách lịch sử lương thành chuỗi để hiển thị.
     *
     * @return chuỗi thông tin lịch sử lương
     */
    @Override
    public String toString() {
        // Kiểm tra danh sách lịch sử có dữ liệu hay không.
        if (list.isEmpty()) {
            return null;
        }
        // Sắp xếp danh sách trước khi hiển thị.
        sortByID();

        // Tạo tiêu đề cho bảng dữ liệu.
        String str =
                String.format(
                        "%7s%10s%10s%10s%10s%15s\n",
                        "Code", "Name", "Age", "Salary", "Status", "Date");

        // Ghép từng lịch sử lương vào chuỗi kết quả.
        for (int i = 0; i < list.size(); i++) {
            str += list.get(i).toString();
        }
        return str;
    }
}
