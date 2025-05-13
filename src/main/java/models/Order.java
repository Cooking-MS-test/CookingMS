package models;

import Notification_And_Alerts.Customer;
import Notification_And_Alerts.Meal;

import java.time.LocalDateTime;
import java.util.*;

public class Order {
    private String orderId;
    private Customer customer;
    private List<Meal> meals=new ArrayList<>();
    private LocalDateTime orderDate;
    private   Meal m;
    private List<Order> orders = new ArrayList<>();
    private String mealName;

    private double totalAmount;


    // Constructor/Getters/Setters
    public Order(String orderId, Customer customer,String meal,double price) {
        this.orderId = orderId;
        this.customer = customer;
        mealName=meal;
         m=new Meal(mealName,price);
        this.meals.add(m);
        this.orderDate = LocalDateTime.now();
    }
    public Order(String orderId,Customer customer,List<Meal> meals) {
        this.orderId = orderId;
        this.customer = customer;
        this.meals = meals;
    }

    private double calculateTotal() {
        return meals.stream().mapToDouble(Meal::getPrice).sum();
    }
    // New method for reordering
    public void createReorder() {
        new Order(generateNewOrderId(), customer, meals);
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
    public String getMealsName(){

       return mealName;
    }

    public Double getTotalAmount() {
       totalAmount=calculateTotal();
        return totalAmount;
    }
}







