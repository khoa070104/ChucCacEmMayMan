package enums;

public enum Car {
    AUDI, MERCEDES, BMW;

    public static Car getCar(String car) {
        try {
            return Car.valueOf(car.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
