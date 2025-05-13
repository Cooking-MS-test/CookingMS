package stepDefinitions.OrderandMenuCustomization;

import static org.junit.Assert.*;
import io.cucumber.java.en.*;
import java.util.*;
import models.Ingredient;
import Zahi.IngredientService;

public class OrderAndMenuCustomizationSteps {

    private List<String> selectedIngredients;
    private IngredientService ingredientService = new IngredientService();
    private List<String> unavailableIngredients = new ArrayList<>();
    private boolean isCombinationValid;
    private String notificationMessage;

    @Given("the customer wants to customize their meal")
    public void the_customer_wants_to_customize_their_meal() {
        selectedIngredients = new ArrayList<>();
    }

    @When("they select ingredients {string}")
    public void they_select_ingredients(String ingredients) {
        selectedIngredients = Arrays.asList(ingredients.split(",\\s*"));
        // Simulate saving the custom request
        ingredientService.saveCustomMealRequest(selectedIngredients);
    }

    @Then("the system should save their custom meal request")
    public void the_system_should_save_their_custom_meal_request() {
        assertTrue("Custom meal request was not saved", ingredientService.isCustomMealSaved(selectedIngredients));
    }

    @Given("the customer selects ingredients {string}")
    public void the_customer_selects_ingredients(String ingredients) {
        selectedIngredients = Arrays.asList(ingredients.split(",\\s*"));
    }

    @When("the system checks the ingredient combination")
    public void the_system_checks_the_ingredient_combination() {
        isCombinationValid = ingredientService.validateCombination(selectedIngredients);
    }

    @Then("the system should notify the customer of an invalid selection")
    public void the_system_should_notify_the_customer_of_an_invalid_selection() {
        assertFalse("Combination should be invalid", isCombinationValid);
    }

    @When("the system verifies ingredient availability")
    public void the_system_verifies_ingredient_availability() {
        unavailableIngredients = ingredientService.checkAvailability(selectedIngredients);
    }

    @Then("the system should notify the customer about unavailable ingredients")
    public void the_system_should_notify_the_customer_about_unavailable_ingredients() {
        assertFalse("Some ingredients should be unavailable", unavailableIngredients.isEmpty());
        System.out.println("Unavailable ingredients: " + String.join(", ", unavailableIngredients));
    }
}
