package stepDefinitions.NotificationsandAlerts;

import Notification_And_Alerts.ReminderService;
import Notification_And_Alerts.Meal;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RemindersAndNotificationsSteps {

    private String customerReminder;
    private String chefNotification;
    private Meal meal;
    private ReminderService reminderService = new ReminderService();

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Step Definitions for Customer Reminders

    @Given("the customer has an upcoming meal delivery for {string} on {string}")
    public void the_customer_has_an_upcoming_meal_delivery_for_on(String mealName, String deliveryDateStr) {
        LocalDate date = LocalDate.parse(deliveryDateStr, formatter);
        meal = new Meal(mealName, 0.0);
        meal.setDeliveryDate(date.atStartOfDay());
    }

    @When("the system sends a reminder for the delivery")
    public void the_system_sends_a_reminder_for_the_delivery() {
        customerReminder = reminderService.createDeliveryReminderMessage(meal);
    }

    @Then("the customer should receive a reminder message: {string}")
    public void the_customer_should_receive_a_reminder_message(String expectedReminder) {
        Assert.assertEquals("Reminder message does not match", expectedReminder, customerReminder);
    }

    // Step Definitions for Chef Notifications

    @Given("the chef has a scheduled cooking task for {string} on {string}")
    public void the_chef_has_a_scheduled_cooking_task_for_on(String mealName, String cookingDateStr) {
        LocalDate date = LocalDate.parse(cookingDateStr, formatter);
        meal = new Meal(mealName, 0.0);
        meal.setCookingDate(date.atStartOfDay());
    }

    @When("the system sends a notification for the cooking task")
    public void the_system_sends_a_notification_for_the_cooking_task() {
        chefNotification = reminderService.createCookingNotificationMessage(meal);
    }

    @Then("the chef should receive a notification message: {string}")
    public void the_chef_should_receive_a_notification_message(String expectedNotification) {
        Assert.assertEquals("Notification message does not match", expectedNotification, chefNotification);
    }
}
