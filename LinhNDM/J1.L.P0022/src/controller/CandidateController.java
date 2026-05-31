package controller;

import common.Messages;
import java.io.IOException;
import java.util.ArrayList;
import model.Candidate;
import model.Candidates;
import model.ExperienceCandidate;
import model.FresherCandidate;
import model.InternCandidate;
import model.Validator;
import repository.CandidateRepository;
import view.CandidateView;

/**
 * Điều phối luồng chương trình giữa View và Model.
 *
 * @author LinhNDM
 */
public class CandidateController {

    private final Candidates candidateList;
    private final CandidateRepository repository;
    private final CandidateView view;

    /**
     * Khởi tạo controller với model, repository và view.
     */
    public CandidateController() {
        this.candidateList = new Candidates();
        this.repository = new CandidateRepository();
        this.view = new CandidateView();
        repository.loadInto(candidateList);
    }

    /**
     * Chạy chương trình với menu điều hướng chính.
     */
    public void run() {
        int choice;
        while (true) {
            view.displayMainMenu();
            choice = view.getMainMenuChoice();
            try {
                switch (choice) {
                    case 1:
                        createExperienceCandidates();
                        view.displayAllCandidates(candidateList);
                        break;
                    case 2:
                        createFresherCandidates();
                        view.displayAllCandidates(candidateList);
                        break;
                    case 3:
                        createInternCandidates();
                        view.displayAllCandidates(candidateList);
                        break;
                    case 4:
                        searchCandidates();
                        break;
                    case 5:
                        view.displayMessage(Messages.MSG_PROGRAM_EXIT);
                        return;
                    default:
                        view.displayMessage(Messages.ERR_INVALID_INPUT);
                }
            } catch (IOException exception) {
                view.displayMessage(Messages.ERR_SAVE_CANDIDATE
                        + exception.getMessage());
            }
        }
    }

    /**
     * Tạo một hoặc nhiều ứng viên kinh nghiệm.
     */
    public void createExperienceCandidates() throws IOException {
        String candidateId;
        String firstName;
        String lastName;
        int birthDate;
        String address;
        String phone;
        String email;
        int expInYear;
        String proSkill;
        String continueChoice;
        ExperienceCandidate candidate;
        do {
            view.displayCreateExperienceHeader();
            do {
                candidateId = view.inputCandidateId();
                if (candidateList.isDuplicateId(candidateId)) {
                    view.displayMessage(Messages.ERR_DUP_CANDIDATE_ID);
                }
            } while (candidateList.isDuplicateId(candidateId));
            firstName = view.inputFirstName();
            lastName = view.inputLastName();
            birthDate = Integer.parseInt(view.inputBirthYear());
            address = view.inputAddress();
            phone = view.inputPhone();
            email = view.inputEmail();
            expInYear = view.inputExpInYear();
            proSkill = view.inputProSkill();
            candidate = new ExperienceCandidate(candidateId, firstName, lastName,
                    birthDate, address, phone, email, expInYear, proSkill);
            candidateList.addCandidate(candidate);
            repository.saveCandidate(candidate);
            view.displayMessage(Messages.MSG_CANDIDATE_CREATED);
            continueChoice = view.inputContinueChoice();
        } while (Validator.isContinue(continueChoice));
    }

    /**
     * Tạo một hoặc nhiều ứng viên fresher.
     */
    public void createFresherCandidates() throws IOException {
        String candidateId;
        String firstName;
        String lastName;
        int birthDate;
        String address;
        String phone;
        String email;
        int graduationDate;
        String graduationRank;
        String education;
        String continueChoice;
        FresherCandidate candidate;
        do {
            view.displayCreateFresherHeader();
            do {
                candidateId = view.inputCandidateId();
                if (candidateList.isDuplicateId(candidateId)) {
                    view.displayMessage(Messages.ERR_DUP_CANDIDATE_ID);
                }
            } while (candidateList.isDuplicateId(candidateId));
            firstName = view.inputFirstName();
            lastName = view.inputLastName();
            birthDate = Integer.parseInt(view.inputBirthYear());
            address = view.inputAddress();
            phone = view.inputPhone();
            email = view.inputEmail();
            graduationDate = Integer.parseInt(view.inputGraduationYear());
            graduationRank = view.inputGraduationRank();
            education = view.inputEducation();
            candidate = new FresherCandidate(candidateId, firstName, lastName,
                    birthDate, address, phone, email,
                    graduationDate, graduationRank, education);
            candidateList.addCandidate(candidate);
            repository.saveCandidate(candidate);
            view.displayMessage(Messages.MSG_CANDIDATE_CREATED);
            continueChoice = view.inputContinueChoice();
        } while (Validator.isContinue(continueChoice));
    }

    /**
     * Tạo một hoặc nhiều ứng viên intern.
     */
    public void createInternCandidates() throws IOException {
        String candidateId;
        String firstName;
        String lastName;
        int birthDate;
        String address;
        String phone;
        String email;
        String majors;
        String semester;
        String universityName;
        String continueChoice;
        InternCandidate candidate;
        do {
            view.displayCreateInternHeader();
            do {
                candidateId = view.inputCandidateId();
                if (candidateList.isDuplicateId(candidateId)) {
                    view.displayMessage(Messages.ERR_DUP_CANDIDATE_ID);
                }
            } while (candidateList.isDuplicateId(candidateId));
            firstName = view.inputFirstName();
            lastName = view.inputLastName();
            birthDate = Integer.parseInt(view.inputBirthYear());
            address = view.inputAddress();
            phone = view.inputPhone();
            email = view.inputEmail();
            majors = view.inputMajors();
            semester = view.inputSemester();
            universityName = view.inputUniversityName();
            candidate = new InternCandidate(candidateId, firstName, lastName,
                    birthDate, address, phone, email,
                    majors, semester, universityName);
            candidateList.addCandidate(candidate);
            repository.saveCandidate(candidate);
            view.displayMessage(Messages.MSG_CANDIDATE_CREATED);
            continueChoice = view.inputContinueChoice();
        } while (Validator.isContinue(continueChoice));
    }

    /**
     * Tìm kiếm ứng viên theo tên và loại.
     */
    public void searchCandidates() {
        String name;
        int type;
        ArrayList<Candidate> resultList;
        if (candidateList.isEmpty()) {
            view.displayMessage(Messages.ERR_NO_CANDIDATES);
            return;
        }
        view.displayCandidateListForSearch(candidateList);
        name = view.inputSearchName();
        type = view.inputCandidateType();
        resultList = candidateList.searchByNameAndType(name, type);
        view.displaySearchResults(resultList);
    }
}
