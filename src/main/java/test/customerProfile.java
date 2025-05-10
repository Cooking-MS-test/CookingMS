package test;

import java.util.ArrayList;
import java.util.List;

public class customerProfile {
    private String customerId;
    private String dietaryPreferences;
    private List<String> allergies;

    public customerProfile(String customerId) {
        this.customerId = customerId;
        this.allergies = new ArrayList<>();
    }

    public void setDietaryPreferences(String preferences) {
        this.dietaryPreferences = preferences;
    }

    public void addAllergy(String allergy) {
        allergies.add(allergy);
    }

    public void updateDietaryPreferences(String newPreferences) {
        this.dietaryPreferences = newPreferences;
    }

    public void clearAllergies() {
        allergies.clear();
    }

    public String getProfileSummary() {
        return String.format("Customer %s: Preferences - %s, Allergies - %s",
                customerId, dietaryPreferences, String.join(", ", allergies));
    }
}
