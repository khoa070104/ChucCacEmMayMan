package view;

import java.util.List;
import model.Candidate;
import service.CandidateService;
import utils.Inputter;

/**
 * Console UI for candidate management.
 */
public class CandidateView {

    private static final int TYPE_EXPERIENCE = 0;
    private static final int TYPE_FRESHER = 1;
    private static final int TYPE_INTERN = 2;

    public void displayMainMenu() {
        System.out.println("\nCANDIDATE MANAGEMENT SYSTEM");
        System.out.println("1. Experience");
        System.out.println("2. Fresher");
        System.out.println("3. Internship");
        System.out.println("4. Searching");
        System.out.println("5. Exit");
        System.out.println("(Please choose 1 to Create Experience Candidate, 2 to Create Fresher Candidate,");
        System.out.println(" 3 to Internship Candidate, 4 to Searching and 5 to Exit program).");
    }

    public int getMainMenuChoice() {
        while (true) {
            try {
                String input = Inputter.inputRequired("Your choice: ");
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= 5) {
                    return choice;
                }
                System.out.println("Please enter a number from 1 to 5!");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }

    public void displayAllCandidates(CandidateService service) {
        if (!service.hasCandidates()) {
            System.out.println("\nNo candidates created yet.");
            return;
        }
        System.out.println("\nList of candidate:");
        printGroup("===========EXPERIENCE CANDIDATE============", service.getByType(TYPE_EXPERIENCE));
        printGroup("==========FRESHER CANDIDATE==============", service.getByType(TYPE_FRESHER));
        printGroup("===========INTERN CANDIDATE==============", service.getByType(TYPE_INTERN));
    }

    public void displayCandidateListForSearch(CandidateService service) {
        System.out.println("\nList of candidate:");
        printGroup("===========EXPERIENCE CANDIDATE============", service.getByType(TYPE_EXPERIENCE));
        printGroup("==========FRESHER CANDIDATE==============", service.getByType(TYPE_FRESHER));
        printGroup("===========INTERN CANDIDATE==============", service.getByType(TYPE_INTERN));
    }

    private void printGroup(String header, List<Candidate> list) {
        System.out.println(header);
        if (list.isEmpty()) {
            System.out.println("(empty)");
            return;
        }
        for (Candidate c : list) {
            System.out.println(c.getFullName());
        }
    }
}
