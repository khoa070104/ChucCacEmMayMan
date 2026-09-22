package main;

import model.Graph;
import utils.Validator;

/**
 * Main Class dùng để chạy chương trình kiểm tra cạnh của đồ thị vô hướng.
 *
 * @version 22/09/2026
 */
public class Main {

    public static void main(String[] args) {
        Validator validator = new Validator();
        Graph graph = new Graph();

        // Bước 1: Nhập đỉnh bắt đầu của cạnh cần kiểm tra.
        int startPoint =
                validator.getInt(
                        "Enter the start point:",
                        "Error: Point must be from 1 to 5.",
                        "Error: Invalid integer input! Please enter a number.",
                        1,
                        graph.getNumberOfVertices());

        // Bước 2: Nhập đỉnh kết thúc của cạnh cần kiểm tra.
        int endPoint =
                validator.getInt(
                        "Enter the end point:",
                        "Error: Point must be from 1 to 5.",
                        "Error: Invalid integer input! Please enter a number.",
                        1,
                        graph.getNumberOfVertices());

        // Bước 3: Kiểm tra hai đỉnh có tạo thành một cạnh hay không.
        if (graph.isEdge(startPoint, endPoint)) {
            System.out.println("This is an edge");
        } else {
            System.out.println("This is not an edge");
        }
    }
}
