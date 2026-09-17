package J1.S.P0009.model;

public class Fibonacci {

    private int index;
    private int value;

    // Constructor
    public Fibonacci(int index, int value) {
        this.index = index;
        this.value = value;
    }

    // Getter index
    public int getIndex() {
        return index;
    }

    // Setter index
    public void setIndex(int index) {
        this.index = index;
    }

    // Getter value
    public int getValue() {
        return value;
    }

    // Setter value
    public void setValue(int value) {
        this.value = value;
    }

    // Hien thi thong tin Fibonacci
    @Override
    public String toString() {
        return value + "";
    }
}