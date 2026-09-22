package model;

/**
 * Lớp Fibonacci cung cấp phương thức tạo dãy Fibonacci bằng đệ quy và hiển thị
 * dãy ra màn hình.
 *
 * @version 22/09/2026
 */
public class Fibonacci {

    /**
     * Tạo dãy Fibonacci với số lượng phần tử được chỉ định.
     *
     * @param numberOfTerms số lượng phần tử cần tạo
     * @return mảng chứa dãy Fibonacci
     */
    public long[] generateSequence(int numberOfTerms) {
        if (numberOfTerms <= 0) {
            throw new IllegalArgumentException("Number of terms must be greater than 0.");
        }

        long[] sequence = new long[numberOfTerms];
        generateRecursively(sequence, 0, 0, 1);
        return sequence;
    }

    /**
     * Sinh từng phần tử bằng đệ quy dựa trên hai số Fibonacci liền trước.
     */
    private void generateRecursively(long[] sequence, int index, long current, long next) {
        if (index == sequence.length) {
            return;
        }

        sequence[index] = current;
        generateRecursively(sequence, index + 1, next, current + next);
    }

    /**
     * Hiển thị dãy Fibonacci, các phần tử cách nhau bởi dấu phẩy.
     *
     * @param sequence dãy Fibonacci cần hiển thị
     */
    public void display(long[] sequence) {
        for (int i = 0; i < sequence.length; i++) {
            System.out.print(sequence[i]);
            if (i < sequence.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
}
