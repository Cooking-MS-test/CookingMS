package stepDefinitions.OrderandMenuCustomization;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;

public class IngredientSubstitutionSteps {

    private String selectedIngredient;
    private List<String> suggestedAlternatives;
    private String selectedAlternative;
    private String chefAlert;



    @Given("I am on the meal customization page")
    public void i_am_on_the_meal_customization_page() {
        navigateToMealCustomizationPage();
    }

    @When("I select {string} as an ingredient")
    public void i_select_as_an_ingredient(String ingredient) {
        this.selectedIngredient = ingredient;
        System.out.println("Selecting ingredient: " + ingredient);


        this.suggestedAlternatives = getSuggestedAlternatives(ingredient);
    }

    @When("{string} does not fit my dietary restrictions")
    public void does_not_fit_my_dietary_restrictions(String ingredient) {
        boolean isRestricted = checkDietaryRestriction(ingredient);
        Assert.assertTrue(ingredient + " should not fit dietary restrictions", isRestricted);
    }

    @Then("the system should suggest alternative ingredients: {string}")
    public void the_system_should_suggest_alternative_ingredients(String alternatives) {
        List<String> expectedAlternatives = Arrays.asList(alternatives.split(", "));
        Assert.assertEquals("Suggested alternatives do not match", expectedAlternatives, suggestedAlternatives);
    }

    @Then("I should see a message {string}")
    public void i_should_see_a_message(String expectedMessage) {
        String actualMessage = getMessageDisplayed();
        Assert.assertEquals("Displayed message does not match", expectedMessage, actualMessage);
    }



    @When("{string} is currently unavailable")
    public void is_currently_unavailable(String ingredient) {
        boolean isUnavailable = checkIngredientAvailability(ingredient);
        Assert.assertTrue(ingredient + " should be unavailable", isUnavailable);
    }



    @Given("a customer has requested a substitution for {string}")
    public void a_customer_has_requested_a_substitution_for(String ingredient) {
        this.selectedIngredient = ingredient; // Ensure the selected ingredient is set
        requestSubstitution(ingredient);
    }

    @When("the system suggests {string} as alternatives")
    public void the_system_suggests_as_alternatives(String alternatives) {
        this.suggestedAlternatives = Arrays.asList(alternatives.split(", "));
    }

    @When("the customer selects {string}")
    public void the_customer_selects(String alternative) {
        this.selectedAlternative = alternative;
        System.out.println("Customer selected alternative: " + alternative);


        this.chefAlert = getChefAlert();
    }

    @Then("the chef should receive an alert {string}")
    public void the_chef_should_receive_an_alert(String expectedAlert) {
        Assert.assertEquals("Chef alert does not match", expectedAlert, chefAlert);
    }

    @Then("the chef should be able to approve or adjust the final recipe")
    public void the_chef_should_be_able_to_approve_or_adjust_the_final_recipe() {
        boolean canApproveOrAdjust = checkChefPermissions();
        Assert.assertTrue("Chef should be able to approve or adjust the recipe", canApproveOrAdjust);
    }

    // Helper Methods

    private void navigateToMealCustomizationPage() {

        System.out.println("Navigating to meal customization page...");
    }

    private boolean checkDietaryRestriction(String ingredient) {

        List<String> restrictedIngredients = Arrays.asList("dairy cheese", "peanuts", "gluten pasta");
        return restrictedIngredients.contains(ingredient);
    }

    private boolean checkIngredientAvailability(String ingredient) {

        List<String> unavailableIngredients = Arrays.asList("salmon", "avocado", "quinoa");
        return unavailableIngredients.contains(ingredient);
    }

    private String getMessageDisplayed() {

        return "Here are some alternatives for " + selectedIngredient + ": " + String.join(", ", suggestedAlternatives);
    }

    private void requestSubstitution(String ingredient) {

        System.out.println("Customer requested substitution for: " + ingredient);
    }

    private String getChefAlert() {

        return "A substitution has been applied: " + selectedIngredient + " replaced with " + selectedAlternative;
    }

    private boolean checkChefPermissions() {

        return true;
    }

    private List<String> getSuggestedAlternatives(String ingredient) {

        switch (ingredient) {
            case "dairy cheese":
                return Arrays.asList("vegan cheese", "cashew cheese");
            case "peanuts":
                return Arrays.asList("almonds", "sunflower seeds");
            case "gluten pasta":
                return Arrays.asList("rice pasta", "quinoa pasta");
            case "salmon":
                return Arrays.asList("trout", "cod");
            case "avocado":
                return Arrays.asList("hummus", "guacamole");
            case "quinoa":
                return Arrays.asList("couscous", "bulgur wheat");
            default:
                throw new IllegalArgumentException("No alternatives found for ingredient: " + ingredient);
        }
    }
}