package view;

import common.Constants;
import common.Messages;
import controller.Inputter;
import controller.VehicleManager;
import model.Vehicle;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class VehicleView {
    private final VehicleManager manager = new VehicleManager();

    public void run() {
        while (true) {
            Menu.mainMenu();
            int choice = Inputter.integer("Choose: ", 1, 9);
            switch (choice) {
                case 1: addVehicles(); break;
                case 2: checkExistence(); break;
                case 3: updateVehicle(); break;
                case 4: deleteVehicle(); break;
                case 5: searchVehicle(); break;
                case 6: displayVehicle(); break;
                case 7: save(); break;
                case 8: printFromFile(); break;
                case 9:
                    System.out.println("Goodbye!");
                    return;
                default: System.out.println(Messages.INVALID_CHOICE);
            }
        }
    }

    private void addVehicles() {
        do {
            System.out.println("\n--- Add New Vehicle ---");
            String id;
            do {
                id = Inputter.required("Vehicle ID: ").toUpperCase();
                if (manager.findById(id) != null) System.out.println(Messages.DUPLICATE_ID);
            } while (manager.findById(id) != null);
            String name = Inputter.required("Vehicle name: ");
            String color = Inputter.required("Color: ");
            double price = Inputter.positiveDouble("Price: ");
            String brand = Inputter.required("Brand: ");
            String type = Inputter.required("Type: ");
            int year = Inputter.integer("Product year: ",
                    Constants.FIRST_VEHICLE_YEAR, Constants.CURRENT_YEAR);
            manager.add(new Vehicle(id, name, color, price, brand, type, year));
            System.out.println(Messages.ADDED);
        } while (Inputter.yesNo("Continue adding? (Y/N): "));
    }

    private void checkExistence() {
        String id = Inputter.required("Vehicle ID: ");
        try {
            System.out.println(manager.existsInFile(id) ? Messages.EXISTED : Messages.NOT_FOUND);
        } catch (IOException e) {
            System.out.println(Messages.READ_ERROR);
        }
    }

    private void updateVehicle() {
        Vehicle vehicle = manager.findById(Inputter.required("Vehicle ID: "));
        if (vehicle == null) {
            System.out.println(Messages.NOT_EXIST);
            return;
        }
        System.out.println("Leave blank to keep the current value.");
        String name = Inputter.input("New name: ");
        String color = Inputter.input("New color: ");
        Double price = Inputter.optionalPositiveDouble("New price: ");
        String brand = Inputter.input("New brand: ");
        String type = Inputter.input("New type: ");
        Integer year = Inputter.optionalYear("New product year: ");
        if (!name.isEmpty()) vehicle.setName(name);
        if (!color.isEmpty()) vehicle.setColor(color);
        if (price != null) vehicle.setPrice(price);
        if (!brand.isEmpty()) vehicle.setBrand(brand);
        if (!type.isEmpty()) vehicle.setType(type);
        if (year != null) vehicle.setProductYear(year);
        System.out.println(Messages.UPDATED);
    }

    private void deleteVehicle() {
        String id = Inputter.required("Vehicle ID: ");
        if (manager.findById(id) == null) {
            System.out.println(Messages.NOT_EXIST);
        } else if (!Inputter.yesNo("Confirm deletion? (Y/N): ")) {
            System.out.println("Deletion cancelled.");
        } else if (manager.delete(id)) {
            System.out.println(Messages.DELETED);
        }
    }

    private void searchVehicle() {
        Menu.searchMenu();
        int choice = Inputter.integer("Choose: ", 1, 3);
        if (choice == 1) {
            show(manager.searchByName(Inputter.required("Search name: ")));
        } else if (choice == 2) {
            Vehicle vehicle = manager.findById(Inputter.required("Search ID: "));
            show(vehicle == null ? Collections.emptyList() : Collections.singletonList(vehicle));
        }
    }

    private void displayVehicle() {
        Menu.displayMenu();
        int choice = Inputter.integer("Choose: ", 1, 3);
        if (choice == 1) {
            show(manager.getAll());
        } else if (choice == 2) {
            show(manager.findCheaperThan(Inputter.positiveDouble("Maximum price: ")));
        }
    }

    private void save() {
        try {
            manager.save();
            System.out.println(Messages.SAVED);
        } catch (IOException e) {
            System.out.println(Messages.WRITE_ERROR);
        }
    }

    private void printFromFile() {
        try {
            show(manager.readFromFile());
        } catch (IOException e) {
            System.out.println(Messages.READ_ERROR);
        }
    }

    private void show(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println(Messages.NOT_FOUND);
            return;
        }
        System.out.println("+----------+----------------------+------------+------------+---------------+-------------+------+");
        System.out.printf("| %-8s | %-20s | %-10s | %10s | %-13s | %-11s | %-4s |%n",
                "ID", "Name", "Color", "Price", "Brand", "Type", "Year");
        System.out.println("+----------+----------------------+------------+------------+---------------+-------------+------+");
        for (Vehicle v : vehicles) {
            System.out.printf("| %-8s | %-20s | %-10s | %10.2f | %-13s | %-11s | %4d |%n",
                    v.getId(), v.getName(), v.getColor(), v.getPrice(),
                    v.getBrand(), v.getType(), v.getProductYear());
        }
        System.out.println("+----------+----------------------+------------+------------+---------------+-------------+------+");
    }
}
