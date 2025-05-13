package stepDefinitions.CustomerProfileManagement;

import static org.junit.Assert.*;

import Notification_And_Alerts.Customer;
import models.Admin;
import models.Order;

import io.cucumber.java.en.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderHistorySteps {
    private Customer customerHistory,c1,c2,c11,c22;
    private List<String> retrievedOrders;
    private List<String> retrievedOrders1;
    private List<String> retrievedOrders2;
    private Admin admin;


    @Given("the customer has previous orders")
    public void theCustomerHasPreviousOrders() {
        customerHistory = new Customer();
        Order o1 = new Order("1",customerHistory,"Grilled Chicken",50.0);
        Order o2 = new Order("2",customerHistory,"Vegan Salad",25.5);
        customerHistory.addOrder(o1);
        customerHistory.addOrder(o2);
    }

    @When("they check their order history")
    public void theyCheckTheirOrderHistory() {
        retrievedOrders = customerHistory.getPastOrders();

    }

    @Then("they see a list of past orders")
    public void theySeeAListOfPastOrders() {
        assertFalse(retrievedOrders.isEmpty());
        assertEquals(2, retrievedOrders.size());
    }

    @When("they reorder {string}")
    public void theyReorder(String order) {
        order="1";
        assertEquals("Order Found and ReOrder done successfully", customerHistory.reorder(order));
    }

    @Then("the system confirms the reorder was successful")
    public void theSystemConfirmsTheReorderWasSuccessful() {
        assertEquals("Order Found and ReOrder done successfully", customerHistory.reorder("2"));
    }

    @Given("multiple customers have order histories")
    public void multipleCustomersHaveOrderHistories() {
         c1 = new Customer();
         c2 = new Customer();
        Order order1 = new Order("3",c1,"Fried Chicken",25.0);
        Order order2 = new Order("4",c2,"Chicken Salad",25.5);
        c1.addOrder(order1);
        c2.addOrder(order2);
    }

    @When("the chef checks a specific customer’s history")
    public void theChefChecksASpecificCustomerSHistory() {
        retrievedOrders1=c1.getPastOrders();
        retrievedOrders2=c2.getPastOrders();

    }

    @Then("the chef sees their past orders")
    public void theChefSeesTheirPastOrders() {
        assertFalse(retrievedOrders1.isEmpty());
        assertEquals(1, retrievedOrders1.size());
        assertTrue(retrievedOrders1.get(0).contains("Fried Chicken"));
        assertFalse(retrievedOrders2.isEmpty());
        assertEquals(1, retrievedOrders2.size());
        assertTrue(retrievedOrders2.get(0).contains("Chicken Salad"));
    }

    @Given("the system administrator wants to analyze trends")
    public void the_system_administrator_wants_to_analyze_trends() {
        admin=new Admin("Abdulkreem");
        c11 = new Customer("saed");
       // c22 = new Customer("ahmad");
        Order order11 = new Order("5",c11,"Friedd Chicken",25.5);
       // Order order22 = new Order("6",c22,"Chicken Salad",25.5);
        c11.addOrder(order11);
        //c22.addOrder(order22);
        admin.addCustomer(c11);
       // admin.addCustomer(c22);


        // You can initialize test data here if needed
    }

    @When("they retrieve customer order history")
    public void they_retrieve_customer_order_history() {
        List<Customer> customers = admin.getCustomers();
        assertFalse(customers.isEmpty());
        // Fetch order history from database or mock data
    }

    @Then("they get insights on customer preferences")
    public void they_get_insights_on_customer_preferences() {
        List<String>PastOrders= admin.getPastOrders();
        assertFalse(PastOrders.isEmpty());
        assertEquals(2, PastOrders.size());
        assertEquals(1, admin.getCustomers().size());
    }


}
