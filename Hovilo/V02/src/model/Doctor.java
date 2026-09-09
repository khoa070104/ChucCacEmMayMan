package model;

/** Stores one doctor's information. @author Ho Vi Lo @since 09/09/2026 */
public class Doctor {
    private final String code;
    private String name;
    private String specialization;
    private int availability;

    /** @param code unique code @param name name @param specialization specialty @param availability available slots */
    public Doctor(String code, String name, String specialization, int availability) {
        this.code = code; this.name = name; this.specialization = specialization; this.availability = availability;
    }
    /** @return doctor code */ public String getCode() { return code; }
    /** @return doctor name */ public String getName() { return name; }
    /** @param name new name */ public void setName(String name) { this.name = name; }
    /** @return specialization */ public String getSpecialization() { return specialization; }
    /** @param specialization new specialization */ public void setSpecialization(String specialization) { this.specialization = specialization; }
    /** @return availability */ public int getAvailability() { return availability; }
    /** @param availability new availability */ public void setAvailability(int availability) { this.availability = availability; }
}
