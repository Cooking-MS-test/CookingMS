package Notification_And_Alerts;

import Zahi.Chef;
import java.time.format.DateTimeFormatter;

public class ReminderService {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // For Customers
    public String createDeliveryReminderMessage(Meal meal) {
        return String.format(
                "Reminder: Your %s will be delivered on %s",
                meal.getName(),
                meal.getDeliveryDate().format(DATE_FORMATTER)
        );
    }

    public void sendDeliveryReminder(Customer customer, Meal meal) {
        String message = createDeliveryReminderMessage(meal);
        // Send via email/SMS (e.g., customer.getEmail())
        System.out.println("Sent to customer: " + message);
    }


    // For Chefs
    public String createCookingNotificationMessage(Meal meal) {
        return String.format(
                "Cooking Task: Prepare %s on %s",
                meal.getName(),
                meal.getCookingDate().format(DATE_FORMATTER)
        );
    }

    public void sendCookingNotification(Chef chef, Meal meal) {
        String message = createCookingNotificationMessage(meal);
        // Send via chef's contact method
        System.out.println("Sent to chef: " + message);
    }
}
