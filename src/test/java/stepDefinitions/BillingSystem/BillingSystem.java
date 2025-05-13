package stepDefinitions.BillingSystem;

import Billing_System.InvoiceService;
import Billing_System.ReportService;
import Notification_And_Alerts.Customer;
import Notification_And_Alerts.Meal;
import io.cucumber.java.en.*;
import models.Order;

import java.util.*;

import static org.junit.Assert.*;

public class BillingSystem {

    Map<String, Order> orders = new HashMap<>();
    String generatedInvoice;
    String generatedReport;
    InvoiceService invoiceService = new InvoiceService();
    ReportService reportService = new ReportService();
    List<Order> reportOrders = new ArrayList<>();

    @Given("the customer has placed an order with ID {string}")
    public void the_customer_has_placed_an_order_with_id(String orderId) {
        Customer customer = new Customer("John Doe", "email@example.com");
        Order order = new Order(orderId, customer, "Pizza", 15.99);
        orders.put(orderId, order);
    }

    @When("the system generates an invoice for order {string}")
    public void the_system_generates_an_invoice_for_order(String orderId) {
        Order order = orders.get(orderId);
        generatedInvoice = invoiceService.generateInvoice(order);
        invoiceService.sendInvoice(order.getCustomer(), generatedInvoice);
    }

    @Then("the customer should receive the invoice for order {string}")
    public void the_customer_should_receive_the_invoice_for_order(String orderId) {
        assertNotNull(generatedInvoice);
        assertTrue(generatedInvoice.contains(orderId));
    }

    @Given("the system has order data for the period {string}")
    public void the_system_has_order_data_for_the_period(String period) {
        Customer customer = new Customer("Admin", "admin@example.com");
        reportOrders.clear();
        reportOrders.add(new Order("O100", customer, "Pasta", 12.5));
        reportOrders.add(new Order("O101", customer, "Soup", 7.0));
    }

    @When("the system generates a financial report for {string}")
    public void the_system_generates_a_financial_report_for(String period) {
        generatedReport = reportService.generateFinancialReport(period, reportOrders);
        reportService.sendReportToAdmin(generatedReport);
    }

    @Then("the system administrator should receive the report for {string}")
    public void the_system_administrator_should_receive_the_report_for(String period) {
        assertNotNull(generatedReport);
        assertTrue(generatedReport.contains(period));
        assertTrue(generatedReport.contains("Total Revenue"));
    }
}
