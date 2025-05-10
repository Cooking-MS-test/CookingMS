package Notification_And_Alerts;

import java.time.LocalDate;
import java.util.List;

public class Meal {
    private String name;
    private LocalDate deliveryDate;
    private LocalDate cookingDate;
    private double price;

    // Constructor, Getters, Setters
    public Meal(String name, LocalDate deliveryDate, LocalDate cookingDate) {
        this.name = name;
        this.deliveryDate = deliveryDate;
        this.cookingDate = cookingDate;
    }

    public double getPrice() {
        return price;
    }

    // Example getters
    public String getName() { return name; }
    public LocalDate getDeliveryDate() { return deliveryDate; }
    public LocalDate getCookingDate() { return cookingDate; }
}
