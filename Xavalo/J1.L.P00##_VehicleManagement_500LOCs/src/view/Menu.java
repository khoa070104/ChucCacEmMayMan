package view;

public final class Menu {
    private Menu() {}

    public static void mainMenu() {
        System.out.println("\n========== VEHICLE MANAGEMENT ==========");
        System.out.println("1. Add new vehicle");
        System.out.println("2. Check existing vehicle in file");
        System.out.println("3. Update vehicle");
        System.out.println("4. Delete vehicle");
        System.out.println("5. Search vehicle");
        System.out.println("6. Display vehicle list");
        System.out.println("7. Save data to file");
        System.out.println("8. Print vehicle list from file");
        System.out.println("9. Quit");
        System.out.println("========================================");
    }

    public static void searchMenu() {
        System.out.println("1. Search by name");
        System.out.println("2. Search by ID");
        System.out.println("3. Back");
    }

    public static void displayMenu() {
        System.out.println("1. Display all vehicles");
        System.out.println("2. Show vehicles by price");
        System.out.println("3. Back");
    }
}
