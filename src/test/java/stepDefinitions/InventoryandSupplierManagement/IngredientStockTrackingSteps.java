package stepDefinitions.InventoryandSupplierManagement;

import io.cucumber.java.en.*;

public class IngredientStockTrackingSteps {

    private String ingredient;
    private int stockLevel;
    private int threshold;
    private boolean restockSuggested = false;

    @Given("the kitchen manager is monitoring ingredient stock levels")
    public void the_kitchen_manager_is_monitoring_ingredient_stock_levels() {
        System.out.println("Kitchen manager is monitoring ingredient stock levels.");
    }

    @When("the stock level of {string} falls below {string} units")
    public void the_stock_level_of_falls_below_units(String ingredient, String threshold) {
        this.ingredient = ingredient;
        this.threshold = Integer.parseInt(threshold);
        this.stockLevel = this.threshold - 2; // Simulating a stock level drop below the threshold
        System.out.println("Stock level of " + ingredient + " is now " + stockLevel + " units, which is below the threshold of " + threshold + " units.");
    }

    @When("the stock level of {string} is {string} units")
    public void theStockLevelOfIsUnits(String ingredient, String stockLevel) {
        this.ingredient = ingredient;
        this.stockLevel = Integer.parseInt(stockLevel);
        System.out.println("Stock level of " + ingredient + " is " + stockLevel + " units.");
    }


    @When("the threshold for {string} is {string} units")
    public void theThresholdForIsUnits(String ingredient, String threshold) {
        this.threshold = Integer.parseInt(threshold);
        System.out.println("Threshold for " + ingredient + " is " + threshold + " units.");
    }

    @Then("the system should suggest restocking for {string}")
    public void the_system_should_suggest_restocking_for(String ingredient) {
        restockSuggested = true;
        System.out.println("System suggests restocking for " + ingredient);
    }

    @Then("the system should not suggest restocking for {string}")
    public void theSystemShouldNotSuggestRestockingFor(String ingredient) {
        if (stockLevel >= threshold) {
            restockSuggested = false;
            System.out.println("No restocking needed for " + ingredient);
        }
    }

    @And("the kitchen manager should see a notification: {string}")
    public void the_kitchen_manager_should_see_a_notification(String notification) {
        if (restockSuggested) {
            System.out.println("Notification displayed: " + notification);
        }
    }

    @Then("the kitchen manager should not see a restocking notification")
    public void theKitchenManagerShouldNotSeeARestockingNotification() {
        if (!restockSuggested) {
            System.out.println("No restocking notification displayed.");
        }
    }
}
