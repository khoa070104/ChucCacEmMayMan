package model;

import common.Constants;

public class Doctor {
    private String code;
    private String name;
    private String specialization;
    private int availability;

    public Doctor() {
    }

    public Doctor(String code, String name, String specialization, int availability) {
        this.code = code;
        this.name = name;
        this.specialization = specialization;
        this.availability = availability;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public boolean checkAvailability() {
        return availability >= Constants.MIN_AVAILABILITY;
    }

    @Override
    public String toString() {
        return String.format("%s\t%s\t%s\t%d", 
            code, name, specialization, availability);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Doctor doctor = (Doctor) obj;
        return code.equals(doctor.code);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }
}
