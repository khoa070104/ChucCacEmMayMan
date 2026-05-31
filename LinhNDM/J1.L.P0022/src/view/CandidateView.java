package view;

import common.Constants;
import common.Messages;
import java.util.ArrayList;
import model.Candidate;
import model.Candidates;
import model.Validator;

/**
 * Hiển thị giao diện và thu thập dữ liệu nhập từ người dùng.
 *
 * @author LinhNDM
 */
public class CandidateView {

    /**
     * Hiển thị menu chính của chương trình.
     */
    public void displayMainMenu() {
        System.out.println(Messages.MSG_MENU_TITLE);
        System.out.println("1. Experience");
        System.out.println("2. Fresher");
        System.out.println("3. Internship");
        System.out.println("4. Searching");
        System.out.println("5. Exit");
        System.out.println(Messages.MSG_MENU_GUIDE);
        System.out.println(Messages.MSG_MENU_GUIDE_LINE2);
    }

    /**
     * Nhập lựa chọn menu và validate phạm vi hợp lệ.
     *
     * @return số lựa chọn từ 1 đến 5
     */
    public int getMainMenuChoice() {
        String inputValue;
        int choice;
        while (true) {
            try {
                inputValue = Inputter.inputRequired(Constants.LABEL_YOUR_CHOICE);
                choice = Integer.parseInt(inputValue);
                if (choice >= Constants.MIN_MENU_CHOICE
                        && choice <= Constants.MAX_MENU_CHOICE) {
                    return choice;
                }
                System.out.println(Messages.ERR_MENU_RANGE);
            } catch (NumberFormatException exception) {
                System.out.println(Messages.ERR_INVALID_NUMBER);
            }
        }
    }

    /**
     * Hiển thị danh sách ứng viên theo từng loại.
     *
     * @param candidateList danh sách ứng viên
     */
    public void displayAllCandidates(Candidates candidateList) {
        if (candidateList.isEmpty()) {
            System.out.println(Messages.ERR_NO_CANDIDATES_CREATED);
            return;
        }
        System.out.println(Messages.MSG_CANDIDATE_LIST);
        printGroup(Constants.HEADER_EXPERIENCE,
                candidateList.findByType(Constants.TYPE_EXPERIENCE));
        printGroup(Constants.HEADER_FRESHER,
                candidateList.findByType(Constants.TYPE_FRESHER));
        printGroup(Constants.HEADER_INTERN,
                candidateList.findByType(Constants.TYPE_INTERN));
    }

    /**
     * Hiển thị danh sách ứng viên trước khi tìm kiếm.
     *
     * @param candidateList danh sách ứng viên
     */
    public void displayCandidateListForSearch(Candidates candidateList) {
        System.out.println(Messages.MSG_CANDIDATE_LIST);
        printGroup(Constants.HEADER_EXPERIENCE,
                candidateList.findByType(Constants.TYPE_EXPERIENCE));
        printGroup(Constants.HEADER_FRESHER,
                candidateList.findByType(Constants.TYPE_FRESHER));
        printGroup(Constants.HEADER_INTERN,
                candidateList.findByType(Constants.TYPE_INTERN));
    }

    /**
     * Hiển thị kết quả tìm kiếm ứng viên.
     *
     * @param resultList danh sách ứng viên tìm được
     */
    public void displaySearchResults(ArrayList<Candidate> resultList) {
        StringBuilder resultBuilder;
        Candidate candidate;
        int index;
        System.out.println(Messages.MSG_SEARCH_RESULTS);
        if (resultList.isEmpty()) {
            System.out.println(Messages.ERR_NO_MATCHING_CANDIDATES);
            return;
        }
        resultBuilder = new StringBuilder();
        for (index = 0; index < resultList.size(); index++) {
            if (index > 0) {
                resultBuilder.append(" ");
            }
            candidate = resultList.get(index);
            resultBuilder.append(candidate.toSearchResult());
        }
        System.out.println(resultBuilder);
    }

    /**
     * Hiển thị một thông báo ra màn hình.
     *
     * @param message nội dung thông báo
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }

    /**
     * Hiển thị tiêu đề tạo ứng viên kinh nghiệm.
     */
    public void displayCreateExperienceHeader() {
        System.out.println(Messages.MSG_CREATE_EXPERIENCE);
    }

    /**
     * Hiển thị tiêu đề tạo ứng viên fresher.
     */
    public void displayCreateFresherHeader() {
        System.out.println(Messages.MSG_CREATE_FRESHER);
    }

    /**
     * Hiển thị tiêu đề tạo ứng viên intern.
     */
    public void displayCreateInternHeader() {
        System.out.println(Messages.MSG_CREATE_INTERN);
    }

    /**
     * Nhập mã ứng viên.
     *
     * @return mã ứng viên đã nhập
     */
    public String inputCandidateId() {
        return Inputter.inputRequired(Constants.LABEL_CANDIDATE_ID);
    }

    /**
     * Nhập tên ứng viên.
     *
     * @return tên đã nhập
     */
    public String inputFirstName() {
        return Inputter.inputRequired(Constants.LABEL_FIRST_NAME);
    }

    /**
     * Nhập họ ứng viên.
     *
     * @return họ đã nhập
     */
    public String inputLastName() {
        return Inputter.inputRequired(Constants.LABEL_LAST_NAME);
    }

    /**
     * Nhập năm sinh và validate định dạng.
     *
     * @return năm sinh hợp lệ dạng chuỗi
     */
    public String inputBirthYear() {
        String inputValue;
        do {
            inputValue = Inputter.inputRequired(Constants.LABEL_BIRTH_DATE);
            if (!Validator.isValidBirthYear(inputValue)) {
                System.out.println(Messages.ERR_INVALID_BIRTH_YEAR);
            }
        } while (!Validator.isValidBirthYear(inputValue));
        return inputValue;
    }

