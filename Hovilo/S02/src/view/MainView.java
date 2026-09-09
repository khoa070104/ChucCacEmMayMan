package view;

import controller.Inputter;
import model.StringReverser;

/**
 * Displays the reverse-string program.
 *
 * @author Ho Vi Lo
 * @since 09/09/2026
 */
public class MainView {
    private final Inputter inputter = new Inputter();
    private final StringReverser reverser = new StringReverser();

    /**
     * Repeats the reverse task until the user presses Escape then Enter.
     */
    public void run() {
        boolean continueProgram;
        do {
            String original = inputter.readRequired("Please enter string: ");
            System.out.println("The old string: " + original);
            System.out.println("The reversed string: " + reverser.reverse(original));
            continueProgram = inputter.wantToContinue();
        } while (continueProgram);
    }
}
