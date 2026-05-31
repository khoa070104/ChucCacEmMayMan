package view;

import common.Constants;
import common.Messages;
import java.util.ArrayList;
import java.util.List;
import model.Fruit;
import model.OrderItem;

/**
 * Hiển thị giao diện và thu thập dữ liệu nhập từ người dùng.
 *
 * @author LinhNDM
 */
public class FruitView {

    /**
     * Hiển thị menu chính của chương trình.
     */
    public void displayMainMenu() {
        System.out.println(Messages.MSG_MENU_TITLE);
        System.out.println("1. Create Fruit");
        System.out.println("2. View orders");
        System.out.println("3. Shopping (for buyer)");
        System.out.println("4. Exit");
        System.out.println(Messages.MSG_MENU_GUIDE);
    }

    /**
     * Nhập lựa chọn menu và validate phạm vi hợp lệ.
     *
     * @return số lựa chọn từ 1 đến 4
     */
    public int getMainMenuChoice() {
        String inputValue;
        int choice;
        while (true) {
            try {
                inputValue = Inputter.inputRequired(Constants.LABEL_YOUR_CHOICE);
                choice = Integer.parseInt(inputValue);
                if (choice >= Constants.MIN_MENU_CHOICE
                        && choice <= Constants.MAX_MENU_CHOICE) {
                    return choice;
                }
                System.out.println(Messages.ERR_MENU_RANGE);
            } catch (NumberFormatException exception) {
                System.out.println(Messages.ERR_INVALID_NUMBER);
            }
        }
    }

    /**
     * Nhập thông tin trái cây mới.
     *
     * @return đối tượng trái cây đã nhập
     */
    public Fruit inputFruit() {
        String fruitId;
        String fruitName;
        double price;
        int quantity;
        String origin;
        System.out.println(Messages.MSG_CREATE_FRUIT);
        fruitId = Inputter.inputRequired(Constants.LABEL_FRUIT_ID);
        fruitName = Inputter.inputRequired(Constants.LABEL_FRUIT_NAME);
        price = Inputter.inputPositivePrice(Constants.LABEL_PRICE);
        quantity = Inputter.inputPositiveQuantity(Constants.LABEL_QUANTITY);
        origin = Inputter.inputRequired(Constants.LABEL_ORIGIN);
        return new Fruit(fruitId, fruitName, price, quantity, origin);
    }

    /**
     * Nhập lựa chọn tiếp tục thêm trái cây.
     *
     * @return Y hoặc N
     */
    public String inputContinueChoice() {
        return Inputter.inputYesNo(Messages.MSG_CONTINUE_ADDING);
    }

    /**
     * Nhập lựa chọn đặt hàng ngay.
     *
     * @return Y hoặc N
     */
    public String inputOrderNowChoice() {
        return Inputter.inputYesNo(Messages.MSG_ORDER_NOW);
    }

    /**
     * Nhập tên khách hàng.
     *
     * @return tên khách hàng
     */
    public String inputCustomerName() {
        return Inputter.inputRequired(Constants.LABEL_CUSTOMER_NAME);
    }

    /**
     * Hiển thị một thông báo ra màn hình.
     *
     * @param message nội dung thông báo
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }

    /**
     * Hiển thị danh sách trái cây.
     *
     * @param fruitList danh sách trái cây
     */
    public void displayAllFruits(List<Fruit> fruitList) {
        if (fruitList.isEmpty()) {
            System.out.println(Messages.ERR_NO_FRUITS_CREATED);
            return;
        }
        System.out.println(Messages.MSG_FRUIT_LIST);
        System.out.printf(Constants.HEADER_FRUIT_LIST,
                Messages.MSG_LIST_HEADER_ID,
                Messages.MSG_LIST_HEADER_NAME,
                Messages.MSG_LIST_HEADER_ORIGIN,
                Messages.MSG_LIST_HEADER_PRICE,
                Messages.MSG_LIST_HEADER_QUANTITY);
        for (Fruit fruit : fruitList) {
            System.out.printf(Constants.ROW_FRUIT_LIST,
                    fruit.getFruitId(),
                    fruit.getFruitName(),
                    fruit.getOrigin(),
                    formatPrice(fruit.getPrice()),
                    fruit.getQuantity());
        }
    }

