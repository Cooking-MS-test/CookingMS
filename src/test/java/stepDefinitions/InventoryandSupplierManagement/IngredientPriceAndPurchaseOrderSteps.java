package stepDefinitions.InventoryandSupplierManagement;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class IngredientPriceAndPurchaseOrderSteps {

    private Map<String, Double> ingredientPrices = new HashMap<>();
    private Map<String, Integer> ingredientStockLevels = new HashMap<>();
    private Map<String, Integer> ingredientThresholds = new HashMap<>();
    private String currentIngredient;
    private Double currentPrice;
    private String purchaseOrderNotification;

    // Step Definitions for Fetching Real-Time Ingredient Prices

    @Given("the kitchen manager needs to check the price of {string}")
    public void the_kitchen_manager_needs_to_check_the_price_of(String ingredient) {
        this.currentIngredient = ingredient;
    }

    @When("the system requests the latest price from the supplier API")
    public void the_system_requests_the_latest_price_from_the_supplier_API() {
        this.currentPrice = fetchPriceFromSupplierAPI(currentIngredient);
    }

    @Then("the system should display the current price of {string} as {string}")
    public void the_system_should_display_the_current_price_of_as(String ingredient, String expectedPrice) {
        String formattedPrice = String.format("$%.2f", currentPrice); // Format to 2 decimal places
        Assert.assertEquals("Price does not match", expectedPrice, formattedPrice);
    }

    // Step Definitions for Automatic Purchase Order Generation

    @Given("the stock level of {string} is {int} units")
    public void the_stock_level_of_is_units(String ingredient, int stock) {
        ingredientStockLevels.put(ingredient, stock); // Store stock level
    }

    @Given("the critical threshold for {string} is {int} units")
    public void the_critical_threshold_for_is_units(String ingredient, int threshold) {
        ingredientThresholds.put(ingredient, threshold); // Store critical threshold
    }

    @When("the system detects that stock level is below the critical threshold")
    public void the_system_detects_that_stock_level_is_below_the_critical_threshold() {
        for (Map.Entry<String, Integer> entry : ingredientStockLevels.entrySet()) {
            String ingredient = entry.getKey();
            int stock = entry.getValue();
            int threshold = ingredientThresholds.getOrDefault(ingredient, 0);

            if (stock < threshold) {
                generatePurchaseOrder(ingredient);
                return; // Exit after detecting the first match
            }
        }
        purchaseOrderNotification = null; // No purchase order if no stock is below threshold
    }

    @Then("the system should generate a purchase order for {string}")
    public void the_system_should_generate_a_purchase_order_for(String ingredient) {
        Assert.assertNotNull("Purchase order was not generated for " + ingredient, purchaseOrderNotification);
    }

    @Then("notify the kitchen manager with a message {string}")
    public void notify_the_kitchen_manager_with_a_message(String expectedNotification) {
        Assert.assertEquals("Notification does not match", expectedNotification, purchaseOrderNotification);
    }

    // Helper Methods

    private Double fetchPriceFromSupplierAPI(String ingredient) {
        ingredientPrices.put("Flour", 2.50);
        ingredientPrices.put("Sugar", 1.80);
        ingredientPrices.put("Butter", 4.20);

        return ingredientPrices.get(ingredient); // Simulate fetching price
    }

    private void generatePurchaseOrder(String ingredient) {
        purchaseOrderNotification = "Purchase Order Placed for " + ingredient;
    }
}
