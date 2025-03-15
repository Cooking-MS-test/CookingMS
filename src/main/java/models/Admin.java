package models;

public class Admin {
    public void analyzeOrders(OrderHistory history) {
        System.out.println("Analyzing orders: " + history.getPastOrders());
    }
}
