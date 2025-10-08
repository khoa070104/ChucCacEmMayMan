package controller;

import java.util.HashMap;
import model.Doctor;
import common.Messages;

public class DoctorHash {
    private HashMap<String, Doctor> doctorMap;

    public DoctorHash() {
        this.doctorMap = new HashMap<>();
    }

    public boolean addDoctor(Doctor doctor) throws Exception {
        // Check if database exists
        if (doctorMap == null) {
            throw new Exception(Messages.ERR_DATABASE_NULL);
        }

        // Check if doctor is null
        if (doctor == null) {
            throw new Exception(Messages.ERR_DOCTOR_NULL);
        }

        // Check if doctor code is duplicate
        if (doctorMap.containsKey(doctor.getCode())) {
            throw new Exception(String.format(Messages.ERR_DOCTOR_CODE_DUPLICATE, doctor.getCode()));
        }

        // Check availability
        if (!doctor.checkAvailability()) {
            throw new Exception(Messages.ERR_AVAILABILITY_INVALID);
        }

        // Add doctor to HashMap
        doctorMap.put(doctor.getCode(), doctor);
        return true;
    }

    public boolean updateDoctor(Doctor doctor) throws Exception {
        // Check if database exists
        if (doctorMap == null) {
            throw new Exception(Messages.ERR_DATABASE_NULL);
        }

        // Check if doctor is null
        if (doctor == null) {
            throw new Exception(Messages.ERR_DOCTOR_NULL);
        }

        // Check if doctor code exists
        if (!doctorMap.containsKey(doctor.getCode())) {
            throw new Exception(Messages.ERR_DOCTOR_CODE_NOT_EXIST);
        }

        // Check availability if provided
        if (doctor.getAvailability() >= 0 && !doctor.checkAvailability()) {
            throw new Exception(Messages.ERR_AVAILABILITY_INVALID);
        }

        // Update doctor in HashMap
        doctorMap.put(doctor.getCode(), doctor);
        return true;
    }

    public boolean deleteDoctor(Doctor doctor) throws Exception {
        // Check if database exists
        if (doctorMap == null) {
            throw new Exception(Messages.ERR_DATABASE_NULL);
        }

        // Check if doctor is null
        if (doctor == null) {
            throw new Exception(Messages.ERR_DOCTOR_NULL);
        }

        // Check if doctor code exists
        if (!doctorMap.containsKey(doctor.getCode())) {
            throw new Exception(Messages.ERR_DOCTOR_CODE_NOT_EXIST);
        }

        // Remove doctor from HashMap
        doctorMap.remove(doctor.getCode());
        return true;
    }

    public HashMap<String, Doctor> searchDoctor(String input) throws Exception {
        // Check if database exists
        if (doctorMap == null) {
            throw new Exception(Messages.ERR_DATABASE_NULL);
        }

        HashMap<String, Doctor> result = new HashMap<>();
        String searchInput = input.toLowerCase();

        // Search through all doctors
        for (Doctor doctor : doctorMap.values()) {
            if (doctor.getCode().toLowerCase().contains(searchInput) ||
                doctor.getName().toLowerCase().contains(searchInput) ||
                doctor.getSpecialization().toLowerCase().contains(searchInput)) {
                result.put(doctor.getCode(), doctor);
            }
        }

        return result;
    }

    public Doctor getDoctorByCode(String code) {
        return doctorMap.get(code);
    }

    public HashMap<String, Doctor> getAllDoctors() {
        return new HashMap<>(doctorMap);
    }

    public int getDoctorCount() {
        return doctorMap.size();
    }

    public boolean isEmpty() {
        return doctorMap.isEmpty();
    }
}
