package Zahi;

import models.Ingredient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IngredientService {

    public Ingredient getIngredientByName(String name) {
        return new Ingredient(name);
    }

    public boolean checkDietaryRestrictions(Ingredient ingredient) {
        List<String> restrictedIngredients = Arrays.asList("dairy cheese", "peanuts", "gluten pasta");
        return restrictedIngredients.contains(ingredient.getName());
    }

    public boolean checkAvailability(Ingredient ingredient) {
        List<String> unavailableIngredients = Arrays.asList("salmon", "avocado", "quinoa");
        return unavailableIngredients.contains(ingredient.getName());
    }

    public List<Ingredient> getSuggestedAlternatives(Ingredient ingredient) {
        switch (ingredient.getName()) {
            case "dairy cheese":
                return Arrays.asList(new Ingredient("vegan cheese"), new Ingredient("cashew cheese"));
            case "peanuts":
                return Arrays.asList(new Ingredient("almonds"), new Ingredient("sunflower seeds"));
            case "gluten pasta":
                return Arrays.asList(new Ingredient("rice pasta"), new Ingredient("quinoa pasta"));
            case "salmon":
                return Arrays.asList(new Ingredient("trout"), new Ingredient("cod"));
            case "avocado":
                return Arrays.asList(new Ingredient("hummus"), new Ingredient("guacamole"));
            case "quinoa":
                return Arrays.asList(new Ingredient("couscous"), new Ingredient("bulgur wheat"));
            default:
                return Arrays.asList();
        }
    }

    public List<String> getAlternativeNames(List<Ingredient> ingredients) {
        return ingredients.stream().map(Ingredient::getName).collect(Collectors.toList());
    }

    public void requestSubstitution(Ingredient ingredient) {
        System.out.println("Customer requested substitution for: " + ingredient.getName());
    }
    public void saveCustomMealRequest(List<String> ingredients) {
        // Simulated saving logic
        System.out.println("Custom meal request saved for: " + ingredients);
    }

    public boolean isCustomMealSaved(List<String> ingredients) {
        // Dummy logic to always return true for now
        return true;
    }

    public boolean validateCombination(List<String> ingredients) {
        List<List<String>> invalidCombinations = Arrays.asList(
                Arrays.asList("Salmon", "Cheese", "Chocolate"),
                Arrays.asList("Milk", "Lemon", "Pickles")
        );

        for (List<String> invalidCombo : invalidCombinations) {
            if (ingredients.containsAll(invalidCombo)) {
                return false;
            }
        }

        return true;
    }

    public List<String> checkAvailability(List<String> ingredients) {
        List<String> unavailable = new ArrayList<>();
        for (String ingredientName : ingredients) {
            Ingredient ingredient = getIngredientByName(ingredientName);
            if (ingredient == null || !ingredient.isAvailable()) {
                unavailable.add(ingredientName);
            }
        }
        return unavailable;
    }
}
