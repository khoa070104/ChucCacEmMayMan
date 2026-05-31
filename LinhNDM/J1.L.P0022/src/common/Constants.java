package common;

/**
 * Chứa các hằng số dùng chung trong chương trình.
 *
 * @author LinhNDM
 */
public final class Constants {

    private Constants() {
    }

    public static final int TYPE_EXPERIENCE = 0;
    public static final int TYPE_FRESHER = 1;
    public static final int TYPE_INTERN = 2;

    public static final int MIN_MENU_CHOICE = 1;
    public static final int MAX_MENU_CHOICE = 5;

    public static final int MIN_BIRTH_YEAR = 1900;
    public static final int MIN_EXP_IN_YEAR = 0;
    public static final int MAX_EXP_IN_YEAR = 100;

    public static final String REGEX_FOUR_DIGIT_YEAR = "\\d{4}";
    public static final String REGEX_PHONE = "\\d{10,}";
    public static final String REGEX_EMAIL = "^[\\w.-]+@[\\w.-]+\\.[\\w.-]+$";
    public static final String REGEX_POSITIVE_INTEGER = "\\d+";
    public static final String REGEX_CANDIDATE_TYPE = "[012]";
    public static final String YES_NO_VALIDATE = "^(?i)[YN]$";
    public static final String FILE_FIELD_SEPARATOR = "|";

    public static final String DATA_FILE = "data/candidates.txt";

    public static final String LABEL_YOUR_CHOICE = "Your choice: ";
    public static final String LABEL_CANDIDATE_ID = "Enter Candidate ID: ";
    public static final String LABEL_FIRST_NAME = "Enter First Name: ";
    public static final String LABEL_LAST_NAME = "Enter Last Name: ";
    public static final String LABEL_BIRTH_DATE = "Enter Birth Date (yyyy): ";
    public static final String LABEL_ADDRESS = "Enter Address: ";
    public static final String LABEL_PHONE = "Enter Phone: ";
    public static final String LABEL_EMAIL = "Enter Email: ";
    public static final String LABEL_EXP_IN_YEAR = "Enter Year of Experience: ";
    public static final String LABEL_PRO_SKILL = "Enter Professional Skill: ";
    public static final String LABEL_GRADUATION_DATE = "Enter Graduation Date (yyyy): ";
    public static final String LABEL_GRADUATION_RANK
            = "Enter Rank of Graduation (Excellence/Good/Fair/Poor): ";
    public static final String LABEL_EDUCATION = "Enter Education (University): ";
    public static final String LABEL_MAJORS = "Enter Majors: ";
    public static final String LABEL_SEMESTER = "Enter Semester: ";
    public static final String LABEL_UNIVERSITY = "Enter University Name: ";
    public static final String LABEL_SEARCH_NAME
            = "\nInput Candidate name (First name or Last name): ";
    public static final String LABEL_CANDIDATE_TYPE = "Input type of candidate: ";

    public static final String HEADER_EXPERIENCE
            = "===========EXPERIENCE CANDIDATE============";
    public static final String HEADER_FRESHER
            = "==========FRESHER CANDIDATE==============";
    public static final String HEADER_INTERN
            = "===========INTERN CANDIDATE==============";

    public static final String[] GRADUATION_RANKS
            = {"Excellence", "Good", "Fair", "Poor"};
}
