package InventoryAndSupplierManagement;

import models.Ingredient;

import java.time.LocalDateTime;
import java.util.UUID;

public class PurchaseOrder {
    private String id;
    private LocalDateTime date;
    private String ingredientName;
    private double quantity;
    private double unitPrice;
    private String status;

    public PurchaseOrder() {}
    // Constructor and methods
    public PurchaseOrder(String ingredientName, double quantity, double unitPrice) {
        this.id = "PO-" + UUID.randomUUID().toString().substring(0, 8);
        this.date = LocalDateTime.now();
        this.ingredientName = ingredientName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.status = "Pending";
    }
    public String generatePurchaseOrder(Ingredient ingredient) {
        // In real implementation, this would create a PO in the system
        return "Purchase Order Placed for " + ingredient.getName();
    }
}
