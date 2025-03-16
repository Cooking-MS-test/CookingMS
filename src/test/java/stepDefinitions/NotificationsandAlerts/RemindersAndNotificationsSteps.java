package stepDefinitions.NotificationsandAlerts;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class RemindersAndNotificationsSteps {

    private String meal;
    private String deliveryDate;
    private String cookingDate;
    private String customerReminder;
    private String chefNotification;

    // Step Definitions for Customer Reminders

    @Given("the customer has an upcoming meal delivery for {string} on {string}")
    public void the_customer_has_an_upcoming_meal_delivery_for_on(String meal, String deliveryDate) {
        this.meal = meal;
        this.deliveryDate = deliveryDate;
    }

    @When("the system sends a reminder for the delivery")
    public void the_system_sends_a_reminder_for_the_delivery() {
        this.customerReminder = "Reminder: Your " + meal + " will be delivered on " + deliveryDate;
    }

    @Then("the customer should receive a reminder message: {string}")
    public void the_customer_should_receive_a_reminder_message(String expectedReminder) {
        Assert.assertEquals("Reminder message does not match", expectedReminder, customerReminder);
    }

    // Step Definitions for Chef Notifications

    @Given("the chef has a scheduled cooking task for {string} on {string}")
    public void the_chef_has_a_scheduled_cooking_task_for_on(String meal, String cookingDate) {
        this.meal = meal;
        this.cookingDate = cookingDate;
    }

    @When("the system sends a notification for the cooking task")
    public void the_system_sends_a_notification_for_the_cooking_task() {
        this.chefNotification = "Cooking Task: Prepare " + meal + " on " + cookingDate;
    }

    @Then("the chef should receive a notification message: {string}")
    public void the_chef_should_receive_a_notification_message(String expectedNotification) {
        Assert.assertEquals("Notification message does not match", expectedNotification, chefNotification);
    }
}