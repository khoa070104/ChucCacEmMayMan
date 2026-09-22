package model;

/**
 * Lớp Graph biểu diễn đồ thị vô hướng bằng ma trận kề và cung cấp phương thức
 * kiểm tra cạnh giữa hai đỉnh.
 *
 * @version 22/09/2026
 */
public class Graph {

    private final int[][] adjacencyMatrix = {
        {0, 0, 0, 1, 0},
        {0, 0, 0, 1, 1},
        {0, 0, 0, 0, 1},
        {1, 1, 0, 0, 1},
        {0, 1, 1, 1, 0}
    };

    /**
     * Trả về số lượng đỉnh của đồ thị.
     *
     * @return số lượng đỉnh
     */
    public int getNumberOfVertices() {
        return adjacencyMatrix.length;
    }

    /**
     * Kiểm tra hai đỉnh có được nối với nhau bằng một cạnh hay không.
     *
     * @param startPoint đỉnh bắt đầu, được đánh số từ 1
     * @param endPoint đỉnh kết thúc, được đánh số từ 1
     * @return true nếu tồn tại cạnh giữa hai đỉnh, ngược lại trả về false
     */
    public boolean isEdge(int startPoint, int endPoint) {
        validatePoint(startPoint);
        validatePoint(endPoint);

        // Chuyển số thứ tự đỉnh sang chỉ số mảng trước khi kiểm tra ma trận kề.
        return adjacencyMatrix[startPoint - 1][endPoint - 1] == 1;
    }

    private void validatePoint(int point) {
        if (point < 1 || point > adjacencyMatrix.length) {
            throw new IllegalArgumentException("Point must be from 1 to 5.");
        }
    }
}
