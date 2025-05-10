package test;

public class purchaseOrderSystem {
    private StockManager stockManager;
    private NotificationService notificationService;

    public purchaseOrderSystem(StockManager stockManager) {
        this.stockManager = stockManager;
        this.notificationService = new NotificationService();
    }

    public void checkAndCreatePurchaseOrders() {
        // This would integrate with the StockManager to check levels
        // and create orders when needed
        System.out.println("Checking stock levels for purchase orders...");
    }

    public void generatePurchaseOrder(String ingredient) {
        String message = String.format("Purchase Order Placed for %s", ingredient);
        notificationService.notifyKitchenManager(ingredient, message);
    }
}
