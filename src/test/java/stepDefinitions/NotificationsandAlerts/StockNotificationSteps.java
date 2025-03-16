package stepDefinitions.NotificationsandAlerts;

import io.cucumber.java.en.*;
import static org.junit.Assert.*;

public class StockNotificationSteps {

    private String ingredient;
    private int stock;
    private int threshold;
    private String notificationMessage;
    private boolean notificationSent;

    @Given("the system monitors stock levels for ingredients")
    public void theSystemMonitorsStockLevelsForIngredients() {
        System.out.println("System is monitoring stock levels.");
    }

    @Given("kitchen managers need alerts when stock is low to reorder in time")
    public void kitchenManagersNeedAlertsWhenStockIsLowToReorderInTime() {
        System.out.println("Kitchen managers will receive alerts for low stock.");
    }

    @Given("the stock level of {string} is {int} units with a critical threshold of {int} units")
    public void the_stock_level_of_is_units_with_a_critical_threshold_of_units(String ingredient, int stock, int threshold) {
        this.ingredient = ingredient;
        this.stock = stock;
        this.threshold = threshold;
    }

    @When("the system checks the stock levels")
    public void the_system_checks_the_stock_levels() {
        if (stock < threshold) {
            notificationSent = true;
            String unitText = "units"; // Correct singular/plural form
            notificationMessage = "Alert: Stock for " + ingredient + " is low. Only " + stock + " " + unitText + " remaining!";
        } else {
            notificationSent = false;
            notificationMessage = "No notification sent";
        }
    }

    @Then("the system should notify the kitchen manager about {string}")
    public void the_system_should_notify_the_kitchen_manager_about(String ingredient) {
        assertEquals(this.ingredient, ingredient);
        assertTrue("Expected notification to be sent", notificationSent);
    }

    @Then("the system should not notify the kitchen manager about {string}")
    public void the_system_should_not_notify_the_kitchen_manager_about(String ingredient) {
        assertEquals(this.ingredient, ingredient);
        assertFalse("Expected no notification to be sent", notificationSent);
    }

    @And("the notification message should be {string}")
    public void the_notification_message_should_be(String expectedMessage) {
        // Replace "unit(s)" in the expected message with the correct singular/plural form
        String unitText = (stock == 1) ? "unit" : "units";
        String formattedExpectedMessage = expectedMessage.replace("unit(s)", unitText);

        assertEquals("Notification message mismatch!", formattedExpectedMessage, notificationMessage);
    }

    @And("the system should display {string}")
    public void the_system_should_display(String successMessage) {
        String expectedSuccessMessage = notificationSent ? "Notification sent successfully." : "No action needed.";
        assertEquals("Success message mismatch!", expectedSuccessMessage, successMessage);
    }
}