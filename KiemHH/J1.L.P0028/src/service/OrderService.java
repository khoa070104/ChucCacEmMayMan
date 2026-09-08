package service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import model.FeastOrder;
import repository.BinaryRepository;
import util.AppLogger;

public class OrderService {
    private static final String DATA_FILE = "feast_order_service.dat";
    private final BinaryRepository<FeastOrder> repository = new BinaryRepository<>(DATA_FILE);
    private final List<FeastOrder> orders;

    public OrderService() {
        orders = loadData();
    }

    private List<FeastOrder> loadData() {
        try {
            return repository.load();
        } catch (IOException | ClassNotFoundException exception) {
            AppLogger.log("Cannot load order data", exception);
            return new ArrayList<>();
        }
    }

    public FeastOrder create(String customerCode, String menuCode, int numberOfTables,
            LocalDate eventDate, java.math.BigDecimal menuPrice) {
        if (isDuplicate(0, customerCode, menuCode, eventDate)) return null;
        int nextId = orders.stream().mapToInt(FeastOrder::getOrderId).max().orElse(0) + 1;
        FeastOrder order = new FeastOrder(nextId, customerCode, menuCode,
                numberOfTables, eventDate, menuPrice);
        orders.add(order);
        return order;
    }

    public FeastOrder findById(int orderId) {
        return orders.stream().filter(order -> order.getOrderId() == orderId)
                .findFirst().orElse(null);
    }

    public boolean isDuplicate(int excludedOrderId, String customerCode,
            String menuCode, LocalDate eventDate) {
        return orders.stream().anyMatch(order -> order.getOrderId() != excludedOrderId
                && order.getCustomerCode().equalsIgnoreCase(customerCode)
                && order.getMenuCode().equalsIgnoreCase(menuCode)
                && order.getEventDate().equals(eventDate));
    }

    public List<FeastOrder> getSortedOrders() {
        return orders.stream().sorted(Comparator.comparing(FeastOrder::getEventDate)
                .thenComparingInt(FeastOrder::getOrderId)).toList();
    }

    public void save() throws IOException {
        repository.save(orders);
    }
}
