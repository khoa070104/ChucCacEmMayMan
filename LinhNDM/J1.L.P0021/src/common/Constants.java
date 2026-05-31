package common;

/**
 * Chứa các hằng số dùng chung trong chương trình.
 *
 * @author LinhNDM
 */
public final class Constants {

    private Constants() {
    }

    public static final int MIN_STUDENTS = 10;
    public static final int MIN_MENU_CHOICE = 1;
    public static final int MAX_MENU_CHOICE = 5;
    public static final int MIN_COURSE_INDEX = 1;

    public static final String YES_NO_VALIDATE = "^(?i)[YN]$";
    public static final String UPDATE_DELETE_VALIDATE = "^(?i)[UD]$";
    public static final String REPORT_KEY_SEPARATOR = "|";
    public static final String TABLE_SEPARATOR
            = "----------------------------------------------------------";

    public static final String LABEL_ENTER_ID = "Enter ID: ";
    public static final String LABEL_ENTER_NAME = "Enter name: ";
    public static final String LABEL_ENTER_SEMESTER = "Enter semester: ";
    public static final String LABEL_ENTER_COURSE = "Enter course (1-3): ";
    public static final String LABEL_YOUR_CHOICE = "Your choice: ";
    public static final String LABEL_SELECT_COURSE = "Select course:";

    public static final String[] COURSES = {"Java", ".Net", "C/C++"};
}
