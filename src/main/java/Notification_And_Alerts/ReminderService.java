package Notification_And_Alerts;

public class ReminderService {
    // For Customers
    public String createDeliveryReminderMessage(Meal meal) {
        return String.format(
                "Reminder: Your %s will be delivered on %s",
                meal.getName(),
                meal.getDeliveryDate()
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
                meal.getCookingDate()
        );
    }

    public void sendCookingNotification(Chef chef, Meal meal) {
        String message = createCookingNotificationMessage(meal);
        // Send via chef's contact method
        System.out.println("Sent to chef: " + message);
    }
}