    /**
     * Nhập địa chỉ ứng viên.
     *
     * @return địa chỉ đã nhập
     */
    public String inputAddress() {
        return Inputter.inputRequired(Constants.LABEL_ADDRESS);
    }

    /**
     * Nhập số điện thoại và validate định dạng.
     *
     * @return số điện thoại hợp lệ
     */
    public String inputPhone() {
        String inputValue;
        do {
            inputValue = Inputter.inputRequired(Constants.LABEL_PHONE);
            if (!Validator.isValidPhone(inputValue)) {
                System.out.println(Messages.ERR_INVALID_PHONE);
            }
        } while (!Validator.isValidPhone(inputValue));
        return inputValue;
    }

    /**
     * Nhập email và validate định dạng.
     *
     * @return email hợp lệ
     */
    public String inputEmail() {
        String inputValue;
        do {
            inputValue = Inputter.inputRequired(Constants.LABEL_EMAIL);
            if (!Validator.isValidEmail(inputValue)) {
                System.out.println(Messages.ERR_INVALID_EMAIL);
            }
        } while (!Validator.isValidEmail(inputValue));
        return inputValue;
    }

    /**
     * Nhập số năm kinh nghiệm và validate định dạng.
     *
     * @return số năm kinh nghiệm hợp lệ
     */
    public int inputExpInYear() {
        String inputValue;
        do {
            inputValue = Inputter.inputRequired(Constants.LABEL_EXP_IN_YEAR);
            if (!Validator.isValidExpInYear(inputValue)) {
                System.out.println(Messages.ERR_INVALID_EXP_IN_YEAR);
            }
        } while (!Validator.isValidExpInYear(inputValue));
        return Integer.parseInt(inputValue);
    }

    /**
     * Nhập kỹ năng chuyên môn.
     *
     * @return kỹ năng chuyên môn đã nhập
     */
    public String inputProSkill() {
        return Inputter.inputRequired(Constants.LABEL_PRO_SKILL);
    }

    /**
     * Nhập năm tốt nghiệp và validate định dạng.
     *
     * @return năm tốt nghiệp hợp lệ dạng chuỗi
     */
    public String inputGraduationYear() {
        String inputValue;
        do {
            inputValue = Inputter.inputRequired(Constants.LABEL_GRADUATION_DATE);
            if (!Validator.isValidBirthYear(inputValue)) {
                System.out.println(Messages.ERR_INVALID_BIRTH_YEAR);
            }
        } while (!Validator.isValidBirthYear(inputValue));
        return inputValue;
    }

    /**
     * Nhập xếp loại tốt nghiệp và validate định dạng.
     *
     * @return xếp loại tốt nghiệp hợp lệ
     */
    public String inputGraduationRank() {
        String inputValue;
        do {
            inputValue = Inputter.inputRequired(Constants.LABEL_GRADUATION_RANK);
            if (!Validator.isValidGraduationRank(inputValue)) {
                System.out.println(Messages.ERR_INVALID_GRADUATION_RANK);
            }
        } while (!Validator.isValidGraduationRank(inputValue));
        return Validator.normalizeGraduationRank(inputValue);
    }

    /**
     * Nhập thông tin trường đại học.
     *
     * @return tên trường đã nhập
     */
    public String inputEducation() {
        return Inputter.inputRequired(Constants.LABEL_EDUCATION);
    }

    /**
     * Nhập chuyên ngành intern.
     *
     * @return chuyên ngành đã nhập
     */
    public String inputMajors() {
        return Inputter.inputRequired(Constants.LABEL_MAJORS);
    }

    /**
     * Nhập học kỳ intern.
     *
     * @return học kỳ đã nhập
     */
    public String inputSemester() {
        return Inputter.inputRequired(Constants.LABEL_SEMESTER);
    }

    /**
     * Nhập tên trường đại học intern.
     *
     * @return tên trường đã nhập
     */
    public String inputUniversityName() {
        return Inputter.inputRequired(Constants.LABEL_UNIVERSITY);
    }

    /**
     * Nhập lựa chọn tiếp tục thêm ứng viên.
     *
     * @return Y hoặc N
     */
    public String inputContinueChoice() {
        return Inputter.inputRequired(Messages.MSG_CONTINUE_ADDING,
                Constants.YES_NO_VALIDATE);
    }

    /**
     * Nhập tên cần tìm kiếm.
     *
     * @return tên đã nhập
     */
    public String inputSearchName() {
        return Inputter.inputRequired(Constants.LABEL_SEARCH_NAME);
    }

    /**
     * Nhập loại ứng viên cần tìm kiếm.
     *
     * @return loại ứng viên hợp lệ
     */
    public int inputCandidateType() {
        String inputValue;
        do {
            inputValue = Inputter.inputRequired(Constants.LABEL_CANDIDATE_TYPE);
            if (!Validator.isValidCandidateType(inputValue)) {
                System.out.println(Messages.ERR_INVALID_CANDIDATE_TYPE);
            }
        } while (!Validator.isValidCandidateType(inputValue));
        return Integer.parseInt(inputValue);
    }

    /**
     * Hiển thị một nhóm ứng viên theo loại.
     *
     * @param header tiêu đề nhóm
     * @param candidateList danh sách ứng viên trong nhóm
     */
    private void printGroup(String header, ArrayList<Candidate> candidateList) {
        Candidate candidate;
        System.out.println(header);
        if (candidateList.isEmpty()) {
            System.out.println(Messages.MSG_EMPTY_GROUP);
            return;
        }
        for (Candidate item : candidateList) {
            candidate = item;
            System.out.println(candidate.getFullName());
        }
    }
}
