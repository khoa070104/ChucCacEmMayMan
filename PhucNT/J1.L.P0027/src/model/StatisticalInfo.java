package model;

public class StatisticalInfo {

    private String mountainCode;
    private String peakName;
    private int numOfStudent;
    private double totalCost;

    public StatisticalInfo() {
    }

    public StatisticalInfo(String mountainCode, String peakName) {
        this.mountainCode = mountainCode;
        this.peakName = peakName;
        this.numOfStudent = 0;
        this.totalCost = 0;
    }

    public void addStudent(double fee) {
        numOfStudent++;
        totalCost += fee;
    }

    public String getMountainCode() {
        return mountainCode;
    }

    public void setMountainCode(String mountainCode) {
        this.mountainCode = mountainCode;
    }

    public String getPeakName() {
        return peakName;
    }

    public void setPeakName(String peakName) {
        this.peakName = peakName;
    }

    public int getNumOfStudent() {
        return numOfStudent;
    }

    public void setNumOfStudent(int numOfStudent) {
        this.numOfStudent = numOfStudent;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-22d | %,.0f", peakName, numOfStudent, totalCost);
    }
}
