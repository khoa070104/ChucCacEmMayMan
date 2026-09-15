package service;

import data.BinaryStore;
import model.Car;
import model.InsuranceStatement;
import util.Input;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.*;
import java.util.function.Function;

public class InsuranceManager {
    private static final String CAR_FILE = "carInfo.dat";
    private static final String INSURANCE_FILE = "insurances.dat";
    private final Input input = new Input();
    private Map<String, Car> cars = new LinkedHashMap<>();
    private Map<String, InsuranceStatement> statements = new LinkedHashMap<>();
    private boolean changed;

    public void run() {
        load(false);
        while (true) {
            menu();
            switch (input.integer("Select: ", n -> n >= 1 && n <= 10, "Select 1 to 10.")) {
                case 1 -> addCar(); case 2 -> findCar(); case 3 -> updateCar(); case 4 -> deleteCar();
                case 5 -> addStatement(); case 6 -> listStatements(); case 7 -> reportUninsured();
                case 8 -> save(); case 9 -> load(true); case 10 -> { if (quit()) return; }
            }
        }
    }

    private void menu() {
        System.out.println("\n===== CAR INSURANCE MANAGEMENT =====");
        System.out.println("1. Add car information\n2. Find a car\n3. Update a car\n4. Delete a car");
        System.out.println("5. Add an insurance statement\n6. List insurance statements");
        System.out.println("7. Report uninsured cars\n8. Save data\n9. Load data\n10. Quit");
    }

    private void addCar() {
        do {
            String plate = input.text("License plate: ", s -> !s.isBlank() && !cars.containsKey(key(s)),
                    "License plate must be non-empty and unique.");
            Car car = readCar(plate, null);
            cars.put(key(plate), car); changed = true;
            System.out.println("Car added successfully.");
        } while (input.yesNo("Add another car? (Y/N): "));
    }

    private Car readCar(String plate, Car old) {
        String owner = requiredOrOld("Owner (2-35 characters)", old == null ? null : old.getOwner(),
                s -> s.length() >= 2 && s.length() <= 35);
        String brand = requiredOrOld("Brand", old == null ? null : old.getBrand(), s -> !s.isBlank());
        long value = old == null ? input.positiveLong("Vehicle value: ") : optionalLong("Vehicle value", old.getValue());
        LocalDate regDate = old == null ? input.date("Registration date (MM/dd/yyyy): ", d -> d.isBefore(LocalDate.now()))
                : optionalDate("Registration date", old.getRegistrationDate(), d -> d.isBefore(LocalDate.now()));
        String place = requiredOrOld("Registration place", old == null ? null : old.getRegistrationPlace(), s -> !s.isBlank());
        int type = old == null ? seatType("Vehicle type (5/7/9): ") : optionalSeatType(old.getVehicleType());
        return new Car(plate.toUpperCase(), owner, brand, value, regDate, place, type);
    }

    private void findCar() {
        Car car = cars.get(key(input.optional("License plate: ")));
        if (car == null) System.out.println("Unregistered vehicle"); else printCar(car, 1);
    }

    private void updateCar() {
        String plate = input.optional("License plate: ");
        Car old = cars.get(key(plate));
        if (old == null) { System.out.println("Unregistered vehicle"); return; }
        cars.put(key(plate), readCar(old.getLicensePlate(), old)); changed = true;
        System.out.println("Car updated successfully.");
    }

    private void deleteCar() {
        String plate = input.optional("License plate: ");
        Car car = cars.get(key(plate));
        if (car == null) { System.out.println("Unregistered vehicle"); return; }
        if (statements.values().stream().anyMatch(s -> key(s.getLicensePlate()).equals(key(plate)))) {
            System.out.println("Cannot delete: the car is already registered in insurance."); return;
        }
        if (input.yesNo("Delete this car? (Y/N): ")) {
            cars.remove(key(plate)); changed = true; System.out.println("Car deleted successfully.");
        } else System.out.println("Deletion cancelled.");
    }

    private void addStatement() {
        if (cars.isEmpty()) { System.out.println("Register a car first."); return; }
        do {
            String id = input.text("Insurance ID: ", s -> !s.isBlank() && !statements.containsKey(key(s)),
                    "Insurance ID must be non-empty and unique.");
            LocalDate date = input.date("Established date (MM/dd/yyyy): ", d -> d.isAfter(LocalDate.now()));
            String plate = input.text("License plate: ", s -> cars.containsKey(key(s)), "Unregistered vehicle");
            String name = input.text("Customer name: ", s -> !s.isBlank(), "Customer name is required.");
            int period = input.integer("Insurance period (12/24/36): ", n -> n == 12 || n == 24 || n == 36,
                    "Choose 12, 24, or 36.");
            long fee = calculateFee(cars.get(key(plate)).getValue(), period);
            statements.put(key(id), new InsuranceStatement(id, date, cars.get(key(plate)).getLicensePlate(), name, period, fee));
            changed = true; System.out.printf("Insurance statement added. Fee: $%,d%n", fee);
        } while (input.yesNo("Add another statement? (Y/N): "));
    }

    public static long calculateFee(long value, int period) {
        return Math.round(value * (period == 12 ? .25 : period == 24 ? .40 : .45));
    }

