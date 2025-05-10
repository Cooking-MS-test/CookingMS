package Notification_And_Alerts;

import models.Order;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private String email; // or phone for SMS
    private List<Meal> upcomingMeals;
    private String dietaryPreferences; // e.g., "Vegan", "Gluten-Free"
    private String allergies; // e.g., "Peanuts, Shellfish"
    private List<Order> orders;




    // Helper method for profile summary
    public String getDietarySummary() {
        return String.format(
                "Dietary Preferences: %s | Allergies: %s",
                dietaryPreferences, allergies
        );
    }

    public Customer(String name) {
        this.name = name;
    }
    public void addUpcomingMeal(Meal meal) {
        upcomingMeals.add(meal);
    }
    public void addOrder(Order order) {
        if (orders == null) orders = new ArrayList<>();
        orders.add(order);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setDietaryPreferences(String dietaryPreferences) {
        if (dietaryPreferences == null) {
            this.dietaryPreferences = "None";
        } else {
            this.dietaryPreferences = dietaryPreferences.trim();
        }
    }

    // Setter for allergies
    public void setAllergies(String allergies) {
        if (allergies == null || allergies.isEmpty()) {
            this.allergies = "None";
        } else {
            this.allergies = allergies.trim();
        }
    }

    // Getter for dietary preferences
    public String getDietaryPreferences() {
        return this.dietaryPreferences;
    }

    // Getter for allergies
    public String getAllergies() {
        return this.allergies;
    }

    public String getName() {
        return this.name;
    }

    public Object getEmail() {
        return this.email;
    }
}
