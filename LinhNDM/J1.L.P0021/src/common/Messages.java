package common;

/**
 * Chứa các thông báo hiển thị cho người dùng.
 *
 * @author LinhNDM
 */
public final class Messages {

    private Messages() {
    }

    public static final String ERR_DUP_STUDENT_ID = "Student ID already exists!";
    public static final String ERR_INVALID_INPUT = "Invalid input! Please try again.";
    public static final String ERR_NO_STUDENTS_FOUND = "Not found!";
    public static final String ERR_NO_STUDENTS_ADDED = "No students have been added yet!";
    public static final String ERR_STUDENT_NOT_FOUND = "Student not found!";
    public static final String ERR_INPUT_EMPTY = "Input cannot be empty!";
    public static final String ERR_INVALID_NUMBER
            = "Invalid input! Please enter a valid number.";
    public static final String ERR_MENU_RANGE = "Please enter a number from 1 to 5!";

    public static final String MSG_STUDENT_ADDED_SUCCESS = "Added successfully.";
    public static final String MSG_CONTINUE_ADDING = "Do you want to continue (Y/N)? ";
    public static final String MSG_SEARCH_RESULTS = "Search results:";
    public static final String MSG_PROGRAM_EXIT = "Thank you for using Student Management!";
    public static final String MSG_UPDATE_DELETE
            = "Do you want to update (U) or delete (D) student? ";
    public static final String MSG_UPDATED_SUCCESS = "Updated successfully.";
    public static final String MSG_DELETED_SUCCESS = "Deleted successfully.";
    public static final String MSG_REPORT_HEADER = "Report:";
    public static final String MSG_WELCOME = "\nWELCOME TO STUDENT MANAGEMENT";
    public static final String MSG_MENU_GUIDE
            = "(Please choose 1 to Create, 2 to Find and Sort, 3 to Update/Delete, "
            + "4 to Report and 5 to Exit program).";
    public static final String MSG_FOUND_STUDENT = "Found: %s | %s | %s | %s";
    public static final String MSG_SEARCH_TABLE_HEADER
            = "%-20s | %-10s | %-15s%n";
    public static final String MSG_SEARCH_TABLE_ROW
            = "%-20s | %-10s | %-15s%n";
    public static final String MSG_REPORT_ROW = "%s | %s | %d%n";
}
