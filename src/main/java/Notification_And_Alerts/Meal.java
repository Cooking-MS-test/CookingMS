package Notification_And_Alerts;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Meal {
    private String name;
    private LocalDateTime deliveryDate;
    private LocalDateTime cookingDate;
    private double price;

    public Meal(String name ,double price) {
        this.name = name;
        this.price = price;

    }
    // Constructor, Getters, Setters
    public Meal(String name, LocalDateTime deliveryDate, LocalDateTime cookingDate) {
        this.name = name;
        this.deliveryDate = deliveryDate;
        this.cookingDate = cookingDate;
    }
    public void setDeliveryDate(LocalDateTime deliveryDate){
        this.deliveryDate = deliveryDate;
    }

    public double getPrice() {
        return price;
    }

    // Example getters
    public String getName() { return name; }
    public LocalDateTime getDeliveryDate() { return deliveryDate; }
    public LocalDateTime getCookingDate() { return cookingDate; }
}
