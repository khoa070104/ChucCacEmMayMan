package common;

/**
 * Chứa các thông báo hiển thị cho người dùng.
 *
 * @author LinhNDM
 */
public final class Messages {

    private Messages() {
    }

    public static final String ERR_INPUT_EMPTY = "Input cannot be empty!";
    public static final String ERR_INVALID_INPUT = "Invalid input! Please try again.";
    public static final String ERR_INVALID_NUMBER
            = "Invalid input! Please enter a number.";
    public static final String ERR_MENU_RANGE = "Please enter a number from 1 to 5!";
    public static final String ERR_INVALID_BIRTH_YEAR
            = "Invalid year! Must be 4 digits from 1900 to current year.";
    public static final String ERR_INVALID_PHONE
            = "Invalid phone! Must be numeric with at least 10 digits.";
    public static final String ERR_INVALID_EMAIL
            = "Invalid email! Format: account@domain (e.g. annguyen@fpt.edu.vn)";
    public static final String ERR_INVALID_EXP_IN_YEAR
            = "Invalid year of experience! Must be a number from 0 to 100.";
    public static final String ERR_INVALID_GRADUATION_RANK
            = "Invalid rank! Must be one of: Excellence, Good, Fair, Poor.";
    public static final String ERR_INVALID_CANDIDATE_TYPE
            = "Invalid type! Must be 0 (Experience), 1 (Fresher), or 2 (Intern).";
    public static final String ERR_DUP_CANDIDATE_ID = "Candidate ID already exists!";
    public static final String ERR_NO_CANDIDATES
            = "No candidates available. Please create candidates first.";
    public static final String ERR_NO_CANDIDATES_CREATED = "\nNo candidates created yet.";
    public static final String ERR_NO_MATCHING_CANDIDATES = "No matching candidates.";
    public static final String ERR_SAVE_CANDIDATE = "Error saving candidate: ";
    public static final String ERR_LOAD_CANDIDATE_FILE = "Could not load candidates file: ";

    public static final String MSG_PROGRAM_EXIT
            = "Thank you for using Candidate Management System!";
    public static final String MSG_MENU_TITLE = "\nCANDIDATE MANAGEMENT SYSTEM";
    public static final String MSG_MENU_GUIDE
            = "(Please choose 1 to Create Experience Candidate, 2 to Create Fresher Candidate,";
    public static final String MSG_MENU_GUIDE_LINE2
            = " 3 to Internship Candidate, 4 to Searching and 5 to Exit program).";
    public static final String MSG_CANDIDATE_LIST = "\nList of candidate:";
    public static final String MSG_EMPTY_GROUP = "(empty)";
    public static final String MSG_CANDIDATE_CREATED = "Candidate created successfully!";
    public static final String MSG_CONTINUE_ADDING = "Do you want to continue (Y/N)? ";
    public static final String MSG_SEARCH_RESULTS = "\nThe candidates found:";
    public static final String MSG_CREATE_EXPERIENCE = "\n--- CREATE EXPERIENCE CANDIDATE ---";
    public static final String MSG_CREATE_FRESHER = "\n--- CREATE FRESHER CANDIDATE ---";
    public static final String MSG_CREATE_INTERN = "\n--- CREATE INTERN CANDIDATE ---";
}
