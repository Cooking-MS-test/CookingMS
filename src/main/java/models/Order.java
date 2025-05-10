package models;

import Notification_And_Alerts.Customer;
import Notification_And_Alerts.Meal;

import java.util.Date;
import java.util.List;

public class Order {
    private String orderId;
    private Customer customer;
    private List<Meal> meals;
    private Date orderDate;

    public Order(String orderId, Customer customer, List<Meal> meals, Date orderDate) {
        this.orderId = orderId;
        this.customer = customer;
        this.meals = meals;
        this.orderDate = orderDate;
    }

    public String getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public List<Meal> getMeals() { return meals; }
    public Date getOrderDate() { return orderDate; }
}
