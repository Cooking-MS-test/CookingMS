package Notification_And_Alerts;

import Zahi.StockCheckResult;
import models.Ingredient;

public class StockMonitor {
    private NotificationService notificationService;


    public StockMonitor(NotificationService notificationService) {

        this.notificationService = notificationService;
    }

    public StockCheckResult checkStock(Ingredient ingredient) {
        if (ingredient.isStockLow()) {  // Check if stock is below threshold
            String message = notificationService.generateLowStockAlert(ingredient);
            return new StockCheckResult(true, message, "Notification sent successfully.");
        } else {
            return new StockCheckResult(false,
                    notificationService.getNoNotificationMessage(),
                    "No action needed.");
        }
    }
}
