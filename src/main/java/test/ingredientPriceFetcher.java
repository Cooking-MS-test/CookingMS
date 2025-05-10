package test;

import java.util.HashMap;
import java.util.Map;

public class ingredientPriceFetcher {
    private Map<String, Double> supplierPrices;

    public ingredientPriceFetcher() {
        this.supplierPrices = new HashMap<>();
        // Initialize with some sample prices
        supplierPrices.put("Flour", 2.50);
        supplierPrices.put("Sugar", 1.80);
        supplierPrices.put("Butter", 4.20);
    }

    public double getCurrentPrice(String ingredient) {
        return supplierPrices.getOrDefault(ingredient, 0.0);
    }
}
