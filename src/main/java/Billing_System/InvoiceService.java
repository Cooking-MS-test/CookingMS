package Billing_System;

import Notification_And_Alerts.Customer;
import models.Order;

import java.time.format.DateTimeFormatter;

public class InvoiceService {
    public String generateInvoice(Order order) {
        return String.format(
                "Invoice for Order #%s\nCustomer: %s\nAmount: $%.2f\nDate: %s",
                order.getOrderId(),
                order.getCustomer().getName(),
                order.getTotalAmount(),
                order.getOrderDate().format(DateTimeFormatter.ISO_DATE)
        );
    }

    public void sendInvoice(Customer customer, String invoice) {
        // Send to customer's email/phone (existing contact info)
        System.out.println("Sent invoice to " + customer.getName() + ":\n" + invoice);
    }
}
