package J1.S.P0009.view;

import J1.S.P0009.dto.FibonacciResponseDTO;
import java.util.List;

public class FibonacciView {

    private List<FibonacciResponseDTO> fibonacciList;

    // Nhan du lieu da xu ly tu Controller
    public void setFibonacciList(List<FibonacciResponseDTO> fibonacciList) {
        this.fibonacciList = fibonacciList;
    }

    // Hien thi danh sach Fibonacci
    public void display() {
        System.out.println("The 45 sequence of Fibonacci:");

        // Duyet qua danh sach
        for (int i = 0; i < fibonacciList.size(); i++) {
            System.out.print(fibonacciList.get(i));

            // In dau phay giua cac so
            if (i < fibonacciList.size() - 1) {
                System.out.print(", ");
            }
        }

        System.out.println();
    }
}
