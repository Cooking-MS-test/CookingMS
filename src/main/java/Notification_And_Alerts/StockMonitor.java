package Notification_And_Alerts;

public class StockMonitor {
    private NotificationService notificationService;

    public StockMonitor(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void checkStockLevel(Ingredient ingredient) {
        if (ingredient.isStockLow()) {
            String message = notificationService.generateAlertMessage(ingredient);
            notificationService.sendNotification(message);
            System.out.println("Notification sent successfully.");
        } else {
            System.out.println("No action needed.");
        }
    }
}
