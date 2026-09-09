package entity;

/**
 * Enum SalaryStatus dùng để lưu trạng thái thay đổi lương của công nhân.
 *
 * @author win
 */
public enum SalaryStatus {
    // Trạng thái tăng lương va giam luong.
    UP,
    DOWN;

    /**
     * Lấy trạng thái tăng lương.
     *
     * @return trạng thái UP
     */
    public static SalaryStatus getUP() {
        return UP;
    }

    /**
     * Lấy trạng thái giảm lương.
     *
     * @return trạng thái DOWN
     */
    public static SalaryStatus getDOWN() {
        return DOWN;
    }
}
