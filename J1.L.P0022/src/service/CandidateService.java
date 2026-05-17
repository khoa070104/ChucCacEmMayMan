package service;

import java.io.IOException;
import java.util.List;
import model.Candidate;
import model.ExperienceCandidate;
import model.FresherCandidate;
import model.InternCandidate;
import repository.CandidateRepository;
import utils.Inputter;

/**
 * Business logic for candidate management.
 */
public class CandidateService {

    private static final int TYPE_EXPERIENCE = 0;
    private static final int TYPE_FRESHER = 1;
    private static final int TYPE_INTERN = 2;

    private final CandidateRepository repository;

    public CandidateService(CandidateRepository repository) {
        this.repository = repository;
    }

    public void createExperienceCandidates() throws IOException {
        createCandidatesLoop(TYPE_EXPERIENCE);
    }

    public void createFresherCandidates() throws IOException {
        createCandidatesLoop(TYPE_FRESHER);
    }

    public void createInternCandidates() throws IOException {
        createCandidatesLoop(TYPE_INTERN);
    }

    private void createCandidatesLoop(int type) throws IOException {
        do {
            switch (type) {
                case TYPE_EXPERIENCE:
                    repository.add(inputExperienceCandidate());
                    break;
                case TYPE_FRESHER:
                    repository.add(inputFresherCandidate());
                    break;
                case TYPE_INTERN:
                    repository.add(inputInternCandidate());
                    break;
                default:
                    return;
            }
            System.out.println("Candidate created successfully!");
        } while (Inputter.inputYesNo().equalsIgnoreCase("Y"));
    }

    private ExperienceCandidate inputExperienceCandidate() {
        System.out.println("\n--- CREATE EXPERIENCE CANDIDATE ---");
        String id = Inputter.inputRequired("Enter Candidate ID: ");
        String firstName = Inputter.inputRequired("Enter First Name: ");
        String lastName = Inputter.inputRequired("Enter Last Name: ");
        int birthDate = Integer.parseInt(Inputter.inputBirthYear());
        String address = Inputter.inputRequired("Enter Address: ");
        String phone = Inputter.inputPhone();
        String email = Inputter.inputEmail();
        int expInYear = Inputter.inputExpInYear();
        String proSkill = Inputter.inputRequired("Enter Professional Skill: ");
        return new ExperienceCandidate(id, firstName, lastName, birthDate,
                address, phone, email, expInYear, proSkill);
    }

    private FresherCandidate inputFresherCandidate() {
        System.out.println("\n--- CREATE FRESHER CANDIDATE ---");
        String id = Inputter.inputRequired("Enter Candidate ID: ");
        String firstName = Inputter.inputRequired("Enter First Name: ");
        String lastName = Inputter.inputRequired("Enter Last Name: ");
        int birthDate = Integer.parseInt(Inputter.inputBirthYear());
        String address = Inputter.inputRequired("Enter Address: ");
        String phone = Inputter.inputPhone();
        String email = Inputter.inputEmail();
        int graduationDate = Integer.parseInt(Inputter.inputGraduationYear());
        String graduationRank = Inputter.inputGraduationRank();
        String education = Inputter.inputRequired("Enter Education (University): ");
        return new FresherCandidate(id, firstName, lastName, birthDate,
                address, phone, email, graduationDate, graduationRank, education);
    }

    private InternCandidate inputInternCandidate() {
        System.out.println("\n--- CREATE INTERN CANDIDATE ---");
        String id = Inputter.inputRequired("Enter Candidate ID: ");
        String firstName = Inputter.inputRequired("Enter First Name: ");
        String lastName = Inputter.inputRequired("Enter Last Name: ");
        int birthDate = Integer.parseInt(Inputter.inputBirthYear());
        String address = Inputter.inputRequired("Enter Address: ");
        String phone = Inputter.inputPhone();
        String email = Inputter.inputEmail();
        String majors = Inputter.inputRequired("Enter Majors: ");
        String semester = Inputter.inputRequired("Enter Semester: ");
        String university = Inputter.inputRequired("Enter University Name: ");
        return new InternCandidate(id, firstName, lastName, birthDate,
                address, phone, email, majors, semester, university);
    }

    public List<Candidate> search(String name, int type) {
        return repository.findByNameAndType(name, type);
    }

    public List<Candidate> getAllCandidates() {
        return repository.findAll();
    }

    public List<Candidate> getByType(int type) {
        return repository.findByType(type);
    }

    public boolean hasCandidates() {
        return !repository.isEmpty();
    }
}
