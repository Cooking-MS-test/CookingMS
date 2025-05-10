package Notification_And_Alerts;

public class Ingredient {
    private String name;

    private int stock;
    private int threshold;

    // Constructor
    public Ingredient(String name, int stock, int threshold) {
        this.name = name;
        this.stock = stock;
        this.threshold = threshold;
    }

    // Getters & Setters
    public String getName() { return name; }
    public int getStock() { return stock; }
    public int getThreshold() { return threshold; }

    // Business Logic: Check if stock is low
    public boolean isStockLow() {
        return stock < threshold;
    }



}
