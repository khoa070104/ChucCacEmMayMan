package J1.S.P0009.dto;

public class FibonacciResponseDTO {

    private int index;
    private int value;

    public FibonacciResponseDTO(int index, int value) {
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
