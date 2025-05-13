package Notification_And_Alerts;

import models.Order;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Customer {
    private String name;
    private String email; // or phone for SMS
    private List<Meal> upcomingMeals;
    private String dietaryPreferences; // e.g., "Vegan", "Gluten-Free"
    private String allergies; // e.g., "Peanuts, Shellfish"
    private List<Order> orders;
   private List<String> pastOrder = new ArrayList<>();
    private Set<DietaryRestriction> restrictions;



    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Customer() {

    }

    public void setRestrictions(Set<DietaryRestriction> restrictions){
         this.restrictions = restrictions;
    }
    public boolean hasRestriction(DietaryRestriction restriction) {
        return restrictions.contains(restriction);
    }

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




    public String reorder(String mealName) {
        // Find the meal in past orders
        for (Order order : orders) {
            if (order.containsMeal(mealName)) {
                // If the meal exists, reorder it and create a new order
                Order reorder = order.createReorder();
                addOrder(reorder);  // Add the new order to the customer's order list
                return "Order Found and ReOrder done successfully: RE-" + reorder.getOrderId();
            }
        }
        return "Order not found";  // If no meal found in past orders
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
    public List<String> getPastOrders() {
        pastOrder.clear(); // clear previous results
        if (orders != null) {
            for (Order order : orders) {
                String mealNames = order.getMeals().stream()
                        .map(Meal::getName)
                        .reduce((a, b) -> a + ", " + b)
                        .orElse("No meals");
                pastOrder.add(mealNames);
            }
        }
        return pastOrder;
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
