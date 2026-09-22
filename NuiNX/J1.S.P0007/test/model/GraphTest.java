package model;

/**
 * Bộ kiểm thử độc lập cho chức năng kiểm tra cạnh của đồ thị.
 */
public class GraphTest {

    public static void main(String[] args) {
        Graph graph = new Graph();

        assertEdge(graph, 1, 4, true);
        assertEdge(graph, 2, 5, true);
        assertEdge(graph, 3, 5, true);
        assertEdge(graph, 1, 3, false);
        assertEdge(graph, 5, 5, false);

        // Đồ thị vô hướng nên ma trận kề phải đối xứng.
        for (int firstPoint = 1; firstPoint <= graph.getNumberOfVertices(); firstPoint++) {
            for (int secondPoint = 1;
                    secondPoint <= graph.getNumberOfVertices();
                    secondPoint++) {
                if (graph.isEdge(firstPoint, secondPoint)
                        != graph.isEdge(secondPoint, firstPoint)) {
                    throw new AssertionError("The adjacency matrix is not symmetric.");
                }
            }
        }

        assertIllegalArgument(() -> graph.isEdge(0, 1));
        assertIllegalArgument(() -> graph.isEdge(1, 6));
        System.out.println("GraphTest: PASSED");
    }

    private static void assertEdge(Graph graph, int startPoint, int endPoint, boolean expected) {
        if (graph.isEdge(startPoint, endPoint) != expected) {
            throw new AssertionError(
                    "Unexpected result for edge (" + startPoint + ", " + endPoint + ").");
        }
    }

    private static void assertIllegalArgument(Runnable action) {
        try {
            action.run();
            throw new AssertionError("Expected IllegalArgumentException");
        } catch (IllegalArgumentException expected) {
            // Kết quả mong đợi.
        }
    }
}
