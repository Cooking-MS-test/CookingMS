package stepDefinitions.InventoryandSupplierManagement;

import InventoryAndSupplierManagement.InventoryManager;
import InventoryAndSupplierManagement.PurchaseOrder;
import InventoryAndSupplierManagement.SupplierAPIService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import models.Ingredient;
import org.junit.Assert;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientPriceAndPurchaseOrderSteps {

    private Ingredient currentIngredient;
    private SupplierAPIService supplierService = new SupplierAPIService();
    private PurchaseOrder poService = new PurchaseOrder();
    private InventoryManager inventoryManager = new InventoryManager(supplierService, poService);
    private double fetchedPrice;
    private String notification;

    @Given("the kitchen manager needs to check the price of {string}")
    public void setupPriceCheck(String ingredientName) {
        currentIngredient = new Ingredient(ingredientName, 0, 0);
    }

    @When("the system requests the latest price from the supplier API")
    public void fetchPriceFromSupplier() {
        fetchedPrice = inventoryManager.checkIngredientPrice(currentIngredient.getName());
    }

    @Then("the system should display the current price of {string} as {string}")
    public void verifyDisplayedPrice(String ingredientName, String expectedPrice) {
        assertEquals(ingredientName, currentIngredient.getName());
        assertEquals(expectedPrice, String.format("$%.2f", fetchedPrice));
    }

    @Given("the stock level of {string} is {int} units")
    public void setStockLevel(String ingredientName, int stock) {
        currentIngredient = new Ingredient(ingredientName, 0, stock);
    }

    @Given("the critical threshold for {string} is {int} units")
    public void setCriticalThreshold(String ingredientName, int threshold) {
        currentIngredient = new Ingredient(ingredientName, threshold, currentIngredient.getStock());
    }

    @When("the system detects that stock level is below the critical threshold")
    public void checkStockLevel() {
        notification = inventoryManager.handleLowStock(currentIngredient);
    }

    @Then("the system should generate a purchase order for {string}")
    public void verifyPurchaseOrderGenerated(String ingredientName) {
        assertNotNull(notification);
        assertEquals(ingredientName, currentIngredient.getName());
    }

    @Then("notify the kitchen manager with a message {string}")
    public void verifyNotification(String expectedMessage) {
        assertEquals(expectedMessage, notification);
    }
}
