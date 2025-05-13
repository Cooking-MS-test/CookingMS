package models;

import Notification_And_Alerts.Customer;
import Notification_And_Alerts.Meal;

import java.time.LocalDateTime;
import java.util.*;

public class Order {
    private String orderId;
    private Customer customer;
    private List<Meal> meals = new ArrayList<>();
    private LocalDateTime orderDate;
    private double totalAmount;


    public Order(String orderId, Customer customer, String meal, double price) {
        this.orderId = orderId;
        this.customer = customer;
        this.meals.add(new Meal(meal, price));
        this.orderDate = LocalDateTime.now();
        this.totalAmount = calculateTotal();
    }

    public Order(String orderId, Customer customer, List<Meal> meals) {
        this.orderId = orderId;
        this.customer = customer;
        this.meals = meals;
        this.orderDate = LocalDateTime.now();
        this.totalAmount = calculateTotal();
    }

    public Order createReorder() {
        String newOrderId = "RE-" + UUID.randomUUID().toString().substring(0, 8);

        List<Meal> reorderedMeals = new ArrayList<>();
        for (Meal meal : meals) {
            reorderedMeals.add(new Meal(meal.getName(), meal.getPrice()));
        }
        return new Order(newOrderId, customer, reorderedMeals);
    }


    private double calculateTotal() {
        return meals.stream().mapToDouble(Meal::getPrice).sum();
    }

    public boolean containsMeal(String mealName) {
        if (mealName == null || mealName.trim().isEmpty()) {
            return false;
        }

        return meals.stream().anyMatch(m -> m.getName() != null && m.getName().trim().equalsIgnoreCase(mealName.trim()));
    }



    public String getOrderId() {
        return orderId;
    }
    public Customer getCustomer() { return customer; }
    public List<Meal> getMeals() { return meals; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public double getTotalAmount() { return totalAmount; }
}
