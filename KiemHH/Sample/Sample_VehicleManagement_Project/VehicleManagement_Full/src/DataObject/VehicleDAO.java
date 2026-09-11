package DataObject;

import Entity.Vehicle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class VehicleDAO {

    private final List<Vehicle> vehicleList = new ArrayList();
    private final FileManager fileManager;

    public VehicleDAO(String fileName) throws Exception {
        this.fileManager = new FileManager(fileName);
    }

    // --------------------------------------------------

    public void loadDataFromFile() throws Exception {
        String id, name, color, brand, type;
        double price;
        int productYear;
        try {
            vehicleList.clear();
            List<String> empData = fileManager.readDataFromFile();
            for (String e : empData) {
                List<String> vehicle = Arrays.asList(e.split(","));
                id = vehicle.get(0).trim();
                name = vehicle.get(1).trim();
                color = vehicle.get(2).trim();
                price = Double.parseDouble(vehicle.get(3).trim());
                brand = vehicle.get(4).trim();
                type = vehicle.get(5).trim();
                productYear = Integer.parseInt(vehicle.get(6).trim());
                Vehicle newEmp = new Vehicle(id, name, color, price, brand, type, productYear);
                vehicleList.add(newEmp);
            }
        } catch (Exception ex) {
            throw new Exception("Can not read data from file.Please check file again.");
        }
    }

    // --------------------------------------------------
    public List<Vehicle> getVehicleListFromFile() throws Exception {
        loadDataFromFile();
        return vehicleList;
    }

    // --------------------------------------------------
    public List<Vehicle> getVehicleList() {

        // Method 01
        // sort by getPrice DESC
        // vehicleList.sort(Comparator.comparing(Vehicle::getPrice).reversed()); //DESC
        // sort by warranty ASC
        // vehicleList.sort(Comparator.comparing(Phone::getWarranty)); //ASC
        // sort by name ASC and then by warranty DESC
        // vehicleList.sort(Comparator.comparing(Phone::getName)
        //        .thenComparing(Phone::getWarranty,Comparator.reverseOrder()));
        return vehicleList;
    }

    // --------------------------------------------------

    public void saveVehicleListToFile() throws Exception {
        List<String> stringObjects =
                vehicleList.stream().map(String::valueOf).collect(Collectors.toList());
        String data = String.join("\n", stringObjects);
        fileManager.saveDataToFile(data);
    }

    // --------------------------------------------------
    public boolean addVehicle(Vehicle vehicle) {
        if (findVehicleById(vehicle.getId()) != null) {
            return false;
        }
        vehicleList.add(vehicle);
        return true;
    }

    // --------------------------------------------------

    public boolean removeVehicle(String idVehicle) {
        Vehicle vehicle = findVehicleById(idVehicle);
        if (vehicle == null) {
            return false;
        }
        vehicleList.remove(vehicle);
        return true;
    }

    // --------------------------------------------------
    public boolean updateVehicle(Vehicle vehicle) {
        Vehicle v = findVehicleById(vehicle.getId());
        if (v == null) {
            return false;
        }
        int index = vehicleList.indexOf(v);
        vehicleList.set(index, vehicle);
        return true;
    }

    // --------------------------------------------------

    public Vehicle findVehicleById(String id) {
        for (Vehicle v : vehicleList) {
            if (v.getId().equalsIgnoreCase(id)) {
                return v;
            }
        }
        return null;
    }

    // --------------------------------------------------
    // Find by keyword of vehicle name

    public List<Vehicle> findVehicleByName(String name) {
        List<Vehicle> list = new ArrayList<>();
        if (name == null || name.trim().isEmpty()) {
            return Collections.emptyList();
        }
        for (Vehicle v : vehicleList) {
            if (v.getName() != null && v.getName().toLowerCase().contains(name.toLowerCase())) {
                list.add(v);
            }
        }
        return list;
    }

    public List<Vehicle> findVehicleByPrice(double price) {
        List<Vehicle> list = new ArrayList<>();
        for (Vehicle v : vehicleList) {
            if (v.getPrice() < price) {
                list.add(v);
            }
        }
        return list;
    }
}
