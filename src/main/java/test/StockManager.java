package test;
import java.util.HashMap;
import java.util.Map;

public class StockManager {
    private Map<String, Integer> stockLevels;
    private Map<String, Integer> thresholds;
    private NotificationService notificationService;

    public StockManager() {
        this.stockLevels = new HashMap<>();
        this.thresholds = new HashMap<>();
        this.notificationService = new NotificationService();
    }

    public void setStockLevel(String ingredient, int quantity) {
        stockLevels.put(ingredient, quantity);
    }

    public void setCriticalThreshold(String ingredient, int threshold) {
        thresholds.put(ingredient, threshold);
    }

    public void checkStockLevels() {
        for (Map.Entry<String, Integer> entry : stockLevels.entrySet()) {
            String ingredient = entry.getKey();
            int stock = entry.getValue();
            int threshold = thresholds.getOrDefault(ingredient, 0);

            if (stock <= threshold) {
                String message = String.format("Alert: Stock for %s is low. Only %d units remaining!",
                        ingredient, stock);
                notificationService.notifyKitchenManager(ingredient, message);
                System.out.println("Notification sent successfully.");
            } else {
                System.out.println("No action needed.");
            }
        }
    }
}

