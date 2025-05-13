package Notification_And_Alerts;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private List<String> ingredients;
    private ChefNotifier chefNotifier;

    public Recipe(ChefNotifier chefNotifier) {
        this.chefNotifier = chefNotifier;
        this.ingredients = new ArrayList<>();
    }

    public void substituteIngredient(String original, String substitution) {
        ingredients.remove(original);
        ingredients.add(substitution);
        chefNotifier.notifySubstitution(original, substitution);
    }
}
