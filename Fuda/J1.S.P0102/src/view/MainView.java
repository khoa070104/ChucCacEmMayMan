package view;

import common.Constants;
import common.Messages;
import controller.Inputter;
import controller.ShoeList;
import model.Shoe;

import java.util.List;

public class MainView {
    private final ShoeList shoeList;

    public MainView() {
        shoeList = new ShoeList();
    }

    public void run() {
        while (true) {
            Menu.display();
            int choice = Inputter.inputInt("> Choose: ", 1);
            switch (choice) {
                case 1:
                    displayShoes(shoeList.getAll());
                    break;
                case 2:
                    addShoe();
                    break;
                case 3:
                    searchShoe();
                    break;
                case 4:
                    updateShoe();
                    break;
                case 5:
                    removeShoe();
                    break;
                case 6:
                    filterByPrice();
                    break;
                case 7:
                    filterByQuantity();
                    break;
                case 8:
                    saveData();
                    break;
                case 9:
                    if (quit()) {
                        return;
                    }
                    break;
                default:
                    System.out.println(Messages.ERR_INVALID_CHOICE);
            }
        }
    }

    private String inputShoeId(String label) {
        while (true) {
            String id = Inputter.inputRequired(label).toUpperCase();
            if (id.matches(Constants.SHOE_ID_REGEX)) {
                return id;
            }
            System.out.println(Messages.ERR_INVALID_ID);
        }
    }

    private void addShoe() {
        System.out.println("\n--- Add New Shoe ---");
        String id;
        while (true) {
            id = inputShoeId("Enter shoe ID: ");
            if (shoeList.findById(id) == null) {
                break;
            }
            System.out.println(Messages.ERR_DUPLICATE_ID);
        }
        String name = Inputter.inputRequired("Enter shoe name: ");
        String brand = Inputter.inputRequired("Enter brand: ");
        double price = Inputter.inputDouble("Enter price: ", 0);
        int quantity = Inputter.inputInt("Enter quantity: ", 0);
        shoeList.add(new Shoe(id, name, brand, price, quantity));
        System.out.println(Messages.MSG_ADDED);
    }

    private void searchShoe() {
        String id = inputShoeId("Enter shoe ID to search: ");
        Shoe shoe = shoeList.findById(id);
        if (shoe == null) {
            System.out.println(Messages.ERR_NOT_FOUND);
            return;
        }
        displayShoes(java.util.Collections.singletonList(shoe));
    }

    private void updateShoe() {
        String id = inputShoeId("Enter shoe ID to update: ");
        Shoe shoe = shoeList.findById(id);
        if (shoe == null) {
            System.out.println(Messages.ERR_NOT_FOUND);
            return;
        }
        System.out.println("Leave a field empty to keep its current value.");
        String name = Inputter.input("Enter new shoe name: ");
        String brand = Inputter.input("Enter new brand: ");
        Double price = Inputter.inputOptionalDouble("Enter new price: ", 0);
        Integer quantity = Inputter.inputOptionalInt("Enter new quantity: ", 0);

        boolean updated = false;
        if (!name.isEmpty()) {
            shoe.setName(name);
            updated = true;
        }
        if (!brand.isEmpty()) {
            shoe.setBrand(brand);
            updated = true;
        }
        if (price != null) {
            shoe.setPrice(price);
            updated = true;
        }
        if (quantity != null) {
            shoe.setQuantity(quantity);
            updated = true;
        }
        if (updated) {
            shoeList.markChanged();
            System.out.println(Messages.MSG_UPDATED);
        } else {
            System.out.println("No changes were made.");
        }
    }

    private void removeShoe() {
        String id = inputShoeId("Enter shoe ID to remove: ");
        if (!shoeList.remove(id)) {
            System.out.println(Messages.ERR_NOT_FOUND);
            return;
        }
        System.out.println(Messages.MSG_REMOVED);
    }

    private void filterByPrice() {
        double maximumPrice = Inputter.inputDouble("Enter maximum price: ", 0);
        displayShoes(shoeList.findByMaximumPrice(maximumPrice));
    }

    private void filterByQuantity() {
        int minimumQuantity = Inputter.inputInt("Enter minimum quantity: ", 0);
        displayShoes(shoeList.findByMinimumQuantity(minimumQuantity));
    }

    private void saveData() {
        if (shoeList.saveToFile()) {
            System.out.println(Messages.MSG_SAVED);
        }
    }

    private boolean quit() {
        if (shoeList.isChanged()
                && Inputter.inputYesNo("Data has changed. Save before exiting? (Y/N): ")) {
            saveData();
            if (shoeList.isChanged()) {
                return false;
            }
        }
        System.out.println(Messages.MSG_EXIT);
        return true;
    }

    private void displayShoes(List<Shoe> shoes) {
        if (shoes.isEmpty()) {
            System.out.println(Messages.MSG_NO_DATA);
            return;
        }
        System.out.println("+------+------------------------------+--------------------+------------+----------+");
        System.out.printf("| %-4s | %-28s | %-18s | %10s | %8s |%n",
                "ID", "Shoe Name", "Brand", "Price", "Quantity");
        System.out.println("+------+------------------------------+--------------------+------------+----------+");
        for (Shoe shoe : shoes) {
            System.out.printf("| %-4s | %-28s | %-18s | %10.2f | %8d |%n",
                    shoe.getId(), shoe.getName(), shoe.getBrand(),
                    shoe.getPrice(), shoe.getQuantity());
        }
        System.out.println("+------+------------------------------+--------------------+------------+----------+");
    }
}
