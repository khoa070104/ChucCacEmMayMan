package view;

public final class Menu {
    private Menu() {}

    public static void display() {
        System.out.println("\n========== SHOE MANAGEMENT ==========");
        System.out.println("1. List all shoes");
        System.out.println("2. Add a new shoe");
        System.out.println("3. Search shoe by ID");
        System.out.println("4. Update shoe by ID");
        System.out.println("5. Remove shoe by ID");
        System.out.println("6. List shoes by maximum price");
        System.out.println("7. List shoes by minimum quantity");
        System.out.println("8. Save data to file");
        System.out.println("9. Quit program");
        System.out.println("=====================================");
    }
}
