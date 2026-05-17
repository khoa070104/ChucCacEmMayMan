package controller;

import java.io.IOException;
import java.util.List;
import model.Candidate;
import repository.CandidateRepository;
import service.CandidateService;
import utils.Inputter;
import view.CandidateView;

/**
 * Coordinates view and service layers.
 */
public class CandidateController {

    private final CandidateService service;
    private final CandidateView view;

    public CandidateController() {
        CandidateRepository repository = new CandidateRepository();
        this.service = new CandidateService(repository);
        this.view = new CandidateView();
    }

    public void run() {
        while (true) {
            view.displayMainMenu();
            int choice = view.getMainMenuChoice();
            try {
                switch (choice) {
                    case 1:
                        service.createExperienceCandidates();
                        view.displayAllCandidates(service);
                        break;
                    case 2:
                        service.createFresherCandidates();
                        view.displayAllCandidates(service);
                        break;
                    case 3:
                        service.createInternCandidates();
                        view.displayAllCandidates(service);
                        break;
                    case 4:
                        handleSearch();
                        break;
                    case 5:
                        System.out.println("Thank you for using Candidate Management System!");
                        return;
                    default:
                        System.out.println("Invalid choice! Please select 1 to 5.");
                }
            } catch (IOException e) {
                System.out.println("Error saving candidate: " + e.getMessage());
            }
        }
    }

    private void handleSearch() {
        if (!service.hasCandidates()) {
            System.out.println("No candidates available. Please create candidates first.");
            return;
        }

        view.displayCandidateListForSearch(service);

        String name = Inputter.inputRequired("\nInput Candidate name (First name or Last name): ");
        int type = Inputter.inputCandidateType();

        List<Candidate> found = service.search(name, type);
        System.out.println("\nThe candidates found:");
        if (found.isEmpty()) {
            System.out.println("No matching candidates.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < found.size(); i++) {
            if (i > 0) {
                sb.append(" ");
            }
            sb.append(found.get(i).toSearchResult());
        }
        System.out.println(sb);
    }
}
