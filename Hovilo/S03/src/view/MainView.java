package view;

import controller.Inputter;

import model.NumberConverter;

/**
 * Displays the numeral conversion menu.
 *
 * @author Ho Vi Lo
 * @since 09/09/2026
 */
public class MainView {
    private final Inputter inputter = new Inputter();
    private final NumberConverter converter = new NumberConverter();

    /**
     * Displays the menu and performs conversions until Exit is selected.
     */
    public void run() {
        while (true) {
            System.out.println("1. Convert binary number to decimal number");
            System.out.println("2. Convert octal number to decimal number");
            System.out.println("3. Convert hexadecimal number to decimal number");
            System.out.println("4. Exit");
            int choice = inputter.readChoice("Please choose number (1-4): ");
            if (choice == 4) return;
            int base = choice == 1 ? 2 : choice == 2 ? 8 : 16;
            String name = choice == 1 ? "binary" : choice == 2 ? "octal" : "hexadecimal";
            String number = inputter.readNumber("Enter " + name + " number: ", base);
            try {
                System.out.println("Decimal number is: " + converter.toDecimal(number, base));
            } catch (ArithmeticException exception) {
                System.out.println("The number is too large. Please try a shorter number.");
            }
        }
    }
}
