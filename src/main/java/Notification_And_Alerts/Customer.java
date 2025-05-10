package Notification_And_Alerts;

import java.util.List;

public class Customer {
    private String name;
    private String email; // or phone for SMS
    private List<Meal> upcomingMeals;
    private String dietaryPreferences; // e.g., "Vegan", "Gluten-Free"
    private String allergies; // e.g., "Peanuts, Shellfish"

    public Customer(String name) {
        this.name = name;
    }
    public void addUpcomingMeal(Meal meal) {
        upcomingMeals.add(meal);
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
}
