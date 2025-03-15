package stepDefinitions;

import static org.junit.Assert.*;

import models.Customer;
import io.cucumber.java.en.*;
import java.util.HashMap;
import java.util.Map;

public class CustomerProfileSteps {
    private Customer customer;
    private Map<String, Customer> customerProfiles = new HashMap<>();

    @Given("the customer is on the profile settings page")
    public void theCustomerIsOnTheProfileSettingsPage() {
        customer = new Customer("John Doe");
    }

    @When("they enter dietary preferences {string} and allergies {string}")
    public void theyEnterDietaryPreferencesAndAllergies(String dietaryPreferences, String allergies) {
        customer.setDietaryPreferences(dietaryPreferences);
        customer.setAllergies(allergies);
    }

    @Then("the system saves the information successfully")
    public void theSystemSavesTheInformationSuccessfully() {
        assertNotNull(customer.getDietaryPreferences());
        assertNotNull(customer.getAllergies());
    }

    @Given("the customer has saved dietary preferences {string} and allergies {string}")
    public void theCustomerHasSavedDietaryPreferencesAndAllergies(String dietaryPreferences, String allergies) {
        customer = new Customer("John Doe");
        customer.setDietaryPreferences(dietaryPreferences);
        customer.setAllergies(allergies);
    }

    @When("they update preferences to {string} and allergies to {string}")
    public void theyUpdatePreferencesToAndAllergiesTo(String newPreferences, String newAllergies) {
        customer.setDietaryPreferences(newPreferences);
        customer.setAllergies(newAllergies);
    }

    @Then("the system updates the information successfully")
    public void theSystemUpdatesTheInformationSuccessfully() {
        assertNotNull(customer.getDietaryPreferences());
        assertNotNull(customer.getAllergies());
    }

    @Given("multiple customers with dietary preferences")
    public void multipleCustomersWithDietaryPreferences() {
        customerProfiles.put("John", new Customer("John"));
        customerProfiles.get("John").setDietaryPreferences("Vegetarian");
        customerProfiles.get("John").setAllergies("Peanuts");

        customerProfiles.put("Alice", new Customer("Alice"));
        customerProfiles.get("Alice").setDietaryPreferences("Vegan");
        customerProfiles.get("Alice").setAllergies("Dairy");
    }

    @When("the chef accesses a customer's profile")
    public void theChefAccessesACustomerSProfile() {
        // Accessing customers in HashMap
    }

    @Then("the chef sees the correct dietary preferences and allergies")
    public void theChefSeesTheCorrectDietaryPreferencesAndAllergies() {
        assertEquals("Vegetarian", customerProfiles.get("John").getDietaryPreferences());
        assertEquals("Peanuts", customerProfiles.get("John").getAllergies());

        assertEquals("Vegan", customerProfiles.get("Alice").getDietaryPreferences());
        assertEquals("Dairy", customerProfiles.get("Alice").getAllergies());
    }
}
