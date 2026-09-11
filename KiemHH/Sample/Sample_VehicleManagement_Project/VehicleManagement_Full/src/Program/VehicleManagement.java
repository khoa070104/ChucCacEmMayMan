package Program;

import DataObject.VehicleDAO;

import Entity.Vehicle;

import Utilities.DataInput;
import Utilities.DataValidation;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class VehicleManagement {

    // ---------------------------------------------------
    private final VehicleDAO vehicleDAO;

    public VehicleManagement(String fileName) throws Exception {
        this.vehicleDAO = new VehicleDAO(fileName);
    }

    // ---------------------------------------------------
    public void printList(List<Vehicle> vehicles) {

        System.out.println(String.join("", Collections.nCopies(100, "-")));
        System.out.format(
                "%-10s | %-15s | %-10s | %-12s | %-15s | %-12s | %-10s%n",
                "ID", "Name", "Color", "Price", "Brand", "Type", "Year");

        System.out.println(String.join("", Collections.nCopies(100, "-")));

        for (Vehicle v : vehicles) {
            System.out.format(
                    "%-10s | %-15s | %-10s | %-12.0f | %-15s | %-12s | %-10d%n",
                    v.getId(),
                    v.getName(),
                    v.getColor(),
                    v.getPrice(),
                    v.getBrand(),
                    v.getType(),
                    v.getProductYear());
        }
        System.out.printf("%d vehicle(s) in list.%n", vehicles.size());
        System.out.println(String.join("", Collections.nCopies(100, "-")));
    }

    // ---------------------------------------------------
    public void printVehicleList() {
        printList(vehicleDAO.getVehicleList());
    }

    // ---------------------------------------------------
    public void printVehicleListFromFile() throws Exception {
        printList(vehicleDAO.getVehicleListFromFile());
    }

    // ---------------------------------------------------
    public void printVehicleListByPriceDESC() {
        // search vehicle by price then print vehicle list by Price DESC
        double price = Double.parseDouble(DataInput.getString("Enter price: "));
        List<Vehicle> vehicles = vehicleDAO.findVehicleByPrice(price);
        vehicles.sort(Comparator.comparing(Vehicle::getPrice).reversed());
        printList(vehicles);
    }

    // ---------------------------------------------------
    public void checkExists() {
        // System.out.println("This function not available");
        String id = DataInput.getString("Enter id: ");
        if (vehicleDAO.findVehicleById(id) != null) {
            System.out.println("The vehicle has existed");
        } else {
            System.out.println("The vehicle not found");
        }
    }

    // ---------------------------------------------------
    public void addVehicle() {
        try {

            String id = DataInput.getString("Enter id: ");
            String name = DataInput.getString("Enter name: ");
            String color = DataInput.getString("Enter color: ");
            double price = DataInput.getDoubleNumber("Enter price: ");
            String brand = DataInput.getString("Enter brand: ");
            String type = DataInput.getString("Enter type: ");
            int productYear = DataInput.getIntegerNumber("Enter product year: ");

            Vehicle vehicle = new Vehicle(id, name, color, price, brand, type, productYear);

            if (!vehicleDAO.addVehicle(vehicle)) {
                System.out.println("Vehicle already exists.");
            } else {
                System.out.println("Vehicle has added successfully.");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // ---------------------------------------------------
    public void removeVehicle() {

        try {

            String id = DataInput.getString("Enter id: ");

            if (vehicleDAO.removeVehicle(id)) {
                System.out.println("Vehicle has removed successfully.");
            } else {
                System.out.println("Vehicle not found.");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // ---------------------------------------------------
    public void updateVehicle() {

        try {

            String id = DataInput.getString("Enter id: ");

            Vehicle vehicle = vehicleDAO.findVehicleById(id);

            if (vehicle == null) {
                System.out.println("Vehicle not found.");
                return;
            }

            String name = DataInput.getString("Enter name: ");
            if (DataValidation.checkStringEmpty(name)) {
                vehicle.setName(name);
            }

            String color = DataInput.getString("Enter color: ");
            if (DataValidation.checkStringEmpty(color)) {
                vehicle.setColor(color);
            }

            String strPrice = DataInput.getString("Enter price: ");
            if (DataValidation.checkStringEmpty(strPrice)) {
                vehicle.setPrice(Double.parseDouble(strPrice));
            }

            String brand = DataInput.getString("Enter brand: ");
            if (DataValidation.checkStringEmpty(brand)) {
                vehicle.setBrand(brand);
            }

            String type = DataInput.getString("Enter type: ");
            if (DataValidation.checkStringEmpty(type)) {
                vehicle.setType(type);
            }

            String strYear = DataInput.getString("Enter product year: ");
            if (DataValidation.checkStringEmpty(strYear)) {
                vehicle.setProductYear(Integer.parseInt(strYear));
            }

            if (vehicleDAO.updateVehicle(vehicle)) {
                System.out.println("Vehicle has updated successfully.");
            } else {
                System.out.println("Vehicle not found.");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // ---------------------------------------------------
    public void findVehicleById() {
        try {
            String id = DataInput.getString("Enter id: ");
            Vehicle vehicle = vehicleDAO.findVehicleById(id);
            if (vehicle != null) {
                //                System.out.println(String.join("", Collections.nCopies(100,
                // "-")));
                //                System.out.format(
                //                        "%-10s | %-15s | %-10s | %-12s | %-15s | %-12s | %-10s%n",
                //                        "ID", "Name", "Color", "Price", "Brand", "Type", "Year");
                //                System.out.format(
                //                        "%-10s | %-15s | %-10s | %-12.0f | %-15s | %-12s |
                // %-10d%n",
                //                        vehicle.getId(),
                //                        vehicle.getName(),
                //                        vehicle.getColor(),
                //                        vehicle.getPrice(),
                //                        vehicle.getBrand(),
                //                        vehicle.getType(),
                //                        vehicle.getProductYear());
                printList(Collections.singletonList(vehicle));
            } else {
                System.out.println("Vehicle not found.");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // ---------------------------------------------------
    public void findVehicleByName() {
        try {
            String name = DataInput.getString("Enter vehicle name: ");
            List<Vehicle> vehicles = vehicleDAO.findVehicleByName(name);
            if (vehicles.isEmpty()) {
                System.out.println("Not found.");
            } else {
                printList(vehicles);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // ---------------------------------------------------
    public void saveVehicles() {

        try {

            vehicleDAO.saveVehicleListToFile();
            System.out.println("Vehicles saved.");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
