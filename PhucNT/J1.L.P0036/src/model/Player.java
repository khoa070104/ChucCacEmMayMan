package model;

public class Player extends Entity {

    private String clubId;
    private String name;
    private String position;
    private int shirtNumber;

    public Player() {
    }

    public Player(String id, String clubId, String name, String position, int shirtNumber) {
        super(id);
        this.clubId = clubId;
        this.name = name;
        this.position = position;
        this.shirtNumber = shirtNumber;
    }

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getShirtNumber() {
        return shirtNumber;
    }

    public void setShirtNumber(int shirtNumber) {
        this.shirtNumber = shirtNumber;
    }

    @Override
    public String toFileLine() {
        return String.format("%s, %s, %s, %s, %d", id, clubId, name, position, shirtNumber);
    }

    @Override
    public String toString() {
        return String.format("%-8s| %-10s| %-22s| %-12s| %d",
                id, clubId, name, position, shirtNumber);
    }

    public String toStringWithClubName(String clubName) {
        return String.format("%-8s| %-10s| %-26s| %-22s| %-12s| %d",
                id, clubId, clubName, name, position, shirtNumber);
    }
}
