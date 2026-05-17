package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import model.Fruit;
import model.OrderItem;
import repository.FruitRepository;
import repository.OrderRepository;
import utils.Inputter;

/**
 * Business logic for fruit shop management.
 */
public class FruitService {

    private final FruitRepository fruitRepository;
    private final OrderRepository orderRepository;
    private final ArrayList<OrderItem> cart = new ArrayList<>();

    public FruitService(FruitRepository fruitRepository, OrderRepository orderRepository) {
        this.fruitRepository = fruitRepository;
        this.orderRepository = orderRepository;
    }

    public void createFruits() throws IOException {
        do {
            Fruit fruit = inputFruit();
            if (fruitRepository.existsById(fruit.getFruitId())) {
                System.out.println("Fruit ID already exists! Please use another ID.");
                continue;
            }
            fruitRepository.add(fruit);
            System.out.println("Fruit created successfully!");
        } while (Inputter.inputYesNo("Do you want to continue (Y/N)? ").equalsIgnoreCase("Y"));
    }

    private Fruit inputFruit() {
        System.out.println("\n--- CREATE FRUIT ---");
        String id = Inputter.inputRequired("Fruit Id: ");
        String name = Inputter.inputRequired("Fruit Name: ");
        double price = Inputter.inputPositivePrice("Price: ");
        int quantity = Inputter.inputPositiveQuantity("Quantity: ");
        String origin = Inputter.inputRequired("Origin: ");
        return new Fruit(id, name, price, quantity, origin);
    }

    public List<Fruit> getAllFruits() {
        return fruitRepository.findAll();
    }

    public boolean hasFruits() {
        return !fruitRepository.isEmpty();
    }

    public Fruit getFruitByListIndex(int listIndex) {
        return fruitRepository.findByIndex(listIndex - 1);
    }

    public void addToCart(Fruit fruit, int quantity) {
        cart.add(new OrderItem(fruit.getFruitName(), quantity, fruit.getPrice()));
        fruit.setQuantity(fruit.getQuantity() - quantity);
    }

    public ArrayList<OrderItem> getCart() {
        return cart;
    }

    public void clearCart() {
        cart.clear();
    }

    public double calculateTotal(List<OrderItem> items) {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getAmount();
        }
        return total;
    }

    public void saveOrder(String customerName) {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        for (OrderItem item : cart) {
            orderItems.add(new OrderItem(item.getProductName(),
                    item.getQuantity(), item.getPrice()));
        }
        orderRepository.saveOrder(customerName, orderItems);
        clearCart();
    }

    public boolean hasOrders() {
        return !orderRepository.isEmpty();
    }

    public Hashtable<String, ArrayList<OrderItem>> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    public Enumeration<String> getCustomerNames() {
        return orderRepository.customerNames();
    }

    public ArrayList<OrderItem> getOrderByCustomer(String customerName) {
        return orderRepository.getOrderByCustomer(customerName);
    }

    public boolean isCartEmpty() {
        return cart.isEmpty();
    }
}
