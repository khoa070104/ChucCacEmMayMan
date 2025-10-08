package controller;

import enums.Car;
import enums.Day;
import enums.Color;
import exception.ExceptionCar;
import common.Constants;
import java.util.*;

public class ShowroomManager {
    private Map<Car, CarData> carDataMap;

    public ShowroomManager() {
        initializeCarData();
    }

    private void initializeCarData() {
        carDataMap = new HashMap<>();
        
        // AUDI data
        List<Color> audiColors = Arrays.asList(Color.WHITE, Color.YELLOW, Color.ORANGE);
        List<Integer> audiPrices = Arrays.asList(5500, 3000, 4500);
        List<Day> audiDays = Arrays.asList(Day.FRIDAY, Day.SUNDAY, Day.MONDAY);
        carDataMap.put(Car.AUDI, new CarData(audiColors, audiPrices, audiDays));

        // MERCEDES data
        List<Color> mercedesColors = Arrays.asList(Color.GREEN, Color.BLUE, Color.PURPLE);
        List<Integer> mercedesPrices = Arrays.asList(5000, 6000, 8500);
        List<Day> mercedesDays = Arrays.asList(Day.TUESDAY, Day.SATURDAY, Day.WEDNESDAY);
        carDataMap.put(Car.MERCEDES, new CarData(mercedesColors, mercedesPrices, mercedesDays));

        // BMW data
        List<Color> bmwColors = Arrays.asList(Color.PINK, Color.RED, Color.BROWN);
        List<Integer> bmwPrices = Arrays.asList(2500, 3000, 3500);
        List<Day> bmwDays = Arrays.asList(Day.MONDAY, Day.SUNDAY, Day.THURSDAY);
        carDataMap.put(Car.BMW, new CarData(bmwColors, bmwPrices, bmwDays));
    }

    public Car checkCar(Car car, String color, Day day, String price) throws ExceptionCar {
        // Check if car is valid
        if (car == null) {
            throw new ExceptionCar(Constants.ERROR_CAR_BREAK);
        }

        // Check if color is valid
        Color colorEnum = Color.getColor(color);
        if (colorEnum == null) {
            throw new ExceptionCar(Constants.ERROR_COLOR_NOT_EXIST);
        }

        // Check if day is valid
        if (day == null) {
            throw new ExceptionCar(Constants.ERROR_CANT_SELL_TODAY);
        }

        // Check if price is numeric and positive
        try {
            int priceValue = Integer.parseInt(price);
            if (priceValue <= 0) {
                throw new ExceptionCar(Constants.ERROR_PRICE_GREATER_ZERO);
            }
        } catch (NumberFormatException e) {
            throw new ExceptionCar(Constants.ERROR_PRICE_NUMERIC);
        }

        // Check if color is available for this car (except NO_COLOR)
        if (colorEnum != Color.NO_COLOR) {
            CarData data = carDataMap.get(car);
            if (!data.getColors().contains(colorEnum)) {
                throw new ExceptionCar(Constants.ERROR_COLOR_NOT_EXIST);
            }
        }

        // Check if car can be sold on this day
        CarData data = carDataMap.get(car);
        if (!data.getDays().contains(day)) {
            throw new ExceptionCar(Constants.ERROR_CANT_SELL_TODAY);
        }

        return car;
    }

    public List<Integer> getPrices(Car car) {
        if (car == null || !carDataMap.containsKey(car)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(carDataMap.get(car).getPrices());
    }

    public List<String> getColors(Car car) {
        if (car == null || !carDataMap.containsKey(car)) {
            return new ArrayList<>();
        }
        List<String> colorNames = new ArrayList<>();
        for (Color color : carDataMap.get(car).getColors()) {
            if (color == Color.NO_COLOR) {
                colorNames.add("no color");
            } else {
                colorNames.add(color.name().toLowerCase());
            }
        }
        return colorNames;
    }

    public List<Day> getDaySells(Car car) {
        if (car == null || !carDataMap.containsKey(car)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(carDataMap.get(car).getDays());
    }

    public int calculatePrice(Car car, Color color, int basePrice) {
        if (color == Color.NO_COLOR) {
            return basePrice - Constants.NO_COLOR_DISCOUNT;
        }
        return basePrice;
    }

    // Inner class to hold car data
    private static class CarData {
        private List<Color> colors;
        private List<Integer> prices;
        private List<Day> days;

        public CarData(List<Color> colors, List<Integer> prices, List<Day> days) {
            this.colors = colors;
            this.prices = prices;
            this.days = days;
        }

        public List<Color> getColors() {
            return colors;
        }

        public List<Integer> getPrices() {
            return prices;
        }

        public List<Day> getDays() {
            return days;
        }
    }
}
