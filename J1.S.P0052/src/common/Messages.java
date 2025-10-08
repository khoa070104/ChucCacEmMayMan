package common;

public final class Messages {
    private Messages() {}

    // Error messages
    public static final String ERR_TOTAL_AREA_INVALID = "Total area must be greater than 0!";
    public static final String ERR_COUNTRY_NOT_FOUND = "Country not found!";
    public static final String ERR_EMPTY_COUNTRY_CODE = "Country code cannot be empty!";
    public static final String ERR_EMPTY_COUNTRY_NAME = "Country name cannot be empty!";
    public static final String ERR_EMPTY_TERRAIN = "Terrain cannot be empty!";
    public static final String ERR_INVALID_CHOICE = "Invalid choice! Please try again.";
    public static final String ERR_NO_COUNTRIES_ADDED = "No countries have been added yet!";

    // Success messages
    public static final String MSG_COUNTRY_ADDED_SUCCESS = "Country added successfully!";
    public static final String MSG_PROGRAM_EXIT = "Thank you for using the program!";
    public static final String MSG_SEARCH_RESULTS = "Search results:";
    public static final String MSG_SORTED_RESULTS = "Countries sorted by name (ascending):";
}
