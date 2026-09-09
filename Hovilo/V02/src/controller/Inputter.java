package controller;
import java.util.Scanner;
/** Reads console input for V02. @author Ho Vi Lo @since 09/09/2026 */
public class Inputter {
    private final Scanner scanner = new Scanner(System.in);
    /** @param message prompt @return entered line */ public String readLine(String message) { System.out.print(message); return scanner.nextLine().trim(); }
    /** @param message prompt @return non-empty line */
    public String readRequired(String message) { while (true) { String value = readLine(message); if (!value.isEmpty()) return value; System.out.println("Input must not be empty."); } }
    /** @param message prompt @param allowBlank whether blank returns -1 @return non-negative integer, or -1 for allowed blank */
    public int readAvailability(String message, boolean allowBlank) {
        while (true) { String value = readLine(message); if (allowBlank && value.isEmpty()) return -1;
            try { int number = Integer.parseInt(value); if (number >= 0) return number; }
            catch (NumberFormatException exception) { /* Common message below. */ }
            System.out.println("Availability must be a non-negative integer."); }
    }
    /** @return entered integer menu choice */ public int readChoice() { while (true) { try { return Integer.parseInt(readLine("Please choose: ")); } catch (NumberFormatException exception) { System.out.println("Please enter an integer."); } } }
}
