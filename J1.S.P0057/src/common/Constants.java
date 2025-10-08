package common;

public final class Constants {
    private Constants() {}

    public static final String USER_FILE = "user.dat";
    public static final int MIN_USERNAME_LENGTH = 5;
    public static final int MIN_PASSWORD_LENGTH = 6;
    public static final String USERNAME_REGEX = "^[^\\s]{" + MIN_USERNAME_LENGTH + ",}$";
    public static final String PASSWORD_REGEX = "^[^\\s]{" + MIN_PASSWORD_LENGTH + ",}$";
}
