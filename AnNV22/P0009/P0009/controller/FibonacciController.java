package J1.S.P0009.controller;

import J1.S.P0009.dto.FibonacciResponseDTO;
import J1.S.P0009.service.FibonacciService;
import J1.S.P0009.view.FibonacciView;
import java.util.List;

public class FibonacciController {

    private FibonacciService service;
    private FibonacciView view;

    // Khoi tao Service va View
    public FibonacciController() {
        service = new FibonacciService();
        view = new FibonacciView();
    }

    // Goi Service tao day Fibonacci, sau do truyen DTO sang View
    public void displayFibonacciSequence(int number) {
        service.createFibonacci(number);
        List<FibonacciResponseDTO> fibonacciList = service.getAllFibonacci();
        view.setFibonacciList(fibonacciList);
        view.display();
    }
}
