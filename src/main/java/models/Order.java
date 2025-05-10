package models;

import Notification_And_Alerts.Customer;
import Notification_And_Alerts.Meal;

import java.time.LocalDateTime;
import java.util.*;

public class Order {
    private String orderId;
    private Customer customer;
    private List<Meal> meals;
    private LocalDateTime orderDate;

    private List<Order> orders = new ArrayList<>();

    private double totalAmount;


    // Constructor/Getters/Setters
    public Order(String orderId, Customer customer,List<Meal> meals, double totalAmount) {
        this.orderId = orderId;
        this.customer = customer;
        this.totalAmount = totalAmount;
        this.meals = meals;
        this.orderDate = LocalDateTime.now();
        this.totalAmount = totalAmount;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getOrders() {
        return Collections.unmodifiableList(orders); // Returns read-only view
    }
    private double calculateTotal() {
        return meals.stream().mapToDouble(Meal::getPrice).sum();
    }
    // New method for reordering
    public Order createReorder() {
        return new Order(generateNewOrderId(), customer, meals, calculateTotal());
    }

   /* private String generateNewOrderId() {
         String newID=String.valueOf((int)(Math.random() * 100000));
         return newID;
    }
    */
   private String generateNewOrderId() {
       return "RE-" + UUID.randomUUID().toString().substring(0, 8);
   }
    public boolean containsMeal(String mealName) {
        return meals.stream().anyMatch(m -> m.getName().equalsIgnoreCase(mealName));
    }

    public String getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public List<Meal> getMeals() { return meals; }
    public LocalDateTime getOrderDate() { return orderDate; }

    public Object getTotalAmount() {
        return totalAmount;
    }
}







