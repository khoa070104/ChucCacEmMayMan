package view;

import model.LetterCounter;

import java.util.Scanner;

/** Displays the letter-counting program. @author Ho Vi Lo @since 09/09/2026 */
public class MainView {
    private final Scanner scanner = new Scanner(System.in);
    private final LetterCounter counter = new LetterCounter();

    /** Prompts for text and displays every appearing letter with its count. */
    public void run() {
        System.out.print("Please enter a string: ");
        int[] counts = counter.count(scanner.nextLine());
        for (int i = 0; i < counts.length; i++)
            if (counts[i] > 0) System.out.println((char) ('a' + i) + ": " + counts[i]);
    }
}
