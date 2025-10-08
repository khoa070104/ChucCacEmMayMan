package common;

public final class Messages {
    private Messages() {}

    // Error messages
    public static final String ERR_DATABASE_NULL = "Database does not exist (null HashMap)";
    public static final String ERR_DOCTOR_NULL = "Data does not exist (null doctor)";
    public static final String ERR_DOCTOR_CODE_DUPLICATE = "Doctor code [%s] is duplicate";
    public static final String ERR_DOCTOR_CODE_NOT_EXIST = "Doctor code doesn't exist";
    public static final String ERR_AVAILABILITY_INVALID = "Availability must be >= 0";
    public static final String ERR_EMPTY_CODE = "Doctor code cannot be empty!";
    public static final String ERR_EMPTY_NAME = "Doctor name cannot be empty!";
    public static final String ERR_EMPTY_SPECIALIZATION = "Specialization cannot be empty!";
    public static final String ERR_INVALID_CHOICE = "Invalid choice! Please try again.";
    public static final String ERR_NO_DOCTORS_FOUND = "No doctors found matching the search criteria!";

    // Success messages
    public static final String MSG_DOCTOR_ADDED = "Doctor added successfully!";
    public static final String MSG_DOCTOR_UPDATED = "Doctor updated successfully!";
    public static final String MSG_DOCTOR_DELETED = "Doctor deleted successfully!";
    public static final String MSG_PROGRAM_EXIT = "Thank you for using the program!";
}
