package view;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import model.Fruit;
import model.OrderItem;
import service.FruitService;
import utils.Inputter;

/**
 * Console UI for fruit shop management.
 */
public class FruitView {

    public void displayMainMenu() {
        System.out.println("\nFRUIT SHOP SYSTEM");
        System.out.println("1. Create Fruit");
        System.out.println("2. View orders");
        System.out.println("3. Shopping (for buyer)");
        System.out.println("4. Exit");
        System.out.println("(Please choose 1 to create product, 2 to view order, 3 for shopping, 4 to Exit program).");
    }

    public int getMainMenuChoice() {
        while (true) {
            try {
                String input = Inputter.inputRequired("Your choice: ");
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= 4) {
                    return choice;
                }
                System.out.println("Please enter a number from 1 to 4!");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }

    public void displayAllFruits(List<Fruit> fruits) {
        if (fruits.isEmpty()) {
            System.out.println("\nNo fruits created yet.");
            return;
        }
        System.out.println("\nList of Fruit:");
        System.out.printf("%-6s %-20s %-15s %-10s %-10s%n",
                "ID", "Fruit Name", "Origin", "Price", "Quantity");
        for (Fruit fruit : fruits) {
            System.out.printf("%-6s %-20s %-15s %-10s %-10d%n",
                    fruit.getFruitId(),
                    fruit.getFruitName(),
                    fruit.getOrigin(),
                    formatPrice(fruit.getPrice()),
                    fruit.getQuantity());
        }
    }

    public void displayFruitListForShopping(List<Fruit> fruits) {
        System.out.println("\nList of Fruit:");
        System.out.printf("| %-10s | %-18s | %-10s | %-10s |%n",
                "++ Item ++", "++ Fruit Name ++", "++ Origin ++", "++ Price ++");
        for (int i = 0; i < fruits.size(); i++) {
            Fruit fruit = fruits.get(i);
            if (fruit.getQuantity() <= 0) {
                continue;
            }
            System.out.printf("| %-10d | %-18s | %-10s | %-10s |%n",
                    i + 1,
                    fruit.getFruitName(),
                    fruit.getOrigin(),
                    formatPrice(fruit.getPrice()));
        }
    }

    public int getShoppingItemChoice(int maxItem) {
        while (true) {
            String input = Inputter.inputRequired("Select item: ");
            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= maxItem) {
                    return choice;
                }
                System.out.println("Invalid item! Please select from 1 to " + maxItem + ".");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }

    public void displaySelectedFruit(Fruit fruit) {
        System.out.println("You selected: " + fruit.getFruitName());
    }

    public void displayOrderItems(List<OrderItem> items, double total) {
        System.out.printf("%-20s | %-10s | %-10s | %-10s%n",
                "Product", "Quantity", "Price", "Amount");
        for (OrderItem item : items) {
            System.out.printf("%-20s %-10d %-10s %-10s%n",
                    item.getProductName(),
                    item.getQuantity(),
                    formatPrice(item.getPrice()),
                    formatPrice(item.getAmount()));
        }
        System.out.println("Total: " + formatPrice(total));
    }

    public void displayAllOrders(FruitService service) {
        if (!service.hasOrders()) {
            System.out.println("\nNo orders yet.");
            return;
        }

        Hashtable<String, ArrayList<OrderItem>> orders = service.getAllOrders();
        Enumeration<String> customers = service.getCustomerNames();

        while (customers.hasMoreElements()) {
            String customer = customers.nextElement();
            ArrayList<OrderItem> items = orders.get(customer);
            double total = service.calculateTotal(items);

            System.out.println("\nCustomer: " + customer);
            System.out.printf("%-20s | %-10s | %-10s | %-10s%n",
                    "Product", "Quantity", "Price", "Amount");

            for (int i = 0; i < items.size(); i++) {
                OrderItem item = items.get(i);
                System.out.printf("%d. %-17s %-10d %-10s %-10s%n",
                        i + 1,
                        item.getProductName(),
                        item.getQuantity(),
                        formatPrice(item.getPrice()),
                        formatPrice(item.getAmount()));
            }
            System.out.println("Total: " + formatPrice(total));
        }
    }

    public static String formatPrice(double price) {
        if (price == (long) price) {
            return (long) price + "$";
        }
        return price + "$";
    }
}
