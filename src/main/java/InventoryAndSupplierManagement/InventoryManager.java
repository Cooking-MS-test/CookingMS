package InventoryAndSupplierManagement;

import models.Ingredient;
import Notification_And_Alerts.NotificationService;

public class InventoryManager {
    private final SupplierAPIService supplierService;
    private NotificationService notificationService=new NotificationService(); // Reused from previous features
    private  PurchaseOrder poService=new PurchaseOrder();
    public InventoryManager(SupplierAPIService supplierService,
                            NotificationService notificationService) {
        this.supplierService = supplierService;
        this.notificationService = notificationService;
    }
    public InventoryManager(SupplierAPIService supplierService, PurchaseOrder poService) {
        this.supplierService = supplierService;
        this.poService = poService;
    }
    public double checkIngredientPrice(String ingredientName) {
        return supplierService.fetchCurrentPrice(ingredientName);
    }
    public String handleLowStock(Ingredient ingredient) {
        if (ingredient.isStockLow()) {
            return poService.generatePurchaseOrder(ingredient);
        }
        return null;
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
