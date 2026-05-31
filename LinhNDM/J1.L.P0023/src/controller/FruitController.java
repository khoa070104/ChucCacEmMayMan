package controller;

import common.Messages;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import model.Fruit;
import model.OrderItem;
import repository.FruitRepository;
import repository.OrderRepository;
import service.FruitService;
import view.FruitView;

/**
 * Điều phối luồng chương trình giữa View và Model.
 *
 * @author LinhNDM
 */
public class FruitController {

    private final FruitService service;
    private final FruitView view;

    /**
     * Khởi tạo controller với service và view.
     */
    public FruitController() {
        FruitRepository fruitRepository;
        OrderRepository orderRepository;
        String loadError;
        fruitRepository = new FruitRepository();
        orderRepository = new OrderRepository();
        this.service = new FruitService(fruitRepository, orderRepository);
        this.view = new FruitView();
        loadError = service.getLoadErrorMessage();
        if (loadError != null) {
            view.displayMessage(Messages.ERR_LOAD_FRUIT_FILE + loadError);
        }
    }

    /**
     * Chạy chương trình với menu điều hướng chính.
     */
    public void run() {
        int choice;
        while (true) {
            view.displayMainMenu();
            choice = view.getMainMenuChoice();
            switch (choice) {
                case 1:
                    handleCreateFruit();
                    break;
                case 2:
                    handleViewOrders();
                    break;
                case 3:
                    handleShopping();
                    break;
                case 4:
                    view.displayMessage(Messages.MSG_PROGRAM_EXIT);
                    return;
                default:
                    view.displayMessage(Messages.ERR_INVALID_MENU_CHOICE);
            }
        }
    }

    /**
     * Xử lý chức năng tạo trái cây mới.
     */
    private void handleCreateFruit() {
        String continueChoice;
        Fruit fruit;
        boolean added;
        try {
            while (true) {
                do {
                    fruit = view.inputFruit();
                    added = service.addFruit(fruit);
                    if (!added) {
                        view.displayMessage(Messages.ERR_DUP_FRUIT_ID);
                    }
                } while (!added);
                view.displayMessage(Messages.MSG_FRUIT_CREATED);
                continueChoice = view.inputContinueChoice();
                if (!continueChoice.equalsIgnoreCase("Y")) {
                    break;
                }
            }
            view.displayAllFruits(service.getAllFruits());
        } catch (IOException exception) {
            view.displayMessage(Messages.ERR_SAVE_FRUIT + exception.getMessage());
        }
    }

    /**
     * Xử lý chức năng xem danh sách đơn hàng.
     */
    private void handleViewOrders() {
        Enumeration<String> customerNames;
        String customerName;
        ArrayList<OrderItem> itemList;
        double total;
        if (!service.hasOrders()) {
            view.displayMessage(Messages.ERR_NO_ORDERS);
            return;
        }
        customerNames = service.getCustomerNames();
        while (customerNames.hasMoreElements()) {
            customerName = customerNames.nextElement();
            itemList = service.getOrderByCustomer(customerName);
            total = service.calculateTotal(itemList);
            view.displayCustomerOrder(customerName, itemList, total);
        }
    }

    /**
     * Xử lý chức năng mua hàng.
     */
    private void handleShopping() {
        List<Fruit> fruitList;
        Fruit selectedFruit;
        String orderNowChoice;
        int choice;
        int quantity;
        if (!service.hasFruits()) {
            view.displayMessage(Messages.ERR_NO_FRUITS);
            return;
        }
        if (service.getAvailableFruits().isEmpty()) {
            view.displayMessage(Messages.ERR_OUT_OF_STOCK);
            return;
        }
        fruitList = service.getAllFruits();
        while (true) {
            view.displayFruitListForShopping(fruitList);
            if (service.getAvailableFruits().isEmpty()) {
                view.displayMessage(Messages.ERR_OUT_OF_STOCK);
                break;
            }
            choice = view.getShoppingItemChoice(fruitList.size());
            selectedFruit = service.getFruitByListIndex(choice);
            if (selectedFruit == null || selectedFruit.getQuantity() <= 0) {
                view.displayMessage(Messages.ERR_INVALID_SHOPPING_ITEM);
                continue;
            }
            view.displaySelectedFruit(selectedFruit);
            quantity = view.inputShoppingQuantity(selectedFruit.getQuantity());
            service.addToCart(selectedFruit, quantity);
            orderNowChoice = view.inputOrderNowChoice();
            if (orderNowChoice.equalsIgnoreCase("Y")) {
                finishOrder();
                break;
            }
        }
    }

    /**
     * Hoàn tất đơn hàng và lưu thông tin khách.
     */
    private void finishOrder() {
        ArrayList<OrderItem> cartList;
        double total;
        String customerName;
        if (service.isCartEmpty()) {
            return;
        }
        cartList = service.getCartList();
        total = service.calculateTotal(cartList);
        view.displayOrderItems(cartList, total);
        customerName = view.inputCustomerName();
        service.saveOrder(customerName);
        view.displayMessage(Messages.MSG_ORDER_COMPLETED);
    }
}
