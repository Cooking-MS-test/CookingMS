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


    public Customer() {}
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
    public String reorder(String idOrder){
        String message;
        Order reOrder=null;
        for (Order order : orders) {
            if(order.getOrderId()==idOrder){
                reOrder = order;
            }


        }
        if(reOrder== null){
            message = "Order Not Found";
            return message;
        }
        else{
            reOrder.createReorder();
            message = "Order Found and ReOrder done successfully";
            return message;
        }


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
    public List<String> getPastOrders(){

        for (Order order : orders) {

            String orderr=order.getMealsName();
            pastOrder.add(orderr);
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
