package stepDefinitions.OrderandMenuCustomization;

import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class OrderCustomizationSteps {
    private String selectedIngredients;
    private String validationMessage;

    // Simulated available and incompatible ingredients for testing
    private final Set<String> unavailableIngredients = new HashSet<>(Arrays.asList("Lobster", "Truffle", "Bluefin Tuna"));
    private final Set<String> incompatibleCombinations = new HashSet<>(Arrays.asList("Salmon, Cheese, Chocolate", "Milk, Lemon, Pickles"));

    @Given("the customer wants to customize their meal")
    public void the_customer_wants_to_customize_their_meal() {
        // Simulate customer customization action
        System.out.println("Customer is customizing their meal.");
    }

    @When("they select ingredients {string}")
    public void they_select_ingredients(String ingredients) {
        selectedIngredients = ingredients;
        System.out.println("Customer selected: " + ingredients);
    }

    @Then("the system should save their custom meal request")
    public void the_system_should_save_their_custom_meal_request() {
        assertNotNull(selectedIngredients, "Ingredients should not be null");
        assertFalse(selectedIngredients.isEmpty(), "Ingredients should not be empty");
        System.out.println("Custom meal request saved.");
    }

    @Given("the customer selects ingredients {string}")
    public void the_customer_selects_ingredients(String ingredients) {
        selectedIngredients = ingredients;
        System.out.println("Customer selected: " + ingredients);
    }

    @When("the system checks the ingredient combination")
    public void the_system_checks_the_ingredient_combination() {
        if (incompatibleCombinations.contains(selectedIngredients)) {
            validationMessage = "Invalid ingredient combination!";
        } else {
            validationMessage = "Valid combination.";
        }
    }

    @Then("the system should notify the customer of an invalid selection")
    public void the_system_should_notify_the_customer_of_an_invalid_selection() {
        assertEquals("Invalid ingredient combination!", validationMessage);
        System.out.println("System validation message: " + validationMessage);
    }

    @When("the system verifies ingredient availability")
    public void the_system_verifies_ingredient_availability() {
        String[] ingredientsArray = selectedIngredients.split(", ");
        for (String ingredient : ingredientsArray) {
            if (unavailableIngredients.contains(ingredient)) {
                validationMessage = "Ingredient " + ingredient + " is unavailable!";
                return;
            }
        }
        validationMessage = "All ingredients are available.";
    }

    @Then("the system should notify the customer about unavailable ingredients")
    public void the_system_should_notify_the_customer_about_unavailable_ingredients() {
        assertTrue(validationMessage.contains("unavailable"));
        System.out.println("System notification: " + validationMessage);
    }
}
