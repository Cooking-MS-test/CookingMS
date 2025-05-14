package Notification_And_Alerts;


import models.Ingredient;

public class NotificationService {
    public String generateAlertMessage(Ingredient ingredient) {
        return String.format(
                "Alert: Stock for %s is low. Only %d units remaining!",
                ingredient.getName(),
                ingredient.getStock()
        );
    }
    public String generateLowStockAlert(Ingredient ingredient) {
        return String.format("Alert: Stock for %s is low. Only %d units remaining!",
                ingredient.getName(), ingredient.getStock());
    }

    public void sendNotification(String message) {
        // Logic to send notification (e.g., email, SMS, dashboard alert)
        System.out.println("Notification sent: " + message);
    }
    public String getNoNotificationMessage() {
        return "No notification sent";
    }}