    private void listStatements() {
        int year = input.integer("Year: ", n -> n > 0, "Enter a valid year.");
        int field = input.integer("Sort by 1-ID, 2-Date, 3-License plate, 4-Period: ", n -> n >= 1 && n <= 4, "Choose 1 to 4.");
        boolean asc = input.text("Sort type (ASC/DESC): ", s -> s.equalsIgnoreCase("ASC") || s.equalsIgnoreCase("DESC"),
                "Enter ASC or DESC.").equalsIgnoreCase("ASC");
        Comparator<InsuranceStatement> comparator = switch (field) {
            case 1 -> Comparator.comparing(InsuranceStatement::getId, String.CASE_INSENSITIVE_ORDER);
            case 2 -> Comparator.comparing(InsuranceStatement::getEstablishedDate);
            case 3 -> Comparator.comparing(InsuranceStatement::getLicensePlate, String.CASE_INSENSITIVE_ORDER);
            default -> Comparator.comparingInt(InsuranceStatement::getPeriod);
        };
        List<InsuranceStatement> result = statements.values().stream().filter(s -> s.getEstablishedDate().getYear() == year)
                .sorted(asc ? comparator : comparator.reversed()).toList();
        System.out.printf("Report: INSURANCE STATEMENTS%nFrom: %s To: %s%n", Year.of(year).atMonth(Month.JANUARY).atDay(1), Year.of(year).atMonth(Month.DECEMBER).atEndOfMonth());
        System.out.printf("%-4s %-12s %-12s %-14s %-24s %-8s %12s%n", "No.", "ID", "Date", "License", "Customer", "Period", "Fee");
        int n = 1; for (InsuranceStatement s : result) System.out.printf("%-4d %-12s %-12s %-14s %-24s %-8d $%,11d%n", n++, s.getId(), s.getEstablishedDate().format(Input.DATE_FORMAT), s.getLicensePlate(), s.getCustomerName(), s.getPeriod(), s.getFee());
        if (result.isEmpty()) System.out.println("No insurance statements found.");
    }

    private void reportUninsured() {
        int field = input.integer("Sort by 1-License plate, 2-Owner, 3-Registration date, 4-Vehicle type: ", n -> n >= 1 && n <= 4, "Choose 1 to 4.");
        boolean asc = input.text("Sort type (ASC/DESC): ", s -> s.equalsIgnoreCase("ASC") || s.equalsIgnoreCase("DESC"), "Enter ASC or DESC.").equalsIgnoreCase("ASC");
        Comparator<Car> comparator = switch (field) {
            case 1 -> Comparator.comparing(Car::getLicensePlate, String.CASE_INSENSITIVE_ORDER);
            case 2 -> Comparator.comparing(Car::getOwner, String.CASE_INSENSITIVE_ORDER);
            case 3 -> Comparator.comparing(Car::getRegistrationDate);
            default -> Comparator.comparingInt(Car::getVehicleType);
        };
        Set<String> insured = new HashSet<>(); for (InsuranceStatement s : statements.values()) insured.add(key(s.getLicensePlate()));
        List<Car> result = cars.values().stream().filter(c -> !insured.contains(key(c.getLicensePlate())))
                .sorted(asc ? comparator : comparator.reversed()).toList();
        System.out.println("Report: UNINSURED CARS"); int n = 1;
        for (Car car : result) printCar(car, n++);
        if (result.isEmpty()) System.out.println("No uninsured cars found.");
    }

    private void printCar(Car c, int number) {
        System.out.printf("%d. %-14s %-10s %d-seat %-20s %-15s $%,d (%s)%n", number, c.getLicensePlate(),
                c.getRegistrationDate().format(Input.DATE_FORMAT), c.getVehicleType(), c.getOwner(), c.getBrand(), c.getValue(), c.getRegistrationPlace());
    }

    private void save() {
        try { BinaryStore.save(CAR_FILE, cars); BinaryStore.save(INSURANCE_FILE, statements); changed = false; System.out.println("Data saved successfully."); }
        catch (IOException e) { System.out.println("Unable to save data: " + e.getMessage()); }
    }

    private void load(boolean announce) {
        try { cars = BinaryStore.load(CAR_FILE); statements = BinaryStore.load(INSURANCE_FILE); changed = false; if (announce) System.out.println("Data loaded successfully."); }
        catch (IOException | ClassNotFoundException e) { System.out.println("Unable to load data: " + e.getMessage()); }
    }

    private boolean quit() {
        if (!input.yesNo("Quit program? (Y/N): ")) return false;
        if (changed) save();
        System.out.println("Goodbye!"); return true;
    }

    private String requiredOrOld(String label, String old, java.util.function.Predicate<String> valid) {
        while (true) {
            String value = input.optional(label + (old == null ? ": " : " [" + old + "] (blank to keep): "));
            if (old != null && value.isEmpty()) return old;
            if (valid.test(value)) return value;
            System.out.println("Invalid value.");
        }
    }

    private long optionalLong(String label, long old) {
        while (true) { String s = input.optional(label + " [" + old + "] (blank to keep): "); if (s.isEmpty()) return old; try { long n = Long.parseLong(s); if (n > 999) return n; } catch (NumberFormatException ignored) { } System.out.println("Enter a number over 999."); }
    }

    private LocalDate optionalDate(String label, LocalDate old, java.util.function.Predicate<LocalDate> valid) {
        while (true) { String s = input.optional(label + " [" + old.format(Input.DATE_FORMAT) + "] (blank to keep): "); if (s.isEmpty()) return old; try { LocalDate d = LocalDate.parse(s, Input.DATE_FORMAT); if (valid.test(d)) return d; } catch (Exception ignored) { } System.out.println("Enter a valid past date in MM/dd/yyyy format."); }
    }

    private int seatType(String prompt) { return input.integer(prompt, n -> n == 5 || n == 7 || n == 9, "Choose 5, 7, or 9."); }
    private int optionalSeatType(int old) { String s = input.optional("Vehicle type [" + old + "] (blank to keep): "); if (s.isEmpty()) return old; while (!s.equals("5") && !s.equals("7") && !s.equals("9")) s = input.optional("Choose 5, 7, or 9: "); return Integer.parseInt(s); }
    private static String key(String value) { return value.trim().toUpperCase(); }
}
