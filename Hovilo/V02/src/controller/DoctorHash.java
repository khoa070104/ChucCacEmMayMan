package controller;

import model.Doctor;

import java.util.LinkedHashMap;
import java.util.Map;

/** Manages doctors in a map indexed by code. @author Ho Vi Lo @since 09/09/2026 */
public class DoctorHash {
    private final Map<String, Doctor> doctors = new LinkedHashMap<>();

    /** @param doctor doctor to add @return true after addition @throws Exception when data is missing or code is duplicated */
    public boolean addDoctor(Doctor doctor) throws Exception {
        checkDoctorData(doctor);
        if (doctors.containsKey(doctor.getCode()))
            throw new Exception("Doctor code " + doctor.getCode() + " is duplicate.");
        doctors.put(doctor.getCode(), doctor);
        return true;
    }

    /** @param doctor doctor carrying replacement values @return true after update @throws Exception when data/code is invalid */
    public boolean updateDoctor(Doctor doctor) throws Exception {
        if (doctors.isEmpty()) throw new Exception("Database does not exist.");
        if (doctor == null || doctor.getCode() == null || doctor.getCode().trim().isEmpty())
            throw new Exception("Data does not exist.");
        if (doctor.getAvailability() < -1)
            throw new Exception("Availability must be greater than or equal to zero.");
        Doctor current = doctors.get(doctor.getCode());
        if (current == null) throw new Exception("Doctor code does not exist.");
        if (!doctor.getName().isEmpty()) current.setName(doctor.getName());
        if (!doctor.getSpecialization().isEmpty())
            current.setSpecialization(doctor.getSpecialization());
        if (doctor.getAvailability() >= 0) current.setAvailability(doctor.getAvailability());
        return true;
    }

    /** @param doctor doctor whose code will be deleted @return true after deletion @throws Exception when database/data/code is invalid */
    public boolean deleteDoctor(Doctor doctor) throws Exception {
        if (doctors.isEmpty()) throw new Exception("Database does not exist.");
        if (doctor == null || doctor.getCode() == null || doctor.getCode().trim().isEmpty())
            throw new Exception("Data does not exist.");
        if (doctors.remove(doctor.getCode()) == null)
            throw new Exception("Doctor code does not exist.");
        return true;
    }

    /**
     * Searches code, name, and specialization without case sensitivity.
     * @param input search text @return matching doctors keyed by code
     * @throws Exception when the database does not exist
     */
    public Map<String, Doctor> searchDoctor(String input) throws Exception {
        if (doctors.isEmpty()) throw new Exception("Database does not exist.");
        String key = input == null ? "" : input.toLowerCase();
        Map<String, Doctor> result = new LinkedHashMap<>();
        for (Doctor doctor : doctors.values()) {
            if (doctor.getCode().toLowerCase().contains(key)
                    || doctor.getName().toLowerCase().contains(key)
                    || doctor.getSpecialization().toLowerCase().contains(key))
                result.put(doctor.getCode(), doctor);
        }
        return result;
    }

    /** @param doctor doctor to validate @throws Exception when required data is missing or availability is negative */
    private void checkDoctorData(Doctor doctor) throws Exception {
        if (doctor == null
                || doctor.getCode() == null
                || doctor.getCode().trim().isEmpty()
                || doctor.getName() == null
                || doctor.getSpecialization() == null) throw new Exception("Data does not exist.");
        if (doctor.getAvailability() < 0)
            throw new Exception("Availability must be greater than or equal to zero.");
    }
}
