package Billing_System;

import models.Order;

import java.util.List;

public class ReportService {
    public String generateFinancialReport(String period, List<Order> orders) {
        double totalRevenue = 0.0;
        for (Order order : orders) {
            double totalAmount = (double) order.getTotalAmount();
            totalRevenue += totalAmount;
        }

        return String.format(
                "Financial Report (%s)\nTotal Orders: %d\nTotal Revenue: $%.2f",
                period, orders.size(), totalRevenue
        );
    }

    public void sendReportToAdmin(String report) {
        System.out.println("Sent to admin:\n" + report);
    }
}
