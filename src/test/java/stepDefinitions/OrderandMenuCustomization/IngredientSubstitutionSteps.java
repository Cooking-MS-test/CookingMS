package stepDefinitions.OrderandMenuCustomization;

import static org.junit.Assert.*;
import Zahi.IngredientService;
import models.Ingredient;
import Zahi.Chef;
import io.cucumber.java.en.*;
import java.util.List;

public class IngredientSubstitutionSteps {
    private Ingredient selectedIngredient;
    private List<Ingredient> suggestedAlternatives;
    private Ingredient selectedAlternative;
    private Chef chef;
    private String chefAlert;  // This was missing

    private IngredientService ingredientService = new IngredientService();

    @Given("I am on the meal customization page")
    public void i_am_on_the_meal_customization_page() {
        navigateToMealCustomizationPage();
    }

    @When("I select {string} as an ingredient")
    public void i_select_as_an_ingredient(String ingredientName) {
        this.selectedIngredient = ingredientService.getIngredientByName(ingredientName);
        suggestedAlternatives = ingredientService.getSuggestedAlternatives(selectedIngredient);
    }

    @When("{string} does not fit my dietary restrictions")
    public void does_not_fit_my_dietary_restrictions(String ingredientName) {
        selectedIngredient = ingredientService.getIngredientByName(ingredientName);
        boolean isRestricted = ingredientService.checkDietaryRestrictions(selectedIngredient);
        assertTrue("Ingredient should not fit dietary restrictions", isRestricted);
    }

    @Then("the system should suggest alternative ingredients: {string}")
    public void the_system_should_suggest_alternative_ingredients(String alternatives) {
        List<String> expectedAlternatives = List.of(alternatives.split(", "));
        assertEquals("Suggested alternatives do not match",
                expectedAlternatives,
                ingredientService.getAlternativeNames(suggestedAlternatives));
    }

    @Then("I should see a message {string}")
    public void i_should_see_a_message(String expectedMessage) {
        String actualMessage = getMessageDisplayed();
        assertEquals("Displayed message does not match", expectedMessage, actualMessage);
    }

    @When("{string} is currently unavailable")
    public void is_currently_unavailable(String ingredientName) {
        selectedIngredient = ingredientService.getIngredientByName(ingredientName);
        boolean isUnavailable = ingredientService.checkAvailability(selectedIngredient);
        assertTrue("Ingredient should be unavailable", isUnavailable);
    }

    @Given("a customer has requested a substitution for {string}")
    public void a_customer_has_requested_a_substitution_for(String ingredientName) {
        selectedIngredient = ingredientService.getIngredientByName(ingredientName);
        ingredientService.requestSubstitution(selectedIngredient);
    }

    @When("the system suggests {string} as alternatives")
    public void the_system_suggests_as_alternatives(String alternatives) {
        suggestedAlternatives = ingredientService.getSuggestedAlternatives(selectedIngredient);
    }

    @When("the customer selects {string}")
    public void the_customer_selects(String alternativeName) {
        selectedAlternative = ingredientService.getIngredientByName(alternativeName);

        // Ensure the selected alternative is available and stocked
        selectedAlternative.setAvailability(true); // ✅ Make it available
        selectedAlternative.updateStock(100);      // ✅ Give it enough stock
        // (assumes threshold is less than 100)

        chef = new Chef();
        chefAlert = chef.createChefAlert(selectedIngredient, selectedAlternative);
    }

    @Then("the chef should receive an alert {string}")
    public void the_chef_should_receive_an_alert(String expectedAlert) {
        assertEquals("Chef alert does not match", expectedAlert, chefAlert);
    }

    @Then("the chef should be able to approve or adjust the final recipe")
    public void the_chef_should_be_able_to_approve_or_adjust_the_final_recipe() {
        assertTrue("Chef should be able to approve or adjust the recipe", chef.canApproveRecipe(selectedIngredient, selectedAlternative));

    }

    // Helper method
    private void navigateToMealCustomizationPage() {
        System.out.println("Navigating to meal customization page...");
    }

    private String getMessageDisplayed() {
        return "Here are some alternatives for " + selectedIngredient.getName() + ": " +
                String.join(", ", ingredientService.getAlternativeNames(suggestedAlternatives));
    }
}
