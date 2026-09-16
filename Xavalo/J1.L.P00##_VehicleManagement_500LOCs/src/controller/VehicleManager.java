package controller;

import model.Vehicle;
import repository.VehicleRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class VehicleManager {
    private final List<Vehicle> vehicles;
    private final VehicleRepository repository;

    public VehicleManager() {
        repository = new VehicleRepository();
        List<Vehicle> loaded;
        try {
            loaded = repository.load();
        } catch (IOException e) {
            loaded = new ArrayList<>();
        }
        vehicles = loaded;
    }

    public boolean add(Vehicle vehicle) {
        if (findById(vehicle.getId()) != null) return false;
        vehicles.add(vehicle);
        return true;
    }

    public Vehicle findById(String id) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId().equalsIgnoreCase(id)) return vehicle;
        }
        return null;
    }

    public boolean existsInFile(String id) throws IOException {
        return repository.existsInFile(id);
    }

    public boolean delete(String id) {
        Vehicle vehicle = findById(id);
        return vehicle != null && vehicles.remove(vehicle);
    }

    public List<Vehicle> searchByName(String keyword) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(vehicle);
            }
        }
        result.sort(Comparator.comparing(Vehicle::getName,
                String.CASE_INSENSITIVE_ORDER).reversed());
        return result;
    }

    public List<Vehicle> findCheaperThan(double price) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getPrice() < price) result.add(vehicle);
        }
        result.sort(Comparator.comparingDouble(Vehicle::getPrice).reversed());
        return result;
    }

    public List<Vehicle> getAll() {
        return new ArrayList<>(vehicles);
    }

    public void save() throws IOException {
        repository.save(vehicles);
    }

    public List<Vehicle> readFromFile() throws IOException {
        return repository.load();
    }
}
