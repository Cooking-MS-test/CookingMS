package models;

import Notification_And_Alerts.Customer;

import java.util.List;
import java.util.Map;

public class Admin {

    private final OrderHistory historyService;

    public Admin(OrderHistory historyService) {
        this.historyService = historyService;
    }

    public void analyzeTrends(List<Customer> customers) {
        Map<String, Long> stats = historyService.getMealPopularityStats(customers);
        System.out.println("Meal Popularity Report:");
        stats.forEach((meal, count) ->
                System.out.printf("%s: %d orders%n", meal, count));
    }


    public void analyzeOrders(OrderHistory history) {
        System.out.println("Analyzing orders: " + history.getPastOrders());
    }
}
// PUSHING
