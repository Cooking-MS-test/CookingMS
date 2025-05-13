package OrderAndMenuCustomization;

import Notification_And_Alerts.Customer;
import models.Ingredient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class MealCustomization {
    private String requestId;
    private List<Ingredient> selectedIngredients;
    private Customer customer;
    private LocalDateTime creationDate;
    private boolean isValid;
    private List<String> validationMessages;

    public MealCustomization(Customer customer, List<Ingredient> ingredients) {
        this.requestId = "CUST-" + UUID.randomUUID().toString().substring(0, 8);
        this.customer = customer;
        this.selectedIngredients = new ArrayList<>(ingredients);
        this.creationDate = LocalDateTime.now();
        this.validationMessages = new ArrayList<>();
        validateCombination();
    }

    private void validateCombination() {
        // Reset validation state
        this.isValid = true;
        this.validationMessages.clear();

        // 1. Check incompatible ingredients
        if (containsIncompatiblePair("Salmon", "Cheese")) {
            validationMessages.add("Salmon and cheese are incompatible");
            isValid = false;
        }

        if (containsIncompatiblePair("Milk", "Lemon")) {
            validationMessages.add("Milk and lemon curdle when combined");
            isValid = false;
        }

        // 2. Check availability
        List<Ingredient> unavailable = selectedIngredients.stream()
                .filter(ing -> !ing.isAvailable())
                .collect(Collectors.toList());

        if (!unavailable.isEmpty()) {
            unavailable.forEach(ing ->
                    validationMessages.add(ing.getName() + " is currently unavailable"));
            isValid = false;
        }
    }

    private boolean containsIncompatiblePair(String ing1, String ing2) {
        boolean hasFirst = selectedIngredients.stream()
                .anyMatch(i -> i.getName().equalsIgnoreCase(ing1));
        boolean hasSecond = selectedIngredients.stream()
                .anyMatch(i -> i.getName().equalsIgnoreCase(ing2));
        return hasFirst && hasSecond;
    }

    // Getters
    public boolean isValid() { return isValid; }
    public List<String> getValidationMessages() { return validationMessages; }
}
