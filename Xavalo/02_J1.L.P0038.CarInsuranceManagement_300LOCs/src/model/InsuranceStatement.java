package model;

import java.io.Serializable;
import java.time.LocalDate;

public class InsuranceStatement implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String id;
    private final LocalDate establishedDate;
    private final String licensePlate;
    private final String customerName;
    private final int period;
    private final long fee;

    public InsuranceStatement(String id, LocalDate establishedDate, String licensePlate,
                              String customerName, int period, long fee) {
        this.id = id;
        this.establishedDate = establishedDate;
        this.licensePlate = licensePlate;
        this.customerName = customerName;
        this.period = period;
        this.fee = fee;
    }

    public String getId() { return id; }
    public LocalDate getEstablishedDate() { return establishedDate; }
    public String getLicensePlate() { return licensePlate; }
    public String getCustomerName() { return customerName; }
    public int getPeriod() { return period; }
    public long getFee() { return fee; }
}
