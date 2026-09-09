package DataObject;

import java.io.IOException;
import java.util.ArrayList;
import Entity.Customer;
import DataObject.CustomerRepository;
import Utilities.AppLogger;

public class CustomerService {
    private final CustomerRepository repository;
    private final ArrayList<Customer> customers;

    public CustomerService() {
        repository = new CustomerRepository();
        customers = loadData();
    }

    private ArrayList<Customer> loadData() {
        try {
            return repository.load();
        } catch (IOException exception) {
            AppLogger.log("Cannot load customer data", exception);
        } catch (ClassNotFoundException exception) {
            AppLogger.log("Customer class cannot be found", exception);
        }
        return new ArrayList<Customer>();
    }

    public boolean add(Customer customer) {
        if (findByCode(customer.getCode()) != null) return false;
        customers.add(customer);
        return true;
    }

    public Customer findByCode(String code) {
        if (code == null) return null;
        for (Customer customer : customers) {
            if (customer.getCode().equalsIgnoreCase(code.trim())) return customer;
        }
        return null;
    }

    public ArrayList<Customer> searchByName(String keyword) {
        ArrayList<Customer> results = new ArrayList<Customer>();
        for (Customer customer : customers) {
            if (customer.getName().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(customer);
            }
        }
        sortByName(results);
        return results;
    }

    public ArrayList<Customer> getSortedCustomers() {
        ArrayList<Customer> results = new ArrayList<Customer>(customers);
        sortByName(results);
        return results;
    }

    private void sortByName(ArrayList<Customer> list) {
        for (int first = 0; first < list.size() - 1; first++) {
            for (int second = first + 1; second < list.size(); second++) {
                if (list.get(first).getName().compareToIgnoreCase(list.get(second).getName()) > 0) {
                    Customer temporary = list.get(first);
                    list.set(first, list.get(second));
                    list.set(second, temporary);
                }
            }
        }
    }

    public void save() throws IOException {
        repository.save(customers);
    }
}
