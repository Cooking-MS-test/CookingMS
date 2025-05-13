package InventoryAndSupplierManagement;

import java.util.HashMap;
import java.util.Map;

public class SupplierAPIService {
    private Map<String, Double> priceCache = new HashMap<>();

    public double fetchCurrentPrice(String ingredientName) {
        // Simulate API call with mock data
        Map<String, Double> mockPrices = Map.of(
                "Flour", 2.50,
                "Sugar", 1.80,
                "Butter", 4.20
        );

        double price = mockPrices.getOrDefault(ingredientName, 0.0);
        priceCache.put(ingredientName, price);
        return price;
    }

    public boolean submitPurchaseOrder(PurchaseOrder order) {
        // Simulate API submission
        System.out.println("PO submitted to supplier: " + order);
        return true;
    }
}
