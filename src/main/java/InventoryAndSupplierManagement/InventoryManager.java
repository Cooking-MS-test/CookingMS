package InventoryAndSupplierManagement;

import models.Ingredient;
import Notification_And_Alerts.NotificationService;

public class InventoryManager {
    private SupplierAPIService supplierService;
    private NotificationService notificationService=new NotificationService(); // Reused from previous features

    public InventoryManager(SupplierAPIService supplierService,
                            NotificationService notificationService) {
        this.supplierService = supplierService;
        this.notificationService = notificationService;
    }

    public void checkAndReorder(Ingredient ingredient) {
        if (ingredient.needsRestocking()) {
            double price = supplierService.fetchCurrentPrice(ingredient.getName());
            double orderQty = ingredient.getThreshold() * 2; // Example logic

            PurchaseOrder po = new PurchaseOrder(
                    ingredient.getName(),
                    orderQty,
                    price
            );

            if (supplierService.submitPurchaseOrder(po)) {
                String message = String.format(
                        "Purchase Order Placed for %s. Quantity: %.2f %s at $%.2f per %s",
                        ingredient.getName(),
                        orderQty,
                        ingredient.getUnit(),
                        price,
                        ingredient.getUnit()
                );
                notificationService.sendNotification("Kitchen Manager "+message);
            }
        }
    }
}
