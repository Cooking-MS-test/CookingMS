package InventoryAndSupplierManagement;

import models.Ingredient;
import Notification_And_Alerts.NotificationService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryMonitor {

    private NotificationService notificationService; // Reused from previous features
    private List<Ingredient> ingredients;

    public InventoryMonitor(NotificationService notificationService) {
        this.notificationService = notificationService;
        this.ingredients = new ArrayList<>();
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public void checkAllStockLevels() {
        ingredients.forEach(ingredient -> {
            if (ingredient.needsRestock()) {
                notificationService.sendNotification(
                        "Kitchen Manager"+
                        ingredient.getStockStatus()
                );
            }
        });
    }

    public List<Ingredient> getRestockSuggestions() {
        return ingredients.stream()
                .filter(Ingredient::needsRestock)
                .collect(Collectors.toList());
    }
}
