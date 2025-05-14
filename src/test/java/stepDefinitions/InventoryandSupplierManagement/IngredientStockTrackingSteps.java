package stepDefinitions.InventoryandSupplierManagement;

import Zahi.StockManager;
import io.cucumber.java.en.*;
import models.Ingredient;

import static org.junit.jupiter.api.Assertions.*;

public class IngredientStockTrackingSteps {


    private boolean restockSuggested = false;
    private StockManager stockManager;
    private Ingredient currentIngredient;
    private String notification;


    @Given("the kitchen manager is monitoring ingredient stock levels")
    public void the_kitchen_manager_is_monitoring_ingredient_stock_levels() {
        stockManager = new StockManager();
    }

    @When("the stock level of {string} falls below {string} units")
    public void the_stock_level_of_falls_below_units(String ingredientName, String threshold) {
        currentIngredient = new Ingredient(ingredientName, Integer.parseInt(threshold), Integer.parseInt(threshold) - 2);
        stockManager.addIngredient(currentIngredient);
        restockSuggested = currentIngredient.needsRestocking();
        if (restockSuggested) {
            notification = stockManager.generateRestockNotification(currentIngredient);
        }
    }
    @Then("the system should suggest restocking for {string}")
    public void verifyRestockSuggestion(String ingredientName) {
        assertTrue(restockSuggested);
        assertEquals(ingredientName, currentIngredient.getName());
    }
    @Then("the kitchen manager should see a notification: {string}")
    public void verifyNotification(String expectedNotification) {
        assertEquals(expectedNotification, notification);
    }

    @When("the stock level of {string} is {string} units")
    public void theStockLevelOfIsUnits(String ingredientName, String currentStock) {
        int i =Integer.parseInt(currentStock);
        currentIngredient = new Ingredient(ingredientName, 0, i);
    }


    @When("the threshold for {string} is {string} units")
    public void theThresholdForIsUnits(String ingredientName, String threshold) {
        currentIngredient = new Ingredient(ingredientName,Integer.parseInt(threshold), currentIngredient.getStock());
        stockManager.addIngredient(currentIngredient);
        restockSuggested = currentIngredient.needsRestocking();
    }



    @Then("the system should not suggest restocking for {string}")
    public void theSystemShouldNotSuggestRestockingFor(String ingredientName) {
        assertFalse(restockSuggested);
        assertEquals(ingredientName, currentIngredient.getName());
    }


    @Then("the kitchen manager should not see a restocking notification")
    public void theKitchenManagerShouldNotSeeARestockingNotification() {
        assertNull(notification);
    }
}
