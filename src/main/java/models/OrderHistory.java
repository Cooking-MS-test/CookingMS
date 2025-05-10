package models;

import Notification_And_Alerts.Customer;
import Notification_And_Alerts.Meal;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderHistory {
    private List<String> pastOrders = new ArrayList<>();


    public List<Order> getCustomerHistory(Customer customer) {
        return customer.getOrders(); // Reuses existing getOrders() from Customer class
    }

    // Reorder functionality
    public Order reorderMeal(Customer customer, String mealName) {
        return customer.getOrders().stream()
                .filter(order -> order.containsMeal(mealName))
                .findFirst()
                .map(Order::createReorder)
                .orElseThrow(() -> new IllegalArgumentException("Meal not found in history"));
    }

    // Admin analytics
    public Map<String, Long> getMealPopularityStats(List<Customer> customers) {
        return customers.stream()
                .flatMap(c -> c.getOrders().stream())
                .flatMap(o -> o.getMeals().stream())
                .collect(Collectors.groupingBy(
                        Meal::getName,
                        Collectors.counting()
                ));
    }

    public void addOrder(String order) {
        pastOrders.add(order);
    }

    public List<String> getPastOrders() {
        return pastOrders;
    }
}
