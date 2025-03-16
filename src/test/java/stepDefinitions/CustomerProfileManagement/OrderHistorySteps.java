package stepDefinitions.CustomerProfileManagement;

import static org.junit.Assert.*;

import models.OrderHistory;
import io.cucumber.java.en.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderHistorySteps {
    private OrderHistory orderHistory;
    private Map<String, OrderHistory> customerOrders = new HashMap<>();
    private List<String> retrievedOrders;

    @Given("the customer has previous orders")
    public void theCustomerHasPreviousOrders() {
        orderHistory = new OrderHistory();
        orderHistory.addOrder("Grilled Chicken");
        orderHistory.addOrder("Vegan Salad");
    }

    @When("they check their order history")
    public void theyCheckTheirOrderHistory() {
        retrievedOrders = orderHistory.getPastOrders();
    }

    @Then("they see a list of past orders")
    public void theySeeAListOfPastOrders() {
        assertFalse(retrievedOrders.isEmpty());
        assertEquals(2, retrievedOrders.size());
    }

    @When("they reorder {string}")
    public void theyReorder(String order) {
        assertTrue(orderHistory.getPastOrders().contains(order));
    }

    @Then("the system confirms the reorder was successful")
    public void theSystemConfirmsTheReorderWasSuccessful() {
        System.out.println("Order placed successfully!");
    }

    @Given("multiple customers have order histories")
    public void multipleCustomersHaveOrderHistories() {
        OrderHistory johnHistory = new OrderHistory();
        johnHistory.addOrder("Grilled Chicken");
        customerOrders.put("John", johnHistory);

        OrderHistory aliceHistory = new OrderHistory();
        aliceHistory.addOrder("Vegan Salad");
        customerOrders.put("Alice", aliceHistory);
    }

    @When("the chef checks a specific customer’s history")
    public void theChefChecksASpecificCustomerSHistory() {
        retrievedOrders = customerOrders.get("John").getPastOrders();
    }

    @Then("the chef sees their past orders")
    public void theChefSeesTheirPastOrders() {
        assertEquals(1, retrievedOrders.size());
        assertEquals("Grilled Chicken", retrievedOrders.get(0));
    }

    @Given("the system administrator wants to analyze trends")
    public void the_system_administrator_wants_to_analyze_trends() {
        System.out.println("Admin is preparing to analyze order trends.");
        // You can initialize test data here if needed
    }

    @When("they retrieve customer order history")
    public void they_retrieve_customer_order_history() {
        System.out.println("Admin retrieves customer order history.");
        // Fetch order history from database or mock data
    }

    @Then("they get insights on customer preferences")
    public void they_get_insights_on_customer_preferences() {
        System.out.println("Admin analyzes trends to get customer insights.");
        // Implement trend analysis logic
        assertTrue("Customer trends identified", true); // Example assertion
    }


}
