package common;

/**
 * Chứa các hằng số dùng chung trong chương trình.
 *
 * @author LinhNDM
 */
public final class Constants {

    private Constants() {
    }

    public static final int MIN_MENU_CHOICE = 1;
    public static final int MAX_MENU_CHOICE = 4;

    public static final String YES_NO_VALIDATE = "^(?i)[YN]$";
    public static final String REGEX_POSITIVE_INTEGER = "\\d+";
    public static final String FILE_FIELD_SEPARATOR = "|";

    public static final String DATA_FILE = "data/fruits.txt";
    public static final String CURRENCY_SUFFIX = "$";

    public static final String LABEL_YOUR_CHOICE = "Your choice: ";
    public static final String LABEL_FRUIT_ID = "Fruit Id: ";
    public static final String LABEL_FRUIT_NAME = "Fruit Name: ";
    public static final String LABEL_PRICE = "Price: ";
    public static final String LABEL_QUANTITY = "Quantity: ";
    public static final String LABEL_ORIGIN = "Origin: ";
    public static final String LABEL_SELECT_ITEM = "Select item: ";
    public static final String LABEL_INPUT_QUANTITY = "Please input quantity: ";
    public static final String LABEL_CUSTOMER_NAME = "Input your name: ";

    public static final String HEADER_FRUIT_LIST
            = "%-6s %-20s %-15s %-10s %-10s%n";
    public static final String ROW_FRUIT_LIST
            = "%-6s %-20s %-15s %-10s %-10d%n";
    public static final String HEADER_SHOPPING_LIST
            = "| %-10s | %-18s | %-10s | %-10s |%n";
    public static final String ROW_SHOPPING_LIST
            = "| %-10d | %-18s | %-10s | %-10s |%n";
    public static final String HEADER_ORDER_ITEM
            = "%-20s | %-10s | %-10s | %-10s%n";
    public static final String ROW_ORDER_ITEM
            = "%-20s %-10d %-10s %-10s%n";
    public static final String ROW_ORDER_ITEM_INDEXED
            = "%d. %-17s %-10d %-10s %-10s%n";
}
