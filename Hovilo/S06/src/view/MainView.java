package view;
import controller.Inputter;
import java.util.Arrays;
import model.IntegerArray;
/** Displays the array manipulation menu. @author Ho Vi Lo @since 09/09/2026 */
public class MainView {
    private final Inputter inputter = new Inputter();
    private final IntegerArray array = new IntegerArray();
    /** Displays the menu and handles choices until another number is entered. */
    public void run() {
        while (true) {
            System.out.println("1. Add a value\n2. Search a value\n3. Print the array");
            System.out.println("4. Print values in a range\n5. Sort ascending\nOthers. Quit");
            int choice = inputter.readInt("Your choice: ");
            if (choice < 1 || choice > 5) return;
            if (choice == 1) {
                try { array.add(inputter.readInt("Value: ")); }
                catch (IllegalStateException exception) { System.out.println(exception.getMessage()); }
            } else if (choice == 2) {
                int value = inputter.readInt("Search value: ");
                int index = array.search(value);
                System.out.println(index < 0 ? "Value was not found." : "Index: " + index);
            } else if (choice == 3) {
                System.out.println(Arrays.toString(array.getValues()));
            } else if (choice == 4) {
                int min = inputter.readInt("Minimum: ");
                int max = inputter.readInt("Maximum: ");
                if (min > max) System.out.println("Minimum must not be greater than maximum.");
                else System.out.println(Arrays.toString(array.valuesInRange(min, max)));
            } else {
                array.sortAscending();
                System.out.println("The array was sorted.");
            }
        }
    }
}
