package business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import model.Player;
import tools.Acceptable;
import tools.Inputter;

public class Players extends ArrayList<Player> {

    private static final String TABLE_HEADER = "------------------------------------------------------------------------------\n"
            + "Player ID| Club ID   | Player Name           | Position    | Shirt No.\n"
            + "------------------------------------------------------------------------------";
    private static final String TABLE_FOOTER = "------------------------------------------------------------------------------";

    private static final String SORTED_TABLE_HEADER = "--------------------------------------------------------------------------------------------\n"
            + "Player ID| Club ID   | Club Name                 | Player Name           | Position    | Shirt No.\n"
            + "--------------------------------------------------------------------------------------------";
    private static final String SORTED_TABLE_FOOTER = "--------------------------------------------------------------------------------------------";

    private final String pathFile;
    private boolean saved;

    public Players(String pathFile) {
        this.pathFile = pathFile;
        this.saved = true;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public Player searchById(String id) {
        for (Player p : this) {
            if (p.getId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }

    public boolean isDuplicateId(String id) {
        return searchById(id) != null;
    }

    public boolean isShirtNumberUsed(String clubId, int shirtNumber, String excludePlayerId) {
        for (Player p : this) {
            if (p.getClubId().equalsIgnoreCase(clubId)
                    && p.getShirtNumber() == shirtNumber
                    && (excludePlayerId == null || !p.getId().equalsIgnoreCase(excludePlayerId))) {
                return true;
            }
        }
        return false;
    }

    public static Player parseLine(String line) {
        if (line == null || line.trim().isEmpty()) {
            return null;
        }
        String[] parts = line.split(",", 5);
        if (parts.length != 5) {
            return null;
        }
        String id = parts[0].trim();
        String clubId = parts[1].trim();
        String name = parts[2].trim();
        String position = parts[3].trim();
        String shirtStr = parts[4].trim();
        if (!Acceptable.isValid(id, Acceptable.PLAYER_ID_VALID)
                || !Acceptable.isValid(clubId, Acceptable.CLUB_ID_VALID)
                || !Acceptable.isValid(name, Acceptable.NON_EMPTY_VALID)
                || !Acceptable.isValidPosition(position)
                || !Acceptable.isValid(shirtStr, Acceptable.SHIRT_NUMBER_VALID)) {
            return null;
        }
        int shirtNumber = Integer.parseInt(shirtStr);
        if (shirtNumber < 1 || shirtNumber > 99) {
            return null;
        }
        return new Player(id, clubId, name, Acceptable.normalizePosition(position), shirtNumber);
    }

    public static boolean isValidPlayer(Player player, Clubs clubs, Players existing,
            boolean checkPlayerIdDuplicate, String excludePlayerIdForShirt) {
        if (player == null) {
            return false;
        }
        if (!Acceptable.isValid(player.getId(), Acceptable.PLAYER_ID_VALID)
                || !clubs.exists(player.getClubId())
                || !Acceptable.isValid(player.getName(), Acceptable.NON_EMPTY_VALID)
                || !Acceptable.isValidPosition(player.getPosition())
                || player.getShirtNumber() < 1
                || player.getShirtNumber() > 99) {
            return false;
        }
        if (checkPlayerIdDuplicate && existing.isDuplicateId(player.getId())) {
            return false;
        }
        return !existing.isShirtNumberUsed(player.getClubId(), player.getShirtNumber(), excludePlayerIdForShirt);
    }

    private void showList(List<Player> list, boolean withClubName, Clubs clubs) {
        if (list.isEmpty()) {
            System.out.println("No player data available.");
            return;
        }
        if (withClubName) {
            System.out.println(SORTED_TABLE_HEADER);
            for (Player p : list) {
                System.out.println(p.toStringWithClubName(clubs.getClubName(p.getClubId())));
            }
            System.out.println(SORTED_TABLE_FOOTER);
        } else {
            System.out.println(TABLE_HEADER);
            for (Player p : list) {
                System.out.println(p);
            }
            System.out.println(TABLE_FOOTER);
        }
    }

    public void showAllSorted(Clubs clubs) {
        List<Player> sorted = new ArrayList<>(this);
        sorted.sort(new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                String name1 = clubs.getClubName(p1.getClubId());
                String name2 = clubs.getClubName(p2.getClubId());
                int cmp = name1.compareToIgnoreCase(name2);
                if (cmp != 0) {
                    return cmp;
                }
                return Integer.compare(p1.getShirtNumber(), p2.getShirtNumber());
            }
        });
        showList(sorted, true, clubs);
    }

    public void searchByPartialName(Inputter inputter, Clubs clubs) {
        String keyword = inputter.getString("Enter partial player name: ");
        if (keyword.isEmpty()) {
            System.out.println("No player data available.");
            return;
        }
        List<Player> result = new ArrayList<>();
        String lower = keyword.toLowerCase();
        for (Player p : this) {
            if (p.getName().toLowerCase().contains(lower)) {
                result.add(p);
            }
        }
        if (result.isEmpty()) {
            System.out.println("No player matches the search criteria.");
            return;
        }
        showList(result, false, clubs);
    }

    public void addPlayer(Inputter inputter, Clubs clubs) {
        clubs.showAll();
        String id = inputter.inputAndLoop("Player ID: ", Acceptable.PLAYER_ID_VALID);
        if (isDuplicateId(id)) {
            System.out.println("This player ID already exists!");
            return;
        }
        String clubId = inputter.getString("Club ID: ");
        if (!clubs.exists(clubId)) {
            System.out.println("This club does not exist!");
            return;
        }
        String name = inputter.inputAndLoop("Player name: ", Acceptable.NON_EMPTY_VALID);
        String position = inputter.inputPosition();
        String shirtStr = inputter.inputAndLoop("Shirt number [1-99]: ", Acceptable.SHIRT_NUMBER_VALID);
        int shirtNumber = Integer.parseInt(shirtStr);
        if (isShirtNumberUsed(clubId, shirtNumber, null)) {
            System.out.println("This shirt number already exists in this club!");
            return;
        }
        Player player = new Player(id, clubId, name, position, shirtNumber);
        add(player);
        saved = false;
        System.out.println("Player added successfully!");
    }

    public void removePlayer(Inputter inputter) {
        String id = inputter.getString("Enter Player ID: ");
        Player player = searchById(id);
        if (player == null) {
            System.out.println("This player does not exist!");
            return;
        }
        remove(player);
        saved = false;
        System.out.println("Player removed successfully!");
    }

    public void updatePlayer(Inputter inputter) {
        String id = inputter.getString("Enter Player ID: ");
        Player player = searchById(id);
        if (player == null) {
            System.out.println("This player does not exist!");
            return;
        }
        String name = inputter.inputOptional("New player name (blank to skip): ", Acceptable.NON_EMPTY_VALID);
        String position = inputter.inputOptionalPosition();
        String shirtStr = inputter.inputOptional("New shirt number (blank to skip): ", Acceptable.SHIRT_NUMBER_VALID);
        if (!name.isEmpty()) {
            player.setName(name);
        }
        if (!position.isEmpty()) {
            player.setPosition(position);
        }
        if (!shirtStr.isEmpty()) {
            int shirtNumber = Integer.parseInt(shirtStr);
            if (isShirtNumberUsed(player.getClubId(), shirtNumber, player.getId())) {
                System.out.println("This shirt number already exists in this club!");
                return;
            }
            player.setShirtNumber(shirtNumber);
        }
        saved = false;
        System.out.println("Player updated successfully!");
    }

    public void listByPosition(Inputter inputter) {
        String positionInput = inputter.getString("Enter position: ");
        if (!Acceptable.isValidPosition(positionInput)) {
            System.out.println("Invalid position.");
            return;
        }
        String position = Acceptable.normalizePosition(positionInput);
        List<Player> result = new ArrayList<>();
        for (Player p : this) {
            if (p.getPosition().equalsIgnoreCase(position)) {
                result.add(p);
            }
        }
        if (result.isEmpty()) {
            System.out.println("No player plays in this position.");
            return;
        }
        showList(result, false, null);
    }

    public boolean loadFromFile(Clubs clubs) {
        File file = new File(pathFile);
        if (!file.exists()) {
            clear();
            return true;
        }
        List<Player> loaded = new ArrayList<>();
        Players tempPlayers = new Players(pathFile);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                Player player = parseLine(line);
                tempPlayers.clear();
                tempPlayers.addAll(loaded);
                if (player == null
                        || !clubs.exists(player.getClubId())
                        || !isValidPlayer(player, clubs, tempPlayers, true, null)) {
                    return false;
                }
                loaded.add(player);
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
            for (Player p : this) {
                bw.write(p.toFileLine());
                bw.newLine();
            }
            saved = true;
        } catch (Exception e) {
            System.out.println("Error saving player data: " + e.getMessage());
        }
    }
}
