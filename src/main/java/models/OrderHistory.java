package models;

import java.util.ArrayList;
import java.util.List;

public class OrderHistory {
    private List<String> pastOrders = new ArrayList<>();

    public void addOrder(String order) {
        pastOrders.add(order);
    }

    public List<String> getPastOrders() {
        return pastOrders;
    }
}
