package models;

public class Customer {
    private String name;
    private String dietaryPreferences;
    private String allergies;

    public Customer(String name) {
        this.name = name;
    }

    public void setDietaryPreferences(String dietaryPreferences) {
        this.dietaryPreferences = dietaryPreferences;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getDietaryPreferences() {
        return dietaryPreferences;
    }

    public String getAllergies() {
        return allergies;
    }

    public String getName() {
        return name;
    }
}
