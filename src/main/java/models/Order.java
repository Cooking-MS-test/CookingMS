package models;

import Notification_And_Alerts.Customer;
import Notification_And_Alerts.Meal;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Order {
    private String orderId;
    private Customer customer;
    private List<Meal> meals;
    private LocalDateTime orderDate;


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
    public String getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public List<Meal> getMeals() { return meals; }
    public LocalDateTime getOrderDate() { return orderDate; }

    public Object getTotalAmount() {
        return totalAmount;
    }
}