    /**
     * Hiển thị danh sách trái cây cho chức năng mua hàng.
     *
     * @param fruitList danh sách trái cây
     */
    public void displayFruitListForShopping(List<Fruit> fruitList) {
        int index;
        Fruit fruit;
        System.out.println(Messages.MSG_FRUIT_LIST);
        System.out.printf(Constants.HEADER_SHOPPING_LIST,
                Messages.MSG_SHOPPING_HEADER_ITEM,
                Messages.MSG_SHOPPING_HEADER_NAME,
                Messages.MSG_SHOPPING_HEADER_ORIGIN,
                Messages.MSG_SHOPPING_HEADER_PRICE);
        for (index = 0; index < fruitList.size(); index++) {
            fruit = fruitList.get(index);
            if (fruit.getQuantity() <= 0) {
                continue;
            }
            System.out.printf(Constants.ROW_SHOPPING_LIST,
                    index + 1,
                    fruit.getFruitName(),
                    fruit.getOrigin(),
                    formatPrice(fruit.getPrice()));
        }
    }

    /**
     * Nhập số thứ tự sản phẩm khi mua hàng.
     *
     * @param maxItem số sản phẩm tối đa
     * @return số thứ tự hợp lệ
     */
    public int getShoppingItemChoice(int maxItem) {
        String inputValue;
        int choice;
        while (true) {
            inputValue = Inputter.inputRequired(Constants.LABEL_SELECT_ITEM);
            try {
                choice = Integer.parseInt(inputValue);
                if (choice >= 1 && choice <= maxItem) {
                    return choice;
                }
                System.out.println(Messages.ERR_INVALID_ITEM_RANGE + maxItem + ".");
            } catch (NumberFormatException exception) {
                System.out.println(Messages.ERR_INVALID_NUMBER);
            }
        }
    }

    /**
     * Nhập số lượng mua không vượt quá tồn kho.
     *
     * @param maxQuantity số lượng tồn kho
     * @return số lượng hợp lệ
     */
    public int inputShoppingQuantity(int maxQuantity) {
        return Inputter.inputPositiveQuantity(
                Constants.LABEL_INPUT_QUANTITY, maxQuantity);
    }

    /**
     * Hiển thị trái cây được chọn.
     *
     * @param fruit trái cây được chọn
     */
    public void displaySelectedFruit(Fruit fruit) {
        System.out.println(Messages.MSG_SELECTED_FRUIT + fruit.getFruitName());
    }

    /**
     * Hiển thị chi tiết đơn hàng và tổng tiền.
     *
     * @param itemList danh sách sản phẩm
     * @param total tổng tiền
     */
    public void displayOrderItems(List<OrderItem> itemList, double total) {
        System.out.printf(Constants.HEADER_ORDER_ITEM,
                Messages.MSG_ORDER_HEADER_PRODUCT,
                Messages.MSG_ORDER_HEADER_QUANTITY,
                Messages.MSG_ORDER_HEADER_PRICE,
                Messages.MSG_ORDER_HEADER_AMOUNT);
        for (OrderItem item : itemList) {
            System.out.printf(Constants.ROW_ORDER_ITEM,
                    item.getProductName(),
                    item.getQuantity(),
                    formatPrice(item.getPrice()),
                    formatPrice(item.getAmount()));
        }
        System.out.println(Messages.MSG_TOTAL_PREFIX + formatPrice(total));
    }

    /**
     * Hiển thị đơn hàng của một khách hàng.
     *
     * @param customerName tên khách hàng
     * @param itemList danh sách sản phẩm
     * @param total tổng tiền
     */
    public void displayCustomerOrder(String customerName,
            ArrayList<OrderItem> itemList, double total) {
        int index;
        OrderItem item;
        System.out.println(Messages.MSG_CUSTOMER_PREFIX + customerName);
        System.out.printf(Constants.HEADER_ORDER_ITEM,
                Messages.MSG_ORDER_HEADER_PRODUCT,
                Messages.MSG_ORDER_HEADER_QUANTITY,
                Messages.MSG_ORDER_HEADER_PRICE,
                Messages.MSG_ORDER_HEADER_AMOUNT);
        for (index = 0; index < itemList.size(); index++) {
            item = itemList.get(index);
            System.out.printf(Constants.ROW_ORDER_ITEM_INDEXED,
                    index + 1,
                    item.getProductName(),
                    item.getQuantity(),
                    formatPrice(item.getPrice()),
                    formatPrice(item.getAmount()));
        }
        System.out.println(Messages.MSG_TOTAL_PREFIX + formatPrice(total));
    }

    /**
     * Định dạng giá tiền kèm đơn vị.
     *
     * @param price giá trị cần định dạng
     * @return chuỗi giá đã định dạng
     */
    public static String formatPrice(double price) {
        StringBuilder priceBuilder;
        priceBuilder = new StringBuilder();
        if (price == (long) price) {
            priceBuilder.append((long) price);
        } else {
            priceBuilder.append(price);
        }
        priceBuilder.append(Constants.CURRENCY_SUFFIX);
        return priceBuilder.toString();
    }
}
