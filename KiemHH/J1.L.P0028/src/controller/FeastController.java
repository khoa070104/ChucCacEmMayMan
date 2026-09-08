package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import model.Customer;
import model.FeastMenu;
import model.FeastOrder;
import service.CustomerService;
import service.MenuService;
import service.OrderService;
import util.AppLogger;
import util.InputReader;
import util.Validator;
import view.ConsoleView;

public class FeastController {
    private final InputReader input = new InputReader();
    private final ConsoleView view = new ConsoleView();
    private final CustomerService customerService = new CustomerService();
    private final MenuService menuService = new MenuService("feastMenu.csv");
    private final OrderService orderService = new OrderService();
    private boolean changed;

    public void run() {
        boolean running = true;
        while (running) {
            view.showMainMenu();
            int choice = input.readPositiveInt("Select an option: ");
            switch (choice) {
                case 1 -> registerCustomers();
                case 2 -> updateCustomers();
                case 3 -> searchCustomers();
                case 4 -> view.showMenus(menuService.getSortedMenus());
                case 5 -> placeOrders();
                case 6 -> updateOrders();
                case 7 -> saveData();
                case 8 -> displayLists();
                case 9 -> running = !confirmQuit();
                default -> System.out.println("Please select an option from 1 to 9.");
            }
        }
        System.out.println("Goodbye!");
    }

    private void registerCustomers() {
        do {
            String code = input.readRequired("Customer code: ", value -> Validator.isCustomerCode(value)
                    && customerService.findByCode(value) == null,
                    "Code must be unique and match C/G/K followed by four digits.").toUpperCase();
            String name = input.readRequired("Customer name: ", Validator::isName,
                    "Name must contain 2 to 25 characters.");
            String phone = input.readRequired("Phone number: ", Validator::isPhoneNumber,
                    "Enter a valid 10-digit Vietnamese mobile number.");
            String email = input.readRequired("Email: ", Validator::isEmail,
                    "Enter a valid email address.");
            customerService.add(new Customer(code, name, phone, email));
            changed = true;
            System.out.println("Customer registered successfully.");
        } while (input.readYesNo("Register another customer? (Y/N): "));
    }

    private void updateCustomers() {
        do {
            String code = input.readRequired("Customer code: ", value -> !value.isBlank(),
                    "Customer code cannot be blank.");
            Customer customer = customerService.findByCode(code);
            if (customer == null) {
                System.out.println("This customer does not exist.");
            } else {
                String name = input.readOptional("New name (blank to keep): ", Validator::isName,
                        "Name must contain 2 to 25 characters.");
                String phone = input.readOptional("New phone (blank to keep): ", Validator::isPhoneNumber,
                        "Enter a valid 10-digit Vietnamese mobile number.");
                String email = input.readOptional("New email (blank to keep): ", Validator::isEmail,
                        "Enter a valid email address.");
                if (!name.isEmpty()) customer.setName(name);
                if (!phone.isEmpty()) customer.setPhoneNumber(phone);
                if (!email.isEmpty()) customer.setEmail(email);
                changed = true;
                System.out.println("Customer information updated successfully.");
            }
        } while (input.readYesNo("Update another customer? (Y/N): "));
    }

    private void searchCustomers() {
        String keyword = input.readRequired("Name or partial name: ", value -> !value.isBlank(),
                "Search text cannot be blank.");
        List<Customer> matches = customerService.searchByName(keyword);
        if (matches.isEmpty()) {
            System.out.println("No one matches the search criteria!");
        } else {
            System.out.println("Matching Customers: " + keyword);
            view.showCustomers(matches);
        }
    }

    private void placeOrders() {
        if (menuService.isEmpty()) {
            System.out.println("Cannot read data from feastMenu.csv. Please check it.");
            return;
        }
        do {
            String customerCode = input.readRequired("Customer code: ",
                    value -> customerService.findByCode(value) != null,
                    "Customer code has not been registered.").toUpperCase();
            String menuCode = input.readRequired("Set menu code: ",
                    value -> menuService.findByCode(value) != null,
                    "Set menu code does not exist.").toUpperCase();
            int tables = input.readPositiveInt("Number of tables: ");
            LocalDate eventDate = input.readFutureDate("Event date (dd/MM/yyyy): ", false);
            FeastMenu menu = menuService.findByCode(menuCode);
            FeastOrder order = orderService.create(customerCode, menuCode, tables,
                    eventDate, menu.getPrice());
            if (order == null) {
                System.out.println("Duplicate data!");
            } else {
                changed = true;
                view.showOrder(order, customerService.findByCode(customerCode), menu);
            }
        } while (input.readYesNo("Place another order? (Y/N): "));
    }

    private void updateOrders() {
        if (menuService.isEmpty()) {
            System.out.println("Cannot read data from feastMenu.csv. Please check it.");
            return;
        }
        do {
            int orderId = input.readPositiveInt("Order ID: ");
            FeastOrder order = orderService.findById(orderId);
            if (order == null) {
                System.out.println("This Order does not exist.");
            } else if (order.getEventDate().isBefore(LocalDate.now())) {
                System.out.println("An order whose event date has passed cannot be updated.");
            } else {
                String menuCode = input.readOptional("New set menu code (blank to keep): ",
                        value -> menuService.findByCode(value) != null, "Set menu code does not exist.");
                Integer tables = input.readOptionalPositiveInt("New number of tables (blank to keep): ");
                LocalDate eventDate = input.readFutureDate("New event date dd/MM/yyyy (blank to keep): ", true);
                String finalMenuCode = menuCode.isEmpty() ? order.getMenuCode() : menuCode.toUpperCase();
                LocalDate finalEventDate = eventDate == null ? order.getEventDate() : eventDate;
                if (orderService.isDuplicate(orderId, order.getCustomerCode(), finalMenuCode, finalEventDate)) {
                    System.out.println("Duplicate data!");
                } else {
                    if (!menuCode.isEmpty()) {
                        FeastMenu menu = menuService.findByCode(finalMenuCode);
                        order.setMenuCode(finalMenuCode);
                        order.setMenuPrice(menu.getPrice());
                    }
                    if (tables != null) order.setNumberOfTables(tables);
                    if (eventDate != null) order.setEventDate(eventDate);
                    changed = true;
                    System.out.println("Order information updated successfully.");
                }
            }
        } while (input.readYesNo("Update another order? (Y/N): "));
    }

    private void displayLists() {
        System.out.println("1. Display customers");
        System.out.println("2. Display orders");
        int choice = input.readPositiveInt("Select a list: ");
        if (choice == 1) {
            view.showCustomers(customerService.getSortedCustomers());
        } else if (choice == 2) {
            view.showOrders(orderService.getSortedOrders());
        } else {
            System.out.println("Please select 1 or 2.");
        }
    }

    private boolean saveData() {
        try {
            customerService.save();
            orderService.save();
            changed = false;
            System.out.println("Customer data has been successfully saved to customers.dat.");
            System.out.println("Order data has been successfully saved to feast_order_service.dat.");
            return true;
        } catch (IOException exception) {
            AppLogger.log("Cannot save application data", exception);
            System.out.println("Unable to save data. See application.log for details.");
            return false;
        }
    }

    private boolean confirmQuit() {
        if (!changed) return true;
        if (input.readYesNo("There are unsaved changes. Save before quitting? (Y/N): ")) {
            return saveData();
        }
        return input.readYesNo("Discard unsaved changes and quit? (Y/N): ");
    }
}
