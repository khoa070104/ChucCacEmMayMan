package constants;

public final class Message {

    private Message() {
    }

    // ===== MENU =====
    public static final String MENU
            = "\n========= Task Program =========\n"
            + "1. Add Task\n"
            + "2. Delete Task\n"
            + "3. Display Task\n"
            + "4. Exit\n";

    // ===== INPUT =====
    public static final String INPUT_CHOICE = "Enter your choice: ";
    public static final String INPUT_REQUIREMENT = "Requirement Name: ";
    public static final String INPUT_TASK_TYPE = "Task Type: ";
    public static final String INPUT_DATE = "Date (dd-MM-yyyy): ";
    public static final String INPUT_FROM = "From: ";
    public static final String INPUT_TO = "To: ";
    public static final String INPUT_ASSIGNEE = "Assignee: ";
    public static final String INPUT_REVIEWER = "Reviewer: ";
    public static final String INPUT_ID = "Enter ID: ";

    // ===== COMMON =====
    public static final String EMPTY_INPUT = "Input cannot be empty";

    // ===== MENU =====
    public static final String INVALID_CHOICE_NUMBER = "Choice must be a number";
    public static final String INVALID_CHOICE_RANGE = "Choice must be between 1 and 4";

    // ===== ID =====
    public static final String INVALID_ID_NUMBER = "ID must be a number";
    public static final String INVALID_ID_POSITIVE = "ID must be greater than 0";
    public static final String TASK_NOT_EXIST = "Task does not exist";

    // ===== TASK TYPE =====
    public static final String INVALID_TASK_TYPE_NUMBER = "Task type must be a number";
    public static final String INVALID_TASK_TYPE_RANGE = "Task type must be between 1 and 4";

    // ===== DATE =====
    public static final String INVALID_DATE = "Date must be in format dd-MM-yyyy";

    // ===== TIME =====
    public static final String INVALID_TIME_NUMBER = "Time must be a number";
    public static final String INVALID_TIME_FORMAT
            = "Time must be in .0 or .5 format";
    public static final String INVALID_TIME_RANGE
            = "Working time must be between 8.0 and 17.5";
    public static final String INVALID_TIME_ORDER
            = "End time must be greater than start time";
}
