package model;

public class Club extends Entity {

    private String name;
    private String sponsorBrand;
    private double budget;

    public Club() {
    }

    public Club(String id, String name, String sponsorBrand, double budget) {
        super(id);
        this.name = name;
        this.sponsorBrand = sponsorBrand;
        this.budget = budget;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSponsorBrand() {
        return sponsorBrand;
    }

    public void setSponsorBrand(String sponsorBrand) {
        this.sponsorBrand = sponsorBrand;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    @Override
    public String toFileLine() {
        return String.format("%s, %s, %s, %.0f", id, name, sponsorBrand, budget);
    }

    @Override
    public String toString() {
        return String.format("%-10s| %-26s| %-14s| %,.0f",
                id, name, sponsorBrand, budget);
    }
}
