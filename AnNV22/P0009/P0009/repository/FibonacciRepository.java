package J1.S.P0009.repository;

import java.util.ArrayList;
import J1.S.P0009.model.Fibonacci;

public class FibonacciRepository {

    private ArrayList<Fibonacci> fibonacciList;

    // Khoi tao danh sach
    public FibonacciRepository() {
        fibonacciList = new ArrayList<>();
    }

    // Them Fibonacci vao danh sach
    public void add(Fibonacci fibonacci) {
        fibonacciList.add(fibonacci);
    }

    // Lay tat ca Fibonacci
    public ArrayList<Fibonacci> getAll() {
        return fibonacciList;
    }
}