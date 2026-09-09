package controller;
import java.util.Scanner;
/** Reads validated console input. @author Ho Vi Lo @since 09/09/2026 */
public class Inputter {
    private final Scanner scanner = new Scanner(System.in);
    /** @param message prompt @return entered trimmed text, possibly blank */ public String readLine(String message) { System.out.print(message); return scanner.nextLine().trim(); }
    /** @param message prompt @return non-empty text */ public String readRequired(String message) { while (true) { String text = readLine(message); if (!text.isEmpty()) return text; System.out.println("Input must not be empty."); } }
    /** @param message prompt @return entered integer */ public int readInt(String message) { while (true) { try { return Integer.parseInt(readLine(message)); } catch (NumberFormatException exception) { System.out.println("Please enter a valid integer."); } } }
    /** @param message prompt @param allowBlank true to return null for blank @return positive price or null */
    public Double readPrice(String message, boolean allowBlank) { while (true) { String text = readLine(message); if (allowBlank && text.isEmpty()) return null; try { double value = Double.parseDouble(text); if (value > 0) return value; } catch (NumberFormatException exception) { /* Common message below. */ } System.out.println("Price must be a positive number."); } }
    /** @param message prompt @return true for Y, false for N */ public boolean readYesNo(String message) { while (true) { String text = readLine(message); if (text.equalsIgnoreCase("Y")) return true; if (text.equalsIgnoreCase("N")) return false; System.out.println("Please enter Y or N."); } }
}
