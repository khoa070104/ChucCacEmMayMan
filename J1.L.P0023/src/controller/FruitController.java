package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Fruit;
import model.OrderItem;
import repository.FruitRepository;
import repository.OrderRepository;
import service.FruitService;
import utils.Inputter;
import view.FruitView;

/**
 * Coordinates view and service layers.
 */
public class FruitController {

    private final FruitService service;
    private final FruitView view;

    public FruitController() {
        FruitRepository fruitRepository = new FruitRepository();
        OrderRepository orderRepository = new OrderRepository();
        this.service = new FruitService(fruitRepository, orderRepository);
        this.view = new FruitView();
    }

    public void run() {
        while (true) {
            view.displayMainMenu();
            int choice = view.getMainMenuChoice();
            switch (choice) {
                case 1:
                    handleCreateFruit();
                    break;
                case 2:
                    view.displayAllOrders(service);
                    break;
                case 3:
                    handleShopping();
                    break;
                case 4:
                    System.out.println("Thank you for using Fruit Shop System!");
                    return;
                default:
                    System.out.println("Invalid choice! Please select 1 to 4.");
            }
        }
    }

    private void handleCreateFruit() {
        try {
            service.createFruits();
            view.displayAllFruits(service.getAllFruits());
        } catch (IOException e) {
            System.out.println("Error saving fruit: " + e.getMessage());
        }
    }

    private void handleShopping() {
        if (!service.hasFruits()) {
            System.out.println("No fruits available. Please create fruits first.");
            return;
        }

        if (getAvailableFruits().isEmpty()) {
            System.out.println("All fruits are out of stock.");
            return;
        }

        List<Fruit> fruits = service.getAllFruits();
        while (true) {
            view.displayFruitListForShopping(fruits);
            if (getAvailableFruits().isEmpty()) {
                System.out.println("All fruits are out of stock.");
                break;
            }

            int choice = view.getShoppingItemChoice(fruits.size());
            Fruit selected = service.getFruitByListIndex(choice);
            if (selected == null || selected.getQuantity() <= 0) {
                System.out.println("Invalid selection or out of stock!");
                continue;
            }

            view.displaySelectedFruit(selected);
            int quantity = Inputter.inputPositiveQuantity("Please input quantity: ", selected.getQuantity());
            service.addToCart(selected, quantity);

            String orderNow = Inputter.inputYesNo("Do you want to order now (Y/N)? ");
            if (orderNow.equalsIgnoreCase("Y")) {
                finishOrder();
                break;
            }
        }
    }

    private void finishOrder() {
        if (service.isCartEmpty()) {
            return;
        }
        ArrayList<OrderItem> cart = service.getCart();
        double total = service.calculateTotal(cart);
        view.displayOrderItems(cart, total);

        String customerName = Inputter.inputRequired("Input your name: ");
        service.saveOrder(customerName);
        System.out.println("Order completed successfully!");
    }

    private List<Fruit> getAvailableFruits() {
        List<Fruit> available = new ArrayList<>();
        for (Fruit fruit : service.getAllFruits()) {
            if (fruit.getQuantity() > 0) {
                available.add(fruit);
            }
        }
        return available;
    }

}
