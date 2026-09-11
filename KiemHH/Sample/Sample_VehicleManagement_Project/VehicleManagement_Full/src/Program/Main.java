package Program;

import Utilities.DataInput;

import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        String vehicleDataFile = "VehicleData.txt";
        VehicleManagement vehicleManagement;
        try {
            vehicleManagement = new VehicleManagement(vehicleDataFile);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return;
        }

        while (true) {
            System.out.println(String.join("", Collections.nCopies(50, "*")));
            System.out.println("========== VEHICLE MANAGEMENT ==========");
            System.out.println("1. Add new vehicle.");
            System.out.println("2. Check exists vehicle.");
            System.out.println("3. Update vehicle.");
            System.out.println("4. Delete vehicle.");
            System.out.println("5. Search by ID vehicle.");
            System.out.println("6. Search by Name vehicle.");
            System.out.println("7. Display all vehicle.");
            System.out.println("8. Show by price.");
            System.out.println("9. Save all vehicles to file.");
            System.out.println("10.Print all vehicles from the file.");
            System.out.println("0. Exit.");
            System.out.println("========================================");
            System.out.print("Please enter your choice: ");
            try {
                int choice = DataInput.getIntegerNumber();
                switch (choice) {
                    case 1:
                        vehicleManagement.addVehicle();
                        break;
                    case 2:
                        vehicleManagement.checkExists();
                        break;
                    case 3:
                        vehicleManagement.updateVehicle();
                        break;
                    case 4:
                        vehicleManagement.removeVehicle();
                        break;
                    case 5:
                        vehicleManagement.findVehicleById();
                        break;
                    case 6:
                        vehicleManagement.findVehicleByName();
                        break;
                    case 7:
                        vehicleManagement.printVehicleList();
                        break;
                    case 8:
                        vehicleManagement.printVehicleListByPriceDESC();
                        break;
                    case 9:
                        vehicleManagement.saveVehicles();
                        break;
                    case 10:
                        vehicleManagement.printVehicleListFromFile();
                        break;
                    case 0:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
