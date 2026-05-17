package dispatcher;

import business.Clubs;
import business.Players;
import tools.Inputter;

public class Main {

    private static final String CLUB_FILE = "data/clubs.txt";
    private static final String PLAYER_FILE = "data/players.txt";

    private final Inputter inputter;
    private final Clubs clubs;
    private final Players players;

    public Main() {
        inputter = new Inputter();
        clubs = new Clubs(CLUB_FILE);
        players = new Players(PLAYER_FILE);
        loadData(false);
    }

    private void showMenu() {
        System.out.println();
        System.out.println("========== EUROPEAN ELITE LEAGUE - CLUB & PLAYER MANAGEMENT ==========");
        System.out.println("1.  List of all clubs");
        System.out.println("2.  Add a new club");
        System.out.println("3.  Search for a club by ID");
        System.out.println("4.  Update a club by ID");
        System.out.println("5.  List all clubs with budget <= input value");
        System.out.println("6.  List all players (by club name, then shirt number)");
        System.out.println("7.  Search players by partial player name");
        System.out.println("8.  Add a new player");
        System.out.println("9.  Remove a player with ID");
        System.out.println("10. Update a player with ID");
        System.out.println("11. List all players by a specific position");
        System.out.println("12. Save data to files");
        System.out.println("13. Load data from files");
        System.out.println("14. Quit program");
        System.out.println("======================================================================");
    }

    private void loadData(boolean showMessage) {
        Clubs tempClubs = new Clubs(CLUB_FILE);
        if (!tempClubs.loadFromFile()) {
            clubs.clear();
            players.clear();
            if (showMessage) {
                System.out.println("Load data failed!");
            }
            return;
        }
        Players tempPlayers = new Players(PLAYER_FILE);
        if (!tempPlayers.loadFromFile(tempClubs)) {
            clubs.clear();
            players.clear();
            if (showMessage) {
                System.out.println("Load data failed!");
            }
            return;
        }
        clubs.clear();
        clubs.addAll(tempClubs);
        clubs.setSaved(true);
        players.clear();
        players.addAll(tempPlayers);
        players.setSaved(true);
        if (showMessage) {
            System.out.println("Load data successfully!");
        }
    }

    private void saveData() {
        clubs.saveToFile();
        players.saveToFile();
        System.out.println("Data saved successfully!");
    }

    private boolean hasUnsavedChanges() {
        return !clubs.isSaved() || !players.isSaved();
    }

    public void run() {
        String choice;
        do {
            showMenu();
            choice = inputter.getString("Enter your choice [1-14]: ");
            switch (choice) {
                case "1":
                    clubs.showAll();
                    break;
                case "2":
                    clubs.addClub(inputter);
                    break;
                case "3":
                    clubs.searchById(inputter);
                    break;
                case "4":
                    clubs.updateClub(inputter);
                    break;
                case "5":
                    clubs.listByBudget(inputter);
                    break;
                case "6":
                    players.showAllSorted(clubs);
                    break;
                case "7":
                    players.searchByPartialName(inputter, clubs);
                    break;
                case "8":
                    players.addPlayer(inputter, clubs);
                    break;
                case "9":
                    players.removePlayer(inputter);
                    break;
                case "10":
                    players.updatePlayer(inputter);
                    break;
                case "11":
                    players.listByPosition(inputter);
                    break;
                case "12":
                    saveData();
                    break;
                case "13":
                    loadData(true);
                    break;
                case "14":
                    if (hasUnsavedChanges()) {
                        saveData();
                    }
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("This function is not available.");
                    break;
            }
        } while (!choice.equals("14"));
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
