package Notification_And_Alerts;

import java.util.Set;

public class Ingredient {
    private String name;
    private boolean needsRestock;
    private double currentStock;
    private double threshold;
    private String unit; // kg, lbs, etc.
    private double lastKnownPrice;
    private Set<String> dietaryFlags; // e.g., "vegan", "gluten-free"
    private boolean isAvailable;
    private Set<DietaryRestriction> restrictions;

    public Ingredient(String name, boolean available, Set<DietaryRestriction> restrictions) {
        this.name = name;
        this.isAvailable = available;
        this.restrictions = restrictions;
    }

    public Ingredient(String name, Set<String> dietaryFlags) {
        this.name = name;
        this.dietaryFlags = dietaryFlags;
        this.isAvailable = true;
    }

    public boolean matchesDietaryRestriction(String restriction) {
        return dietaryFlags.contains(restriction.toLowerCase());
    }

    public void setAvailability(boolean available) {
        this.isAvailable = available;
    }




    public Ingredient(String name, double threshold, String unit) {
        this.name = name;
        this.threshold = threshold;
        this.unit = unit;
        this.needsRestock = false;
    }

    // Business logic
    public boolean needsRestocking() {
        return currentStock < threshold;
    }

    // Getters and setters



    // Constructor
    public Ingredient(String name, int stock, int threshold, String unit) {
        this.name = name;
        this.currentStock = stock;
        this.threshold = threshold;
        this.unit = unit;
    }
    public void updateStock(double amount) {
        this.currentStock += amount;
        checkStockLevel();
    }
    private void checkStockLevel() {
        this.needsRestock = currentStock < threshold;
    }


    public String getStockStatus() {
        if (needsRestock) {
            return String.format("Restock %s: Current stock is %.2f %s",
                    name, currentStock, unit);
        }
        return String.format("%s stock is sufficient (%.2f %s)",
                name, currentStock, unit);
    }



    // Getters & Setters
    public String getName() { return name; }
    public double getStock() { return currentStock; }
    public double getThreshold() { return threshold; }
    public boolean needsRestock() { return needsRestock; }

    // Business Logic: Check if stock is low
    public boolean isStockLow() {
        return currentStock < threshold;
    }


    public Object getUnit() {
        return unit;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}
