package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import model.Customer;
import repository.BinaryRepository;
import util.AppLogger;

public class CustomerService {
    private static final String DATA_FILE = "customers.dat";
    private final BinaryRepository<Customer> repository = new BinaryRepository<>(DATA_FILE);
    private final List<Customer> customers;

    public CustomerService() {
        customers = loadData();
    }

    private List<Customer> loadData() {
        try {
            return repository.load();
        } catch (IOException | ClassNotFoundException exception) {
            AppLogger.log("Cannot load customer data", exception);
            return new ArrayList<>();
        }
    }

    public boolean add(Customer customer) {
        if (findByCode(customer.getCode()) != null) return false;
        customers.add(customer);
        return true;
    }

    public Customer findByCode(String code) {
        if (code == null) return null;
        return customers.stream()
                .filter(customer -> customer.getCode().equalsIgnoreCase(code.trim()))
                .findFirst().orElse(null);
    }

    public List<Customer> searchByName(String keyword) {
        String normalizedKeyword = keyword.toLowerCase(Locale.ROOT);
        return customers.stream()
                .filter(customer -> customer.getName().toLowerCase(Locale.ROOT).contains(normalizedKeyword))
                .sorted(Comparator.comparing(Customer::getName, String.CASE_INSENSITIVE_ORDER))
                .toList();
    }

    public List<Customer> getSortedCustomers() {
        return customers.stream()
                .sorted(Comparator.comparing(Customer::getName, String.CASE_INSENSITIVE_ORDER))
                .toList();
    }

    public void save() throws IOException {
        repository.save(customers);
    }
}
