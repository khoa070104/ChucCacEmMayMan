package J1.S.P0009.main;

import J1.S.P0009.controller.FibonacciController;
import J1.S.P0009.utils.Validation;

public class Main {

    public static void main(String[] args) {
        FibonacciController controller = new FibonacciController();

        try {
            // Kiem tra so luong phan tu truoc khi gui sang Controller
            int number = Validation.getPositiveInt("45");
            controller.displayFibonacciSequence(number);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
