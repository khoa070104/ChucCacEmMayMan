/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Admin
 */
public enum TaskType {// enum kdl chứa các ptu cố định không thể thêm hay xoá, dùng enum không dùng class là do
// enum chỉ cố định 4 phần công việc và 4 công việc này không thể xoá thêm, nếu dùng class thì có thể set ở class khác
    CODE(1, "Code"),
    TEST(2, "Test"),
    DESIGN(3, "Design"),
    REVIEW(4, "Review");
    private int id;
    private String name;

    private TaskType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // task chỉ hiện tasktype là một số nguyên muốn hiển thị ra công việc cụ thể là 1 kiểu string thì phải có method này
    // lấy tên công việc dựa vào id tương ứng
    public static TaskType getTaskTypeById(int id) {
        switch (id) {
            case 1:
                return CODE;
            case 2:
                return TEST;
            case 3:
                return DESIGN;
            case 4:
                return REVIEW;
            default:
                throw new AssertionError();// id trong input chỉ là 1-4 nên không thể nào có số khác, nếu nó xảy ra thì đó là do lỗi logic, k cần try catch(LỖI NGHIÊM TRỌNG)
            // exception là điều này có thể xảy ra
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
