package J1.S.P0009.service;

import J1.S.P0009.dto.FibonacciResponseDTO;
import J1.S.P0009.model.Fibonacci;
import J1.S.P0009.repository.FibonacciRepository;
import java.util.ArrayList;
import java.util.List;

public class FibonacciService {

    private FibonacciRepository repository;

    // Khoi tao Repository
    public FibonacciService() {
        repository = new FibonacciRepository();
    }

    // Ham de quy tinh Fibonacci
    private int calculateFibonacci(int n) {

        // Truong hop co so
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        // Goi lai chinh no
        return calculateFibonacci(n - 1)
                + calculateFibonacci(n - 2);
    }

    // Tao va luu day Fibonacci
    public void createFibonacci(int number) {
        // Tao tung Fibonacci object
        for (int i = 0; i < number; i++) {
            int value = calculateFibonacci(i);
            Fibonacci fibonacci = new Fibonacci(i, value);
            // Luu object vao Repository
            repository.add(fibonacci);
        }
    }

    // Chuyen Model sang ResponseDTO truoc khi tra ve Controller
    public List<FibonacciResponseDTO> getAllFibonacci() {
        List<FibonacciResponseDTO> result = new ArrayList<>();

        for (Fibonacci fibonacci : repository.getAll()) {
            result.add(new FibonacciResponseDTO(
                    fibonacci.getIndex(),
                    fibonacci.getValue()
            ));
        }

        return result;
    }
}
