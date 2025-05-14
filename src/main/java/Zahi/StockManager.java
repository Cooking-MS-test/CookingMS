package Zahi;

import models.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class StockManager {
    private List<Ingredient> ingredients;

    public StockManager() {
        this.ingredients = new ArrayList<>();
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public List<Ingredient> getIngredientsNeedingRestock() {
        List<Ingredient> needsRestock = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            if (ingredient.needsRestocking()) {
                needsRestock.add(ingredient);
            }
        }
        return needsRestock;
    }

    public String generateRestockNotification(Ingredient ingredient) {
        return String.format("Restock %s: Current stock is %d units",
                ingredient.getName(),
                ingredient.getStock());
    }
}
