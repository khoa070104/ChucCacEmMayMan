package common;

public final class Messages {
    private Messages() {}

    // Error messages
    public static final String ERR_USERNAME_TOO_SHORT = "You must enter at least 5 characters, and no space!";
    public static final String ERR_PASSWORD_TOO_SHORT = "You must enter at least 6 characters, and no space!";
    public static final String ERR_USERNAME_EXISTS = "Username already exists!";
    public static final String ERR_INVALID_CREDENTIALS = "Invalid username or password";
    public static final String ERR_FILE_READ = "Error reading user file!";
    public static final String ERR_FILE_WRITE = "Error writing to user file!";
    public static final String ERR_INVALID_CHOICE = "Invalid choice! Please try again.";

    // Success messages
    public static final String MSG_ACCOUNT_CREATED = "Account created successfully!";
    public static final String MSG_LOGIN_SUCCESS = "Login successful!";
    public static final String MSG_PROGRAM_EXIT = "Thank you for using the program!";
}
