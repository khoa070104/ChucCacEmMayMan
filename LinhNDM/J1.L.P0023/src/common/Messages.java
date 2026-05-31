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
    public static final String ERR_MENU_RANGE = "Please enter a number from 1 to 4!";
    public static final String ERR_INVALID_YES_NO = "Please enter Y or N.";
    public static final String ERR_INVALID_PRICE
            = "Invalid price! Must be a positive number.";
    public static final String ERR_INVALID_QUANTITY
            = "Invalid quantity! Must be a positive integer.";
    public static final String ERR_NOT_ENOUGH_STOCK = "Not enough stock! Available: ";
    public static final String ERR_DUP_FRUIT_ID
            = "Fruit ID already exists! Please use another ID.";
    public static final String ERR_SAVE_FRUIT = "Error saving fruit: ";
    public static final String ERR_LOAD_FRUIT_FILE = "Could not load fruits file: ";
    public static final String ERR_NO_FRUITS
            = "No fruits available. Please create fruits first.";
    public static final String ERR_OUT_OF_STOCK = "All fruits are out of stock.";
    public static final String ERR_INVALID_SHOPPING_ITEM
            = "Invalid selection or out of stock!";
    public static final String ERR_NO_FRUITS_CREATED = "\nNo fruits created yet.";
    public static final String ERR_NO_ORDERS = "\nNo orders yet.";
    public static final String ERR_INVALID_MENU_CHOICE
            = "Invalid choice! Please select 1 to 4.";

    public static final String MSG_PROGRAM_EXIT
            = "Thank you for using Fruit Shop System!";
    public static final String MSG_MENU_TITLE = "\nFRUIT SHOP SYSTEM";
    public static final String MSG_MENU_GUIDE
            = "(Please choose 1 to create product, 2 to view order,"
            + " 3 for shopping, 4 to Exit program).";
    public static final String MSG_FRUIT_LIST = "\nList of Fruit:";
    public static final String MSG_FRUIT_CREATED = "Fruit created successfully!";
    public static final String MSG_CONTINUE_ADDING = "Do you want to continue (Y/N)? ";
    public static final String MSG_CREATE_FRUIT = "\n--- CREATE FRUIT ---";
    public static final String MSG_ORDER_NOW = "Do you want to order now (Y/N)? ";
    public static final String MSG_ORDER_COMPLETED = "Order completed successfully!";
    public static final String MSG_SELECTED_FRUIT = "You selected: ";
    public static final String MSG_CUSTOMER_PREFIX = "\nCustomer: ";
    public static final String MSG_TOTAL_PREFIX = "Total: ";
    public static final String MSG_SHOPPING_HEADER_ITEM = "++ Item ++";
    public static final String MSG_SHOPPING_HEADER_NAME = "++ Fruit Name ++";
    public static final String MSG_SHOPPING_HEADER_ORIGIN = "++ Origin ++";
    public static final String MSG_SHOPPING_HEADER_PRICE = "++ Price ++";
    public static final String MSG_ORDER_HEADER_PRODUCT = "Product";
    public static final String MSG_ORDER_HEADER_QUANTITY = "Quantity";
    public static final String MSG_ORDER_HEADER_PRICE = "Price";
    public static final String MSG_ORDER_HEADER_AMOUNT = "Amount";
    public static final String MSG_LIST_HEADER_ID = "ID";
    public static final String MSG_LIST_HEADER_NAME = "Fruit Name";
    public static final String MSG_LIST_HEADER_ORIGIN = "Origin";
    public static final String MSG_LIST_HEADER_PRICE = "Price";
    public static final String MSG_LIST_HEADER_QUANTITY = "Quantity";
    public static final String ERR_INVALID_ITEM_RANGE
            = "Invalid item! Please select from 1 to ";
}
