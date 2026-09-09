package controller;
import java.util.Scanner;
/** Reads integer console input safely. @author Ho Vi Lo @since 09/09/2026 */
public class Inputter {
    private final Scanner scanner = new Scanner(System.in);
    /** @param message prompt @return entered integer */
    public int readInt(String message) {
        while (true) {
            System.out.print(message);
            try { return Integer.parseInt(scanner.nextLine().trim()); }
            catch (NumberFormatException exception) { System.out.println("Please enter a valid integer."); }
        }
    }
}
