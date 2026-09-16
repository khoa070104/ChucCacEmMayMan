package repository;

import common.Constants;
import model.Vehicle;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class VehicleRepository {
    private final String filename;

    public VehicleRepository() {
        filename = Constants.VEHICLE_FILE;
    }

    public List<Vehicle> load() throws IOException {
        List<Vehicle> vehicles = new ArrayList<>();
        File file = new File(filename);
        if (!file.exists() || file.length() == 0) return vehicles;

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    vehicles.add((Vehicle) input.readObject());
                } catch (EOFException e) {
                    break;
                } catch (ClassNotFoundException e) {
                    throw new IOException(e);
                }
            }
        }
        return vehicles;
    }

    public void save(List<Vehicle> vehicles) throws IOException {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename))) {
            for (Vehicle vehicle : vehicles) output.writeObject(vehicle);
        }
    }

    public boolean existsInFile(String id) throws IOException {
        for (Vehicle vehicle : load()) {
            if (vehicle.getId().equalsIgnoreCase(id)) return true;
        }
        return false;
    }
}
