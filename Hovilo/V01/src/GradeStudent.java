import java.util.Scanner;

/**
 * Reads course scores and reports the overall percentage and minimum grade.
 *
 * @author Ho Vi Lo
 * @since 09/09/2026
 */
public class GradeStudent {
    private final Scanner scanner = new Scanner(System.in);
    private int usedWeight;
    private double totalWeightedScore;

    /**
     * Controls the program in the order required by the assignment.
     * @param args command-line arguments; not used
     */
    public static void main(String[] args) {
        GradeStudent program = new GradeStudent();
        program.begin();
        program.midTerm();
        program.finalTerm();
        program.homework();
        program.report();
    }

    /** Displays a short explanation of the program. */
    public void begin() {
        System.out.println("This program reads exam/homework scores");
        System.out.println("and reports your overall course grade.\n");
    }

    /** Reads and calculates the midterm weighted score. */
    public void midTerm() { totalWeightedScore += readExam("Midterm"); }

    /** Reads and calculates the final-term weighted score. */
    public void finalTerm() { totalWeightedScore += readExam("Final"); }

    /**
     * Reads assignments and attendance, then calculates the homework score.
     * Assignment earned and possible points are capped proportionally at 150;
     * attendance is five points per section and capped at 30.
     */
    public void homework() {
        System.out.println("Homework:");
        int weight = readWeight(true);
        int assignmentCount = readInt("Number of assignments: ", 0, Integer.MAX_VALUE);
        double earned = 0;
        double possible = 0;
        for (int i = 1; i <= assignmentCount; i++) {
            int score = readInt("Assignment " + i + " score: ", 0, Integer.MAX_VALUE);
            int max = readInt("Assignment " + i + " max: ", Math.max(1, score), Integer.MAX_VALUE);
            earned += score;
            possible += max;
        }
        earned = Math.min(earned, 150);
        possible = Math.min(possible, 150);
        int sections = readInt("How many sections did you attend: ", 0, Integer.MAX_VALUE);
        int sectionPoints = Math.min(sections * 5, 30);
        System.out.println("Section points = " + sectionPoints + " / 30");
        double total = earned + sectionPoints;
        double maximum = possible + 30;
        double weighted = maximum == 0 ? 0 : total / maximum * weight;
        System.out.printf("Total points = %.1f / %.1f%n", total, maximum);
        System.out.printf("Weighted score = %.1f / %d%n%n", weighted, weight);
        totalWeightedScore += weighted;
    }

    /** Displays the overall percentage, minimum grade, and a short message. */
    public void report() {
        double percentage = Math.round(totalWeightedScore * 10) / 10.0;
        double grade = percentage >= 85 ? 3.0 : percentage >= 75 ? 2.0 : percentage >= 60 ? 0.7 : 0.0;
        System.out.println("Overall percentage = " + percentage);
        System.out.println("Your grade will be at least: " + grade);
        System.out.println(grade >= 3 ? "Excellent work!" : grade >= 2 ? "Good effort." : "Keep studying and improving.");
    }

    /**
     * Reads one exam and calculates its weighted score.
     * @param title exam title @return weighted exam score
     */
    private double readExam(String title) {
        System.out.println(title + ":");
        int weight = readWeight(false);
        int score = readInt("Score earned: ", 0, 100);
        int shifted = readInt("Were scores shifted (1 = yes, 2 = no): ", 1, 2);
        int shift = shifted == 1 ? readInt("Shift amount: ", 0, Integer.MAX_VALUE) : 0;
        int total = Math.min(score + shift, 100);
        double weighted = total / 100.0 * weight;
        System.out.println("Total points = " + total + " / 100");
        System.out.printf("Weighted score = %.1f / %d%n%n", weighted, weight);
        return weighted;
    }

    /**
     * Reads a valid weight and ensures that all three weights total exactly 100.
     * @param last true when reading homework weight @return accepted weight
     */
    private int readWeight(boolean last) {
        while (true) {
            int weight = readInt("Weight (0-100): ", 0, 100);
            if ((!last && usedWeight + weight <= 100) || (last && usedWeight + weight == 100)) {
                usedWeight += weight;
                return weight;
            }
            System.out.println(last ? "The three weights must total exactly 100."
                    : "The total weight must not exceed 100.");
        }
    }

    /**
     * Reads an integer inside an inclusive range.
     * @param message prompt @param min minimum @param max maximum @return valid integer
     */
    private int readInt(String message, int min, int max) {
        while (true) {
            System.out.print(message);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= min && value <= max) return value;
            } catch (NumberFormatException exception) { /* Print one common message below. */ }
            System.out.println("Please enter an integer from " + min + " to " + max + ".");
        }
    }
}
