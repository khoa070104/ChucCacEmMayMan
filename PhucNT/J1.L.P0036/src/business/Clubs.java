package business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import model.Club;
import tools.Acceptable;
import tools.Inputter;

public class Clubs extends ArrayList<Club> {

    private static final String TABLE_HEADER = "--------------------------------------------------------------------------\n"
            + "Club ID   | Club Name                 | Sponsor Brand | Budget\n"
            + "--------------------------------------------------------------------------";
    private static final String TABLE_FOOTER = "--------------------------------------------------------------------------";

    private final String pathFile;
    private boolean saved;

    public Clubs(String pathFile) {
        this.pathFile = pathFile;
        this.saved = true;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public Club searchById(String id) {
        for (Club c : this) {
            if (c.getId().equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    }

    public boolean isDuplicateId(String id) {
        return searchById(id) != null;
    }

    public boolean exists(String clubId) {
        return searchById(clubId) != null;
    }

    public String getClubName(String clubId) {
        Club club = searchById(clubId);
        return club != null ? club.getName() : "";
    }

    public static Club parseLine(String line) {
        if (line == null || line.trim().isEmpty()) {
            return null;
        }
        String[] parts = line.split(",", 4);
        if (parts.length != 4) {
            return null;
        }
        String id = parts[0].trim();
        String name = parts[1].trim();
        String sponsor = parts[2].trim();
        String budgetStr = parts[3].trim();
        if (!Acceptable.isValid(id, Acceptable.CLUB_ID_VALID)
                || !Acceptable.isValid(name, Acceptable.NON_EMPTY_VALID)
                || !Acceptable.isValid(sponsor, Acceptable.NON_EMPTY_VALID)
                || !Acceptable.isPositiveReal(budgetStr)) {
            return null;
        }
        return new Club(id, name, sponsor, Double.parseDouble(budgetStr));
    }

    public static boolean isValidClub(Club club, Clubs existing, boolean checkDuplicate) {
        if (club == null) {
            return false;
        }
        if (!Acceptable.isValid(club.getId(), Acceptable.CLUB_ID_VALID)
                || !Acceptable.isValid(club.getName(), Acceptable.NON_EMPTY_VALID)
                || !Acceptable.isValid(club.getSponsorBrand(), Acceptable.NON_EMPTY_VALID)
                || club.getBudget() <= 0) {
            return false;
        }
        if (checkDuplicate && existing.isDuplicateId(club.getId())) {
            return false;
        }
        return true;
    }

    public void showAll() {
        if (isEmpty()) {
            System.out.println("No club data available.");
            return;
        }
        System.out.println(TABLE_HEADER);
        for (Club c : this) {
            System.out.println(c);
        }
        System.out.println(TABLE_FOOTER);
    }

    public void showClub(Club club) {
        System.out.println(TABLE_HEADER);
        System.out.println(club);
        System.out.println(TABLE_FOOTER);
    }

    public void addClub(Inputter inputter) {
        String id = inputter.inputAndLoop("Club ID: ", Acceptable.CLUB_ID_VALID);
        if (isDuplicateId(id)) {
            System.out.println("This club ID already exists!");
            return;
        }
        String name = inputter.inputAndLoop("Club name: ", Acceptable.NON_EMPTY_VALID);
        String sponsor = inputter.inputAndLoop("Sponsor brand: ", Acceptable.NON_EMPTY_VALID);
        String budgetStr = inputter.inputPositiveReal("Budget (million EUR): ");
        Club club = new Club(id, name, sponsor, Double.parseDouble(budgetStr));
        add(club);
        saved = false;
        System.out.println("Club added successfully!");
    }

    public void searchById(Inputter inputter) {
        String id = inputter.getString("Enter Club ID: ");
        Club club = searchById(id);
        if (club == null) {
            System.out.println("This club does not exist!");
            return;
        }
        showClub(club);
    }

    public void updateClub(Inputter inputter) {
        String id = inputter.getString("Enter Club ID: ");
        Club club = searchById(id);
        if (club == null) {
            System.out.println("This club does not exist!");
            return;
        }
        String name = inputter.inputOptional("New club name (blank to skip): ", Acceptable.NON_EMPTY_VALID);
        String sponsor = inputter.inputOptional("New sponsor brand (blank to skip): ", Acceptable.NON_EMPTY_VALID);
        String budgetStr = inputter.inputOptionalPositiveReal("New budget (blank to skip): ");
        if (!name.isEmpty()) {
            club.setName(name);
        }
        if (!sponsor.isEmpty()) {
            club.setSponsorBrand(sponsor);
        }
        if (!budgetStr.isEmpty()) {
            club.setBudget(Double.parseDouble(budgetStr));
        }
        saved = false;
        System.out.println("Club updated successfully!");
    }

    public void listByBudget(Inputter inputter) {
        double maxBudget = inputter.getDouble("Enter budget value (million EUR): ");
        List<Club> result = new ArrayList<>();
        for (Club c : this) {
            if (c.getBudget() <= maxBudget) {
                result.add(c);
            }
        }
        if (result.isEmpty()) {
            System.out.println("No club matches the criteria.");
            return;
        }
        System.out.println(TABLE_HEADER);
        for (Club c : result) {
            System.out.println(c);
        }
        System.out.println(TABLE_FOOTER);
    }

    public boolean loadFromFile() {
        File file = new File(pathFile);
        if (!file.exists()) {
            clear();
            return true;
        }
        List<Club> loaded = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                Club club = parseLine(line);
                Clubs temp = new Clubs(pathFile);
                temp.addAll(loaded);
                if (club == null || !isValidClub(club, temp, true)) {
                    return false;
                }
                loaded.add(club);
            }
        } catch (Exception e) {
            return false;
        }
        clear();
        addAll(loaded);
        saved = true;
        return true;
    }

    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathFile))) {
            for (Club c : this) {
                bw.write(c.toFileLine());
                bw.newLine();
            }
            saved = true;
        } catch (Exception e) {
            System.out.println("Error saving club data: " + e.getMessage());
        }
    }
}
