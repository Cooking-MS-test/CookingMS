package test;

import java.util.HashMap;
import java.util.Map;

public class billingSystem {
    private Map<String, order> orders;
    private reportGenerator reportGenerator;

    public billingSystem() {
        this.orders = new HashMap<>();
        this.reportGenerator = new reportGenerator();
    }

    public void addOrder(order order) {
        orders.put(order.getOrderId(), order);
    }

    public void generateInvoice(String orderId) {
        order order = orders.get(orderId);
        if (order != null) {
            System.out.println("Invoice generated for order: " + orderId);
        }
    }

    public void generateFinancialReport(String period) {
        reportGenerator.generateReport(period);
    }
}
